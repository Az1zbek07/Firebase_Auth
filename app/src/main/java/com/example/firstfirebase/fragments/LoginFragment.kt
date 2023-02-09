package com.example.firstfirebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.firstfirebase.R
import com.example.firstfirebase.databinding.FragmentLoginBinding
import com.example.firstfirebase.toast
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            binding.pr.isVisible = true
            binding.btnLogin.isEnabled = false

            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    binding.pr.isVisible = false
                    binding.btnLogin.isEnabled = true
                    if (it.isSuccessful){
                        toast("Successfully logged in")
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }else{
                        toast(it.exception?.message.toString())
                    }
                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}