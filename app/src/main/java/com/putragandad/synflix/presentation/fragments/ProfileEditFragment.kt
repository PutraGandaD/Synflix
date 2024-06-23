package com.putragandad.synflix.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.work.WorkInfo
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.putragandad.synflix.R
import com.putragandad.synflix.databinding.FragmentProfileEditBinding
import com.putragandad.synflix.presentation.viewmodels.UserViewModel
import com.putragandad.common.utils.Constant.Companion.KEY_IMAGE_URI
import com.putragandad.common.utils.blur_image.makeStatusNotification
import org.koin.android.ext.android.inject

class ProfileEditFragment : Fragment() {
    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    private val userViewModel : UserViewModel by inject()
    private var uriProfileImageTemp = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvFullname = binding.etEditprofileFullname
        val tvUsername = binding.etEditprofileUsername
        val tvEmail = binding.etEditprofileEmail

        userViewModel.userInfo.observe(viewLifecycleOwner) {
            tvFullname.editText?.setText(it.fullname)
            if(it.username.isNotEmpty()) tvUsername.editText?.setText(it.username)
            tvEmail.editText?.setText(it.email)
            uriProfileImageTemp = it.profilePictureURI
            setPreviewProfilePicture()
        }

        val pickMedia = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedPicUri = result.data?.data
                userViewModel.applyBlur(selectedPicUri.toString())
                userViewModel.outputWorkInfos.observe(viewLifecycleOwner, workInfosObserver())
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
            backPressedDialog()
        }

        handleBackPress()
    }

    private fun saveProfile(fullname: TextInputLayout, username: TextInputLayout, email: TextInputLayout, uriProfileImage: String) {
        val getFullname = fullname.editText?.text.toString()
        val getUsername = username.editText?.text.toString()
        val getEmail = email.editText?.text.toString()
        if(getFullname.isNotEmpty() && getEmail.isNotEmpty()) {
            userViewModel.saveAccountDetail(getUsername, getFullname, getEmail)
            userViewModel.setProfilePicture(uriProfileImage)
            uriProfileImageTemp = ""
            findNavController().popBackStack()
            Snackbar.make(requireView(), "Profile successfully saved.", Snackbar.LENGTH_LONG).show()
        } else {
            Snackbar.make(requireView(), "Email / Fullname field can't be empty!", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            // Note that these next few lines grab a single WorkInfo if it exists
            // This code could be in a Transformation in the ViewModel; they are included here
            // so that the entire process of displaying a WorkInfo is in one location.

            // If there are no matching work info, do nothing
            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }

            // We only care about the one output status.
            // Every continuation has only one worker tagged TAG_OUTPUT
            val workInfo = listOfWorkInfo[0]

            when(workInfo.state) {
                WorkInfo.State.RUNNING -> {
                    makeStatusNotification("Blurring Image...", requireContext())
                }
                WorkInfo.State.SUCCEEDED -> {
                    val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

                    if (!outputImageUri.isNullOrEmpty()) {
                        uriProfileImageTemp = outputImageUri
                        makeStatusNotification("Successfully blurred image.", requireContext())
                        setPreviewProfilePicture()
                    }
                }
                WorkInfo.State.FAILED -> {
                    makeStatusNotification("Failed to blur the image.", requireContext())
                }
                else -> {

                }
            }
        }
    }

    private fun setPreviewProfilePicture() {
        Glide.with(requireView())
            .load(uriProfileImageTemp)
            .placeholder(R.drawable.synflix_profile_picture_default)
            .into(binding.ivProfilePicture)
    }

    private fun backPressedDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Edit Profile")
            .setMessage("Are you sure to cancel? Any changes will be unsaved, including your profile picture.")
            .setNegativeButton("No") { dialog, which ->
                dialog.cancel()
            }
            .setPositiveButton("Yes") { dialog, which ->
                findNavController().popBackStack()
            }
            .show()
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPressedDialog()
            }
        })
    }

}