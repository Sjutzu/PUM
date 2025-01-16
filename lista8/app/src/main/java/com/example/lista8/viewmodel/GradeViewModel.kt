package com.example.lista8.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lista8.model.Grade
import com.example.lista8.model.GradeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GradeViewModel(application: Application) : AndroidViewModel(application) {
    private val gradeDao = GradeDatabase.getDatabase(application).gradeDao()

    val allGrades: Flow<List<Grade>> = gradeDao.getAllGrades()

    fun addGrade(grade: Grade) {
        viewModelScope.launch(Dispatchers.IO) {
            gradeDao.insertGrade(grade)
        }
    }

    fun deleteGrade(grade: Grade) {
        viewModelScope.launch(Dispatchers.IO) {
            gradeDao.deleteGrade(grade)
        }
    }
}
