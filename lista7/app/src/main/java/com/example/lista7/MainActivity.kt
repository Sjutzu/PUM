package com.example.lista7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lista7.ui.theme.Lista7Theme
import com.example.lista7.view.StudentDetailsView
import com.example.lista7.view.StudentListView
import com.example.lista7.viewmodel.StudentViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        setContent {
            Lista7Theme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "studentList") {
                    composable("studentList") {
                        StudentListView(
                            studentViewModel = studentViewModel,
                            navController = navController
                        )
                    }
                    composable("studentDetails/{index}") { backStackEntry ->
                        val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
                        index?.let {
                            StudentDetailsView(studentIndex = it, studentViewModel = studentViewModel)
                        }
                    }
                }
            }
        }
    }
}
