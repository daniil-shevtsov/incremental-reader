package shevtsov.daniil.incrementalreader.main

import androidx.core.view.postDelayed
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.core.util.viewLifecycleLazy
import shevtsov.daniil.incrementalreader.databinding.FragmentMainBinding.bind

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewLifecycleLazy { bind(requireView()) }

    override fun onResume() {
        super.onResume()

        view?.postDelayed(2000) {
            val direction = MainFragmentDirections.navigateToCreationDirection()
            findNavController().navigate(direction)
        }
    }
}
