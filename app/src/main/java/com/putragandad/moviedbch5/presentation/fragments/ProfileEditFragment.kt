package com.putragandad.moviedbch5.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.databinding.FragmentProfileEditBinding
import com.putragandad.moviedbch5.presentation.viewmodels.UserViewModel
import org.koin.android.ext.android.inject

class ProfileEditFragment : Fragment() {
    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    private val userViewModel : UserViewModel by inject()

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

        val tvFullname = binding.etEditprofileFullname
        val tvUsername = binding.etEditprofileUsername
        val tvEmail = binding.etEditprofileEmail
        var uriProfileImageTemp = ""

        userViewModel.userInfo.observe(viewLifecycleOwner) {
            tvFullname.editText?.setText(it.fullname)
            if(it.username.isNotEmpty()) tvUsername.editText?.setText(it.username)
            tvEmail.editText?.setText(it.email)
            uriProfileImageTemp = it.profilePictureURI
            Glide.with(requireView())
                .load(it.profilePictureURI)
                .placeholder(R.drawable.synflix_profile_picture_default)
                .into(binding.ivProfilePicture)
        }

        val pickMedia = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedPicUri = result.data?.data
                Log.d("PhotoPicker", "Selected URI: $selectedPicUri")
                Glide.with(requireView())
                    .load(selectedPicUri)
                    .into(binding.ivProfilePicture)
                uriProfileImageTemp = selectedPicUri.toString()
            }
        }

        binding.btnChooseImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            pickMedia.launch(intent)
        }

        binding.btnEditProfile.setOnClickListener {
            saveProfile(tvFullname, tvUsername, tvEmail, uriProfileImageTemp)
        }

        binding.btnBackRegister.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun saveProfile(fullname: TextInputLayout, username: TextInputLayout, email: TextInputLayout, uriProfileImage: String) {
        val getFullname = fullname.editText?.text.toString()
        val getUsername = username.editText?.text.toString()
        val getEmail = email.editText?.text.toString()
        if(getFullname.isNotEmpty() && getEmail.isNotEmpty()) {
            userViewModel.saveAccountDetail(getUsername, getFullname, getEmail)
            userViewModel.setProfilePicture(uriProfileImage.toString())
            findNavController().popBackStack()
            Snackbar.make(requireView(), "Profile successfully saved.", Snackbar.LENGTH_LONG).show()
        } else {
            Snackbar.make(requireView(), "Email / Fullname field can't be empty!", Snackbar.LENGTH_LONG).show()
        }
    }

}