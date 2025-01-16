package com.example.lista8.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lista8.viewmodel.GradeViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lista8.Screen

@SuppressLint("DefaultLocale")
@Composable
fun GradeListScreen(navController: NavController, viewModel: GradeViewModel) {
    val grades by viewModel.allGrades.collectAsState(initial = emptyList())
    val averageGrade = if (grades.isNotEmpty()) grades.map { it.grade }.average() else 0.0

    Column(
        modifier = Modifier.fillMaxSize() // Ensure the column takes full height
    ) {
        Text(
            text = "Moje Oceny",
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.Black,
            textAlign = TextAlign.Center // Center the text
        )
        LazyColumn(
            modifier = Modifier.weight(1f) // Let the LazyColumn take available space
        ) {
            items(grades) { grade ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp) // Add spacing between items
                        .background(Color.LightGray) // Add background color
                        .padding(16.dp) // Inner padding for content
                        .clickable {
                            navController.navigate(Screen.EditGrade.createRoute(grade.id))
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = grade.subject, fontSize = 18.sp, modifier = Modifier.weight(1f))
                    Text(text = grade.grade.toString(), fontSize = 18.sp, modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp,80.dp,8.dp,8.dp)
                .background(Color.LightGray)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Średnia Ocen", fontSize = 18.sp)
            Text(text = String.format("%.2f", averageGrade), fontSize = 18.sp)
        }
        Button(
            onClick = { navController.navigate(Screen.AddGrade.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 10.dp) // Ensure 10.dp padding from the bottom
        ) {
            Text(text = "NOWY")
        }
    }
}