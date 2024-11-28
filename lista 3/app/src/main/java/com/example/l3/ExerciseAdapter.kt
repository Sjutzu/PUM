package com.example.l3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(
    private val exercises: List<Exercise>
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    // ViewHolder: odpowiada za odnajdywanie widoków w layoucie
    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.exercise_title)
        val contentTextView: TextView = itemView.findViewById(R.id.exercise_content)
        val pointsTextView: TextView = itemView.findViewById(R.id.exercise_points)
    }

    // Inflater layoutu elementu listy
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    // Wiązanie danych z widokami
    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]

        // Ustawienie tytułu zadania (np. "Zadanie 1:")
        holder.titleTextView.text = "Zadanie ${position + 1}:"

        // Ustawienie treści zadania
        holder.contentTextView.text = exercise.content

        // Ustawienie punktów w prawym górnym rogu
        holder.pointsTextView.text = "Punkty: ${exercise.points}"
    }

    // Liczba elementów w liście
    override fun getItemCount(): Int = exercises.size
}
