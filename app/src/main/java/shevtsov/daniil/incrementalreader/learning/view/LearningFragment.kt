package shevtsov.daniil.incrementalreader.learning.view

import androidx.fragment.app.Fragment
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.databinding.FragmentCreationBinding.bind

class LearningFragment : Fragment(R.layout.fragment_learning) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

}