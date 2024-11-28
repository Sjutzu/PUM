package com.example.l3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class E2 : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScoreListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_e2, container, false)
        setupRecyclerView(view)
        return view
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_scores)

        // Pobieramy dane z DataStorage (zakładając, że zostały już wygenerowane w E1)
        val exerciseLists = DataStorage.exerciseLists

        // Jeśli dane istnieją, przetwarzamy je
        if (exerciseLists != null) {
            // Grupowanie danych według przedmiotu
            val groupedData = exerciseLists
                .groupBy { it.subject.name }
                .map { (subject, lists) ->
                    // Obliczamy średnią ocen z wszystkich list danego przedmiotu
                    val allGrades = lists.map { it.grade }
                    val averageGrade = if (allGrades.isNotEmpty()) allGrades.average() else 0.0

                    // Liczymy unikalne numery list
                    val listCount = lists.map { it.listNumber }.distinct().size

                    // Przekazujemy do adaptera
                    ScoreListAdapter.SubjectScoreData(subject, averageGrade, listCount)
                }

            // Inicjalizowanie adaptera z przetworzonymi danymi
            adapter = ScoreListAdapter(groupedData)
            recyclerView.apply {
                adapter = this@E2.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}

