package shevtsov.daniil.incrementalreader.fragmentb

import androidx.fragment.app.Fragment
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.databinding.FragmentBBinding.bind

class FragmentB : Fragment(R.layout.fragment_b) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

}
