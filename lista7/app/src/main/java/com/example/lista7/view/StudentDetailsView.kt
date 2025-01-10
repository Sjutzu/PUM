package com.example.lista7.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lista7.viewmodel.StudentViewModel

@Composable
fun StudentDetailsView(
    modifier: Modifier = Modifier,
    studentIndex: Int,
    studentViewModel: StudentViewModel
) {
    val student = studentViewModel.students.value.find { it.index == studentIndex }

    if (student != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${student.index} ${student.name} ${student.surname}",
                modifier = Modifier.padding(top = 50.dp, bottom = 16.dp),
                style = TextStyle(fontSize = 26.sp)
            )

            Text(text = "Indeks: ${student.index}")
            Text(text = "Imię: ${student.name}")
            Text(text = "Nazwisko: ${student.surname}")
            Text(text = "Średnia ocen: ${student.grades}")
            Text(text = "Rok studiów: ${student.year}")
        }
    } else {
        Text(text = "Student not found")
    }
}