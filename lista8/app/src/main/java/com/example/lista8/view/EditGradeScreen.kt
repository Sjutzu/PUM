package com.example.lista8.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lista8.model.Grade
import com.example.lista8.viewmodel.GradeViewModel

@Composable
fun EditGradeScreen(navController: NavController, viewModel: GradeViewModel, gradeId: Int) {
    val grades by viewModel.allGrades.collectAsState(initial = emptyList())
    val gradeToEdit = grades.find { it.id == gradeId }

    if (gradeToEdit != null) {
        var subject by remember { mutableStateOf(gradeToEdit.subject) }
        var grade by remember { mutableStateOf(gradeToEdit.grade.toString()) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween // Ensure buttons are at the bottom
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Edytuj",
                    fontSize = 32.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                TextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Nazwa Przedmiotu") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = grade,
                    onValueChange = { grade = it },
                    label = { Text("Ocena") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        viewModel.addGrade(Grade(id = gradeToEdit.id, subject = subject, grade = grade.toFloat()))
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) {
                    Text(text = "Zaktualizuj")
                }
                Button(
                    onClick = {
                        viewModel.deleteGrade(gradeToEdit)
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Usuń")
                }
            }
        }
    }
}
