package com.example.lista7.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lista7.model.Student

class StudentViewModel : ViewModel() {
    val students = mutableStateOf(
        listOf(
            Student(341232, "Jan", "Kowalski", 4.0f, 2),
            Student(340222, "Anna", "Nowak", 4.5f, 3),
            Student(340322, "Piotr", "Zieliński", 3.8f, 1),
            Student(333211, "Krzysztof", "Grod", 3.8f, 1),
            Student(333965, "Anrdriej", "Dudu", 4.0f, 3),
            Student(345435, "Anna", "Nowakowska", 4.5f, 1),
            Student(354111, "Piotr", "Szczerba", 3.8f, 2),
            Student(354321, "Maciej", "Rodwiński", 3.8f, 3),
            Student(312345, "Justyna", "Lewankowska", 4.0f, 1),
            Student(367890, "Noemi", "Pudliszki", 4.5f, 1),
            Student(334321, "Alan", "Monster", 3.8f, 1),
            Student(308321, "Tiger", "Bonzo", 3.8f, 3),
            Student(392345, "Jan", "Ślimak", 4.0f, 3),
            Student(367898, "Daria", "Rosikiewicz", 4.5f, 2),
            Student(354326, "Ola", "Lawenda", 3.8f, 2),
            Student(354541, "Ninel", "Komuch", 3.8f, 1),
            Student(322245, "Monitor", "Infor", 4.0f, 2),
            Student(333330, "Daniel", "Cygan", 4.5f, 2),
            Student(300001, "Cezary", "Kogut", 3.8f, 3),
            Student(300024, "Piotr", "Zieliński", 3.8f, 2)
        )
    )
}