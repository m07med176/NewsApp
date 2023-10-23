package iti.workshop.newApp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import iti.workshop.newApp.R
import iti.workshop.newApp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonlogin.setOnClickListener{
            login()
        }
        binding.checkBoxLogin.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(activity, "Check Box is Enable", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Check Box is Disable", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun login() {
        val email: String = binding.emailloginEditTxt.getText().toString()
        val password: String = binding.passwordloginEditTxt.getText().toString()

        /*   if (!email.isEmpty() && !password.isEmpty()) {
               Toast.makeText(activity, email +  password, Toast.LENGTH_SHORT).show()

           } else {
               Toast.makeText(activity, "All Fields Required", Toast.LENGTH_SHORT).show()
           }*/

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val isEmailValid = email.matches(emailPattern.toRegex())

        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
        val isPasswordValid = password.matches(passwordPattern.toRegex())
        if (!isEmailValid) {
            //  binding.emailloginEditTxt.error = "Invalid email address"
            Toast.makeText(activity, "Email Required", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(activity, email, Toast.LENGTH_SHORT).show()

        }

        if (!isPasswordValid) {
            Toast.makeText(activity, "password Required", Toast.LENGTH_SHORT).show()

            //   binding.passwordloginEditTxt.error = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
        }else{
            Toast.makeText(activity,   password, Toast.LENGTH_SHORT).show()
        }
    }

}