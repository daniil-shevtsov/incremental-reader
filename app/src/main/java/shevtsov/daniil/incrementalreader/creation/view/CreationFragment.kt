package shevtsov.daniil.incrementalreader.creation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.IncrementalReaderApplication
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.creation.presentation.CreationScreenEvent
import shevtsov.daniil.incrementalreader.creation.presentation.CreationViewModel
import shevtsov.daniil.incrementalreader.databinding.FragmentCreationBinding
import shevtsov.daniil.incrementalreader.databinding.FragmentCreationBinding.bind
import javax.inject.Inject

class CreationFragment : Fragment(R.layout.fragment_creation) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CreationViewModel by viewModels { viewModelFactory }

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

    private fun handleEvent(event: CreationScreenEvent) {
        when (event) {
            is CreationScreenEvent.ShowItemSaved -> showItemSavedToast(itemName = event.itemName)
        }
    }

    private fun showItemSavedToast(itemName: String) {
        val message = getString(R.string.item_created_message, itemName)
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun FragmentCreationBinding.initViews() {
        creationItemNameEditText.setListeners(
            textEnteredAction = viewModel::onNameEntered,
        )

        creationItemContentEditText.setListeners(
            textEnteredAction = viewModel::onContentEntered,
        )

        creationCreateButton.setOnClickListener { viewModel.onSaveContent() }
    }

    private fun EditText.setListeners(
        textEnteredAction: (text: String) -> Unit
    ) {
        doAfterTextChanged { editable ->
            textEnteredAction.invoke(editable.toString())
        }
    }

}
