package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class FragmentB : Fragment(R.layout.fragment_b) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnSubmitRegister).setOnClickListener {


            val usernameEditText = view.findViewById<EditText>(R.id.etRegisterUsername)
            val passwordEditText = view.findViewById<EditText>(R.id.etRegisterPassword)
            val confirmPasswordEditText = view.findViewById<EditText>(R.id.etRegisterConfirmPassword)

            view.findViewById<Button>(R.id.btnSubmitRegister).setOnClickListener {
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                val confirmPassword = confirmPasswordEditText.text.toString()

                if (password != confirmPassword) {
                    Toast.makeText(requireContext(), "Hasła muszą być identyczne.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val existingUser = UserManager.getUsers().find { it.username == username }

                if (existingUser == null) {
                    val newUser = User(username, password)
                    UserManager.addUser(newUser)
                    Toast.makeText(requireContext(), "Rejestracja udana. Możesz się zalogować.", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_fragmentB_to_fragmentC)
                } else {
                    Toast.makeText(requireContext(), "Użytkownik o tym loginie już istnieje.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        view.findViewById<Button>(R.id.btnBackToMain).setOnClickListener {
            findNavController().navigate(R.id.action_fragmentB_to_fragmentA)
        }
    }
}

