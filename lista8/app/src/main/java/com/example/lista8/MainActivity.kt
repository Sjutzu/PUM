package com.example.lista8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lista8.ui.theme.Lista8Theme
import com.example.lista8.view.AddGradeScreen
import com.example.lista8.view.EditGradeScreen
import com.example.lista8.view.GradeListScreen
import com.example.lista8.viewmodel.GradeViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeApp()
        }
    }
}


annotation class AndroidEntryPoint

sealed class Screen(val route: String) {
    object GradeList : Screen("grade_list")
    object AddGrade : Screen("add_grade")
    object EditGrade : Screen("edit_grade/{gradeId}") {
        fun createRoute(gradeId: Int) = "edit_grade/$gradeId"
    }
}

@Composable
fun GradeApp() {
    val viewModel: GradeViewModel = viewModel() // Tworzenie ViewModel w kontekście Composable
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.GradeList.route
    ) {
        composable(Screen.GradeList.route) {
            GradeListScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.AddGrade.route) {
            AddGradeScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.EditGrade.route) { backStackEntry ->
            val gradeId = backStackEntry.arguments?.getString("gradeId")?.toIntOrNull() ?: 0
            EditGradeScreen(navController = navController, viewModel = viewModel, gradeId = gradeId)
        }
    }
}


