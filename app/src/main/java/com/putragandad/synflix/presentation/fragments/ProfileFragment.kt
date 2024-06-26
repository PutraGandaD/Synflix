package com.putragandad.synflix.presentation.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.putragandad.synflix.R
import com.putragandad.synflix.databinding.FragmentProfileBinding
import com.putragandad.synflix.presentation.viewmodels.UserViewModel
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvFullname = binding.tvProfileName
        val tvUsername = binding.tvProfileUsername
        val tvEmail = binding.tvProfileEmail

        userViewModel.userInfo.observe(viewLifecycleOwner) {
            tvFullname.setText(it.fullname)
            if(it.username.isNotEmpty()) tvUsername.setText(it.username)
            tvEmail.setText(it.email)

            if(it.profilePictureURI != null) {
                // set profile image
                val image = Uri.parse(it.profilePictureURI)
                Glide.with(requireView())
                    .load(image)
                    .placeholder(R.drawable.synflix_profile_picture_default)
                    .into(binding.ivProfilePicture)
            }
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
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
            .show()
    }
}