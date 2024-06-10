package com.putragandad.moviedbch5.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.databinding.FragmentProfileBinding
import com.putragandad.moviedbch5.presentation.viewmodels.UserViewModel
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by inject()

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

        val tvFullname = binding.tvProfileName
        val tvUsername = binding.tvProfileUsername
        val tvEmail = binding.tvProfileEmail

        userViewModel.userInfo.observe(viewLifecycleOwner) { (email, fullname, username) ->
            tvFullname.setText(fullname)
            if(username.isNotEmpty()) tvUsername.setText(username)
            tvEmail.setText(email)
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
                findNavController().popBackStack(R.id.splashFragment, true)
                findNavController().navigate(R.id.loginFragment)
            }
            .show()
    }
}