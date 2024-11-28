package com.example.l3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.l3.databinding.ItemTaskBinding

class TaskListAdapter(
    private val tasks: List<ExerciseList>,
    private val onClick: (ExerciseList) -> Unit
) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.subjectName.text = task.subject.name
        holder.binding.taskCount.text = "Zadań: ${task.exercises.size}"
        holder.binding.grade.text = "Ocena: ${task.grade}"
        holder.binding.listNumber.text = task.listNumber

        holder.binding.root.setOnClickListener { onClick(task) }
    }

    override fun getItemCount(): Int = tasks.size
}
