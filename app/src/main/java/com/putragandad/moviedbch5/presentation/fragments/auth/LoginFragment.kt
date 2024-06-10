package com.putragandad.moviedbch5.presentation.fragments.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.databinding.FragmentLoginBinding
import com.putragandad.moviedbch5.presentation.viewmodels.UserViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by inject()

    private var loginFormIsNotEmpty = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.loginStatus.observe(viewLifecycleOwner) { status ->
            if(loginFormIsNotEmpty) {
                validateLogin(status)
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.editText?.text.toString()
            val password = binding.etLoginPassword.editText?.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                loginFormIsNotEmpty = true
                userViewModel.login(email, password)
            } else {
                Snackbar.make(requireView(), "Email and password can't be empty", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validateLogin(status : Boolean) {
        if(status) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            Snackbar.make(requireView(), "Login successful! You're signed in.", Snackbar.LENGTH_LONG).show()
        } else {
            Snackbar.make(requireView(), "Invalid email or password. Try Again.", Snackbar.LENGTH_LONG)
                .show()
        }
    }
}