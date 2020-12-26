package shevtsov.daniil.incrementalreader.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.IncrementalReaderApplication
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.databinding.FragmentMainBinding
import shevtsov.daniil.incrementalreader.databinding.FragmentMainBinding.bind
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as IncrementalReaderApplication)
            .getAppComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.initViews()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect { event -> handleEvent(event) }
        }

    }

    private fun FragmentMainBinding.initViews() {
        openCreationButton.setOnClickListener { viewModel.onOpenCreation() }
        openLearningButton.setOnClickListener { viewModel.onOpenLearning() }
        openStructureButton.setOnClickListener { viewModel.onOpenStructure() }
    }

    private fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OpenCreation -> openCreation()
            is MainEvent.OpenLearning -> openLearning()
            is MainEvent.OpenStructure -> openStructure()
        }
    }

    private fun openCreation() {
        val direction = MainFragmentDirections.navigateToCreationDirection()
        findNavController().navigate(direction)
    }

    private fun openLearning() {
        val direction = MainFragmentDirections.navigateToLearningDirection()
        findNavController().navigate(direction)
    }

    private fun openStructure() {
        val direction = MainFragmentDirections.navigateToStructureDirection()
        findNavController().navigate(direction)
    }

}
