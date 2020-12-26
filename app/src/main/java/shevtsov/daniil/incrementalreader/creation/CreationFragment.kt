package shevtsov.daniil.incrementalreader.creation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.IncrementalReaderApplication
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
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

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.events.collect { event -> handleEvent(event) }
//        }

    }

    private fun FragmentCreationBinding.initViews() {
        with(creationEditText) {
            doAfterTextChanged { editable ->
                viewModel.onTextEntered(text = editable.toString())
            }

            setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    viewModel.onSaveText()
                }
            }
        }

    }

}
