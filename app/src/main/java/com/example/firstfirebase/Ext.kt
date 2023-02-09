package com.example.firstfirebase

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(text: String){
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}