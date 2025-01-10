package com.example.lista7.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lista7.model.Student
import com.example.lista7.viewmodel.StudentViewModel

@Composable
fun StudentListView(
    modifier: Modifier = Modifier,
    studentViewModel: StudentViewModel,
    navController: NavController
) {

    val students = studentViewModel.students.value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista Studentów",
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
            style = TextStyle(fontSize = 32.sp)
        )
        LazyColumn {
            items(students) { student ->
                StudentItem(student = student, navController = navController)
            }
        }
    }
}

@Composable
fun StudentItem(student: Student, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable {
                navController.navigate("studentDetails/${student.index}")
            }
    ) {
        Surface(modifier = Modifier.padding(16.dp)) {
            Text(text = "${student.index} ${student.name} ${student.surname}")
        }
    }
}
