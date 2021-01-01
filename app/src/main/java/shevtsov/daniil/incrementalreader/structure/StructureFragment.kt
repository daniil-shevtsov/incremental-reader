package shevtsov.daniil.incrementalreader.structure

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.IncrementalReaderApplication
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.databinding.FragmentStructureBinding
import shevtsov.daniil.incrementalreader.databinding.FragmentStructureBinding.bind
import shevtsov.daniil.incrementalreader.structure.adapter.StructureAdapter
import javax.inject.Inject

class StructureFragment : Fragment(R.layout.fragment_structure) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: StructureViewModel by viewModels { viewModelFactory }

    private val structureAdapter = StructureAdapter()

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

    }

    private fun FragmentStructureBinding.initViews() {
        with(structureItemsRecyclerView) {
            adapter = structureAdapter
        }
    }

}