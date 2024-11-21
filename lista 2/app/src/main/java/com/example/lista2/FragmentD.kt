package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation. navigation
import androidx.navigation.fragment.findNavController

import com.example.lista2.databinding.FragmentABinding
import com.example.lista2.databinding.FragmentBBinding

class FragmentD : Fragment(R.layout.fragment_d) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString("username") ?: "blad"
        view.findViewById<TextView>(R.id.tvWelcomeMessage).setText("Witaj ${username}")
        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            findNavController().navigate(R.id.action_fragmentD_to_fragmentA)
        }
    }
}
