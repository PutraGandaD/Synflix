package com.putragandad.moviedbch5.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.databinding.FragmentLoginBinding
import com.putragandad.moviedbch5.databinding.FragmentProfileBinding
import com.putragandad.moviedbch5.ui.viewmodels.UserViewModel
import com.putragandad.moviedbch5.ui.viewmodels.UserViewModelFactory
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val userViewModel : UserViewModel by viewModels {
        UserViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fullname = binding.tvProfileName
        val username = binding.tvProfileUsername
        val email = binding.tvProfileEmail

        userViewModel.getUserFullname().observe(viewLifecycleOwner) { value ->
            fullname.setText(value)
        }

        userViewModel.getUserUsername().observe(viewLifecycleOwner) { value ->
            if(value.isNotEmpty()) username.setText(value)
        }

        userViewModel.getUserEmail().observe(viewLifecycleOwner) { value ->
            email.setText(value)
        }

        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_profileEditFragment)
        }

        binding.btnSignOut.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Log out")
            .setMessage("Are you sure to logout from your Synflix Account?")
            .setNegativeButton("No") { dialog, which ->
                dialog.cancel()
            }
            .setPositiveButton("Yes") { dialog, which ->
                userViewModel.logout()
                Snackbar.make(requireView(), "Logged out successfully!", Snackbar.LENGTH_LONG).show()
                //handle back button
                findNavController().popBackStack(R.id.homeFragment, true); // clear back stack with popup to main nav and inclusive to true to clean the stack
                findNavController().navigate(R.id.loginFragment); // navigate to login screen
            }
            .show()
    }
}