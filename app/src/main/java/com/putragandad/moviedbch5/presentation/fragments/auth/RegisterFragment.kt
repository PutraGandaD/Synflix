package com.putragandad.moviedbch5.presentation.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.putragandad.moviedbch5.databinding.FragmentRegisterBinding
import com.putragandad.moviedbch5.presentation.viewmodels.UserViewModel
import org.koin.android.ext.android.inject

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackRegister.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnCreateAccount.setOnClickListener {
            val fullName = binding.etRegisterName.editText?.text.toString()
            val email = binding.etRegisterEmail.editText?.text.toString()
            val password = binding.etRegisterPassword.editText?.text.toString()
            val passwordCv = binding.etRegisterPasswordCv.editText?.text.toString()

            registerAccount(fullName, email, password, passwordCv)
        }
    }

    private fun registerAccount(fullName: String, email: String, password: String, passwordCv:String) {
        if(fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passwordCv.isNotEmpty()) {
            if(password == passwordCv) {
                userViewModel.register(fullName, email, password)
                Snackbar.make(requireView(), "User registered successfully! Please log in with your credentials to continue", Snackbar.LENGTH_LONG).show()
                findNavController().popBackStack()
            } else {
                Snackbar.make(requireView(), "Passwords do not match. Please make sure your password and password confirmation are the same.", Snackbar.LENGTH_LONG).show()
            }
        } else {
            Snackbar.make(requireView(), "Please fill in all fields to register. All fields are required.", Snackbar.LENGTH_LONG).show()
        }
    }
}