package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class FragmentC : Fragment(R.layout.fragment_c) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = view.findViewById<EditText>(R.id.etLoginUsername)
        val passwordEditText = view.findViewById<EditText>(R.id.etLoginPassword)

        view.findViewById<Button>(R.id.btnLoginSubmit).setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = UserManager.login(username, password)
            if (user != null) {
                val bundle = Bundle().apply {
                    putString("username", username)
                }

                findNavController().navigate(R.id.action_fragmentC_to_fragmentD, bundle)
            } else {
                Toast.makeText(requireContext(), "Niepoprawny login lub hasło", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<Button>(R.id.btnNavigateToRegister).setOnClickListener {
            findNavController().navigate(R.id.action_fragmentC_to_fragmentB)
        }
    }
}
