package iti.workshop.newApp.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import iti.workshop.data.source.remote.models.regieter.RegisterBody
import iti.workshop.newApp.MainActivity
import iti.workshop.newApp.R
import iti.workshop.newApp.databinding.FragmentLoginBinding
import iti.workshop.newApp.databinding.FragmentRegistrationBinding
import iti.workshop.newApp.networkconnection.NetWorkChecker

import iti.workshop.newApp.states.RegisterState
import iti.workshop.newApp.utils.ValidationReg
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {

    lateinit var binding: FragmentRegistrationBinding
    lateinit var networkState: NetWorkChecker
    val viewModel: RegistrationViewModel by viewModels { RegistrationViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkState = NetWorkChecker(requireContext())
        binding.submit.setOnClickListener(View.OnClickListener {
            if(!ValidationReg.validateData(
                    binding.passWord.text.toString(),
                    binding.email.text.toString(),
                    binding.confPassword.text.toString()
                ) ){
                Toast.makeText(
                    requireContext(), "Please check your email and password", Toast.LENGTH_LONG
                ).show()
                return@OnClickListener
            }
            if(!networkState.checkForInternet()){
                Toast.makeText(
                    requireContext(), "Please check your network connection", Toast.LENGTH_LONG
                ).show()
                return@OnClickListener
        }


                lifecycleScope.launch {
                    viewModel.regUser(
                        RegisterBody(
                            binding.userName.text.toString(),
                            binding.email.text.toString(),
                            binding.passWord.text.toString(),
                            false
                        )
                    )
                    viewModel.regbool.collect {
                        when (it) {
                            is RegisterState.Success -> {
                                Toast.makeText(requireContext(), "Done", Toast.LENGTH_LONG).show()
                                Log.i("TAG2", "DONE: ")
                            }

                            is RegisterState.Loading -> {
                                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG)
                                    .show()
                                Log.i("TAG2", "LOADING: ")

                            }

                            is RegisterState.Failure -> {
                                Toast.makeText(
                                    requireContext(), "${it.error.toString()}", Toast.LENGTH_LONG
                                ).show()
                                Log.i("TAG2", "${it.error.toString()}")

                            }

                            else -> {
                                println("TEEEE")
                            }
                        }
                    }

                }



        })
    }


}