package shevtsov.daniil.incrementalreader.structure.view

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
import shevtsov.daniil.incrementalreader.databinding.FragmentStructureBinding
import shevtsov.daniil.incrementalreader.databinding.FragmentStructureBinding.bind
import shevtsov.daniil.incrementalreader.learning.navigation.LearningInitArguments
import shevtsov.daniil.incrementalreader.structure.presentation.StructureScreenEvent
import shevtsov.daniil.incrementalreader.structure.presentation.StructureViewModel
import shevtsov.daniil.incrementalreader.structure.presentation.StructureViewState
import shevtsov.daniil.incrementalreader.structure.view.adapter.StructureAdapter
import shevtsov.daniil.incrementalreader.structure.view.adapter.StructureAdapterAction
import javax.inject.Inject

class StructureFragment : Fragment(R.layout.fragment_structure) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: StructureViewModel by viewModels { viewModelFactory }

    private val structureAdapter = StructureAdapter(actionCallback = this::handleAdapterAction)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as IncrementalReaderApplication)
            .getAppComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.initViews()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state -> renderState(state = state) }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect { event -> handleEvent(event = event) }
        }

    }

    private fun renderState(state: StructureViewState) {
        structureAdapter.submitList(state.items)
    }

    private fun handleEvent(event: StructureScreenEvent) {
        when (event) {
            is StructureScreenEvent.OpenLearning -> openLearning(itemId = event.itemId)
        }
    }

    private fun handleAdapterAction(action: StructureAdapterAction) {
        when (action) {
            is StructureAdapterAction.ItemSelected -> viewModel.onItemSelected(id = action.itemId)
        }
    }

    private fun openLearning(itemId: String) {
        val arguments = LearningInitArguments.SelectedItem(itemId = itemId)
        val direction = StructureFragmentDirections.structureToLearning(initArguments = arguments)
        findNavController().navigate(direction)
    }

    private fun FragmentStructureBinding.initViews() {
        with(structureItemsRecyclerView) {
            adapter = structureAdapter
        }
    }

}