package iti.workshop.newApp.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import iti.workshop.data.source.shared.SharedManager
import iti.workshop.newApp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    private lateinit var viewModel: SplashViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedManager = SharedManager.getInstance(requireContext())!!
        viewModel =
            ViewModelProvider(
                requireActivity(),
                SplashViewModelFactory(sharedManager)
            )[SplashViewModel::class.java]

        (activity as AppCompatActivity).supportActionBar?.hide()
        viewModel.seamlessDetector {
            if (it)
                findNavController().navigate(R.id.action_splashFragment_to_dashBoardFragment)
            else
                findNavController().navigate(R.id.action_splashFragment_to_registrationFragment)

        }


    }
}