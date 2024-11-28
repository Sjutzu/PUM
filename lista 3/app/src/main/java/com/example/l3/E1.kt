package com.example.l3

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize

class E1 : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskListAdapter
    private val exerciseLists: List<ExerciseList> by lazy {
        if (DataStorage.exerciseLists == null) {
            DataStorage.exerciseLists = DummyDataGenerator.generateExerciseLists()
        }
        DataStorage.exerciseLists ?: emptyList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_e1, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_tasks)

        adapter = TaskListAdapter(exerciseLists) { selectedList ->
            val action = E1Directions.actionE1ToE3(selectedList)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }
}

@Parcelize
data class Exercise(
    val content: String,
    val points: Int
) : Parcelable

@Parcelize
data class Subject(
    val name: String
) : Parcelable

@Parcelize
data class ExerciseList(
    val exercises: List<Exercise>,
    val subject: Subject,
    val grade: Double,
    val listNumber: String
) : Parcelable

object DummyDataGenerator {
    private val subjects = listOf(
        Subject("MATEMATYKA"),
        Subject("PUM"),
        Subject("FIZYKA"),
        Subject("ELEKTRONIKA"),
        Subject("ALGORYTMY")
    )
    private val taskContents = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Praesent vulputate leo eget ullamcorper fermentum.",
        "Vestibulum at urna vitae risus bibendum aliquet.",
        "Curabitur bibendum mauris non erat sodales finibus.",
        "Quisque at risus faucibus, fermentum ex quis, lobortis elit.",
        "Aenean cursus nisi a velit suscipit accumsan.",
        "In non felis ac velit placerat viverra id eu sem.",
        "Morbi lobortis magna id nulla rhoncus, at tristique odio egestas.",
        "Nullam at libero sit amet elit lacinia egestas.",
        "Duis sit amet eros vitae magna congue venenatis et in sem.",
        "Sed tempor justo eu imperdiet semper.",
        "Sed gravida nisi vel venenatis tincidunt.",
        "Suspendisse non nisi ut orci elementum venenatis.",
        "Morbi congue est nec mauris ultrices vestibulum.",
        "Fusce fringilla neque ac bibendum pharetra.",
        "Phasellus facilisis magna quis urna euismod, sit amet condimentum mi egestas.",
        "Etiam faucibus sem et maximus scelerisque.",
        "Donec tincidunt quam a aliquam laoreet.",
        "Cras nec odio non sem dignissim blandit."
    )

    fun generateExerciseLists(): List<ExerciseList> {
        val subjectCounters = mutableMapOf<String, Int>()

        return List(20) { index ->
            val subject = subjects.random()

            val listNumber = subjectCounters.getOrDefault(subject.name, 0) + 1
            subjectCounters[subject.name] = listNumber

            val numTasks = (1..10).random()
            val exercises = List(numTasks) { taskIndex ->
                Exercise(
                    content = taskContents.random(),
                    points = (1..10).random()
                )
            }
            ExerciseList(
                exercises = exercises,
                subject = subject,
                grade = listOf(3.0, 4.0, 4.5).random() + listOf(0.0, 0.5).random(),
                listNumber = "Lista $listNumber"
            )
        }
    }
}

object DataStorage {
    var exerciseLists: List<ExerciseList>? = null
}
