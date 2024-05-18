package com.putragandad.moviedbch5.ui.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.databinding.FragmentLoginBinding
import com.putragandad.moviedbch5.ui.viewmodels.UserViewModel
import com.putragandad.moviedbch5.ui.viewmodels.UserViewModelFactory
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel : UserViewModel by viewModels {
        UserViewModelFactory.getInstance(requireActivity())
    }

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

        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.editText?.text.toString()
            val password = binding.etLoginPassword.editText?.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                userViewModel.login(email, password).observe(viewLifecycleOwner) { login ->
                    if(login) {
                        Snackbar.make(requireView(), "Login successful! You're signed in as $email", Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        Snackbar.make(requireView(), "Invalid email or password. Try Again.", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Snackbar.make(requireView(), "Email and password can't be empty", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onStart() {
        super.onStart()

        viewLifecycleOwner.lifecycleScope.launch {
            val loginStatus = userViewModel.readLoginStatus()!!
            if (loginStatus) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}