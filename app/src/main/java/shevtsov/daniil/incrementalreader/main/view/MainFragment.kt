package shevtsov.daniil.incrementalreader.main.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wajahatkarim3.roomexplorer.RoomExplorer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.BuildConfig
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.IncrementalReaderApplication
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemDatabase
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.creation.navigation.CreationInitArguments
import shevtsov.daniil.incrementalreader.databinding.FragmentMainBinding
import shevtsov.daniil.incrementalreader.databinding.FragmentMainBinding.bind
import shevtsov.daniil.incrementalreader.learning.navigation.LearningInitArguments
import shevtsov.daniil.incrementalreader.main.presentation.MainScreenEvent
import shevtsov.daniil.incrementalreader.main.presentation.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as IncrementalReaderApplication)
            .appComponent
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.initViews()

        if (BuildConfig.DEBUG) {
            binding.loadPeaceButton.isVisible = true
            binding.showDatabaseButton.apply {
                isVisible = true
                setOnClickListener {
                    RoomExplorer.show(context, InformationItemDatabase::class.java, "information_item_database")
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect { event -> handleEvent(event) }
        }

    }

    private fun FragmentMainBinding.initViews() {
        loadPeaceButton.setOnClickListener { viewModel.onLoadPeace() }
        openCreationButton.setOnClickListener { viewModel.onOpenCreation() }
        openLearningButton.setOnClickListener { viewModel.onOpenLearning() }
        openStructureButton.setOnClickListener { viewModel.onOpenStructure() }
    }

    private fun handleEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OpenCreation -> openCreation(arguments = CreationInitArguments.Create)
            is MainScreenEvent.LoadPeace -> openCreation(arguments = CreationInitArguments.LoadPeace)
            is MainScreenEvent.OpenLearning -> openLearning()
            is MainScreenEvent.OpenStructure -> openStructure()
        }
    }

    private fun openCreation(arguments: CreationInitArguments) {
        val direction =
            MainFragmentDirections.mainToCreation(initArguments = arguments)
        findNavController().navigate(direction)
    }

    private fun openLearning() {
        val arguments = LearningInitArguments.Empty
        val direction = MainFragmentDirections.mainToLearning(initArguments = arguments)
        findNavController().navigate(direction)
    }

    private fun openStructure() {
        val direction = MainFragmentDirections.mainToStructure()
        findNavController().navigate(direction)
    }

}
