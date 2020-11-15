package shevtsov.daniil.incrementalreader.fragmenta

import androidx.core.view.postDelayed
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.databinding.FragmentABinding.bind
import shevtsov.daniil.incrementalreader.fragmenta.FragmentADirections.Companion.navigateToBDirection

class FragmentA : Fragment(R.layout.fragment_a) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

    override fun onResume() {
        super.onResume()

        view?.postDelayed(2000) {
            val direction = navigateToBDirection()
            findNavController().navigate(direction)
        }
    }
}
