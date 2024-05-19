package com.putragandad.moviedbch5.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.databinding.FragmentProfileBinding
import com.putragandad.moviedbch5.databinding.FragmentProfileEditBinding
import com.putragandad.moviedbch5.ui.viewmodels.UserViewModel
import com.putragandad.moviedbch5.ui.viewmodels.UserViewModelFactory

class ProfileEditFragment : Fragment() {
    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    private val userViewModel : UserViewModel by viewModels {
        UserViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fullname = binding.etEditprofileFullname
        val username = binding.etEditprofileUsername
        val email = binding.etEditprofileEmail

        userViewModel.getUserFullname().observe(viewLifecycleOwner) { value ->
            fullname.editText?.setText(value)
        }

        userViewModel.getUserUsername().observe(viewLifecycleOwner) { value ->
            username.editText?.setText(value)
        }

        userViewModel.getUserEmail().observe(viewLifecycleOwner) { value ->
            email.editText?.setText(value)
        }

        binding.btnEditProfile.setOnClickListener {
            saveProfile(fullname, username, email)
        }

        binding.btnBackRegister.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun saveProfile(fullname: TextInputLayout, username: TextInputLayout, email: TextInputLayout) {
        if(fullname.isNotEmpty() && email.isNotEmpty()) {
            val getFullname = fullname.editText?.text.toString()
            val getUsername = username.editText?.text.toString()
            val getEmail = email.editText?.text.toString()
            userViewModel.saveAccountDetail(getUsername, getFullname, getEmail)
            findNavController().popBackStack()
            Snackbar.make(requireView(), "Profile successfully saved.", Snackbar.LENGTH_LONG).show()
        } else {
            Snackbar.make(requireView(), "Email / Fullname field can't be empty!", Snackbar.LENGTH_LONG).show()
        }
    }
}