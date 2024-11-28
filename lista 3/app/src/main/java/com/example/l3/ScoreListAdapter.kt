package com.example.l3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScoreListAdapter(private val scores: List<SubjectScoreData>) : RecyclerView.Adapter<ScoreListAdapter.ViewHolder>() {

    data class SubjectScoreData(val subject: String, val averageScore: Double, val listCount: Int)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectTextView: TextView = itemView.findViewById(R.id.subject_name)
        val averageScoreTextView: TextView = itemView.findViewById(R.id.average_score)
        val listCountTextView: TextView = itemView.findViewById(R.id.list_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = scores[position]
        holder.subjectTextView.text = data.subject
        holder.averageScoreTextView.text = holder.itemView.context.getString(R.string.average_score_text, data.averageScore)
        holder.listCountTextView.text = "Liczba list: ${data.listCount}"
    }

    override fun getItemCount(): Int {
        return scores.size
    }
}



