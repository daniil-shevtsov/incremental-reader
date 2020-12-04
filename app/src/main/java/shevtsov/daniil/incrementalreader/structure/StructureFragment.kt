package shevtsov.daniil.incrementalreader.structure

import androidx.fragment.app.Fragment
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.databinding.FragmentCreationBinding.bind

class StructureFragment : Fragment(R.layout.fragment_structure) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

}