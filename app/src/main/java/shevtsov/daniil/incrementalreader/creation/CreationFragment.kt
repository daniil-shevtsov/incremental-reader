package shevtsov.daniil.incrementalreader.creation

import androidx.fragment.app.Fragment
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.databinding.FragmentCreationBinding.bind

class CreationFragment : Fragment(R.layout.fragment_creation) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

}
