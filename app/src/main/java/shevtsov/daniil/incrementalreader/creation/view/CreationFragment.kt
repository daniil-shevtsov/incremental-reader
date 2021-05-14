package shevtsov.daniil.incrementalreader.creation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.IncrementalReaderApplication
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.creation.presentation.CreationScreenEvent
import shevtsov.daniil.incrementalreader.creation.presentation.CreationViewModel
import shevtsov.daniil.incrementalreader.creation.presentation.CreationViewState
import shevtsov.daniil.incrementalreader.databinding.FragmentCreationBinding
import shevtsov.daniil.incrementalreader.databinding.FragmentCreationBinding.bind
import javax.inject.Inject

class CreationFragment : Fragment(R.layout.fragment_creation) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

    private val args: CreationFragmentArgs by navArgs()

    private val adapter = GroupieAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CreationViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as IncrementalReaderApplication)
            .appComponent
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.initViews()

        viewLifecycleOwner.lifecycleScope.launch {
            Log.d("KEK", "observe state")
            viewModel.state.collect { state -> renderState(state) }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            Log.d("KEK", "observe events")
            viewModel.events.collect { event -> handleEvent(event) }
        }

        viewModel.onArguments(arguments = args.initArguments)

    }

    private fun renderState(state: CreationViewState) {
        with(state) {
            binding.creationItemNameEditText.setText(title)
            adapter.update(state.contentItems.map { (id, value) ->
                ContentGroupieItem(
                    contentPartId = id,
                    contentPart = value,
                    onTextChanged = { text -> viewModel.onContentEntered(id, text) },
                    onCreateChunk = { selectedText -> viewModel.onCreateChunk(selectedText) },
                    onCreateCloze = { selectedText -> viewModel.onCreateCloze(selectedText) }
                )
            })
        }
    }

    private fun handleEvent(event: CreationScreenEvent) {
        when (event) {
            is CreationScreenEvent.ShowItemSaved -> showItemSavedToast(itemName = event.itemName)
            is CreationScreenEvent.ShowChunkCreated -> showChunkCreatedToast()
            is CreationScreenEvent.ShowClozeCreated -> showClozeCreatedToast()
        }
    }

    private fun showItemSavedToast(itemName: String) {
        Log.d("KEK", "show message that $itemName created")
        val message = getString(R.string.item_created_message, itemName)
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showChunkCreatedToast() {
        val message = getString(R.string.chunk_created_message)
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showClozeCreatedToast() {
        val message = getString(R.string.cloze_created_message)
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun FragmentCreationBinding.initViews() {
        creationItemNameEditText.doAfterTextChanged { viewModel.onNameEntered(it.toString()) }

        optimizedContent.adapter = adapter
        optimizedContent.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        creationCreateButton.setOnClickListener { viewModel.onSaveContent() }
    }


}
