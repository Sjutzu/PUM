package com.example.lista6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lista6.ui.theme.Lista6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun E1Screen(navController: NavHostController) {
    val exerciseLists = remember {
        DataStorage.exerciseLists ?: DummyDataGenerator.generateExerciseLists().also {
            DataStorage.exerciseLists = it
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Moje listy zadań",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                ) {
                    Text(
                        text = "Moje listy zadań",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 100.dp)
                ) {
                    items(exerciseLists) { exerciseList ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(Color.Gray.copy(alpha = 0.1f))
                                .padding(16.dp)
                        ) {
                            ExerciseListItem(exerciseList = exerciseList) {
                                navController.navigate(Screens.E3.createRoute(it.id))
                            }
                        }
                    }
                }
            }
        }
    )
}

data class Exercise(
    val content: String,
    val points: Int,
    val index: Int
)

data class Subject(
    val name: String
)

data class ExerciseList(
    val exercises: List<Exercise>,
    val subject: Subject,
    val grade: Double,
    val listNumber: String,
    val id: Int
)

object DummyDataGenerator {
    private val subjects = listOf(
        Subject("Matematyka"),
        Subject("PUM"),
        Subject("Fizyka"),
        Subject("Elektronika"),
        Subject("Algorytmy")
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
        var id2 = 0
        val subjectGrades = mutableMapOf<String, MutableList<Double>>() // Mapa do zbierania ocen dla każdego przedmiotu
        val subjectTaskCounts = mutableMapOf<String, Int>() // Mapa do zliczania liczby list zadań dla każdego przedmiotu

        return List(20) { index ->
            val subject = subjects.random()
            id2++
            val listNumber = subjectCounters.getOrDefault(subject.name, 0) + 1
            subjectCounters[subject.name] = listNumber

            val numTasks = (1..10).random()
            val exercises = List(numTasks) { taskIndex ->
                Exercise(
                    content = taskContents.random(),
                    points = (1..10).random(),
                    index = taskIndex + 1
                )
            }
            val grade = listOf(3.0, 4.0, 4.5).random() + listOf(0.0, 0.5).random()

            // Dodajemy ocenę do mapy ocen
            subjectGrades.computeIfAbsent(subject.name) { mutableListOf() }.add(grade)
            // Zliczamy listy zadań dla każdego przedmiotu
            subjectTaskCounts[subject.name] = subjectTaskCounts.getOrDefault(subject.name, 0) + 1

            ExerciseList(
                exercises = exercises,
                subject = subject,
                grade = grade,
                listNumber = "Lista $listNumber",
                id = id2
            )
        }.also {
            // Po wygenerowaniu listy, obliczamy średnią dla każdego przedmiotu
            DataStorage.subjectsWithGrades = subjectGrades.map { (subjectName, grades) ->
                val averageGrade = grades.average()
                val taskCount = subjectTaskCounts[subjectName] ?: 0
                Triple(subjectName, averageGrade, taskCount) // Dodajemy również licznik list
            }
        }
    }

}

object DataStorage {
    var exerciseLists: List<ExerciseList>? = null
    var subjectsWithGrades: List<Triple<String, Double, Int>>? = null
}

@Composable
fun ExerciseListItem(exerciseList: ExerciseList, onClick: (ExerciseList) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(exerciseList) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = exerciseList.subject.name,
                    fontSize = 20.sp
                )
                Text(
                    text = exerciseList.listNumber,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Liczba zadań: ${exerciseList.exercises.size}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Ocena: ${exerciseList.grade}",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun E2Screen() {
    // Lista przedmiotów z ocenami i liczbą list
    val subjectsWithGrades = remember {
        DataStorage.subjectsWithGrades?.sortedBy { it.first } ?: emptyList() // Sortowanie alfabetycznie
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Moje Oceny",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(bottom = 20.dp),
                        textAlign = TextAlign.Center // Wyśrodkowanie tytułu
                    )
                },
                modifier = Modifier.padding(top = 20.dp) // Dodatkowy odstęp od góry, jeśli potrzebny
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                items(subjectsWithGrades) { (subject, averageGrade, taskCount) ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color.Gray.copy(alpha = 0.1f))  // Tło dla każdego elementu
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = subject,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Średnia: ${"%.2f".format(averageGrade)}",
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Liczba list: $taskCount",
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun E3Screen(id: Int) {
    val exerciseLists = DataStorage.exerciseLists ?: emptyList()

    val selectedList = exerciseLists.find { it.id == id }
    var subjectName = ""
    var headerTitle = ""
    if (selectedList != null) {
        subjectName = selectedList.subject.name
        headerTitle = "$subjectName\n${selectedList.listNumber}"
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = headerTitle,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 35.dp)

                    )

                },
                modifier = Modifier.height(150.dp)

            )
        },
        content = { paddingValues ->
            if (selectedList != null) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp)
                    .padding(paddingValues)) {
                    LazyColumn(modifier = Modifier.padding(16.dp)) {
                        items(selectedList.exercises) { exercise ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))  // Tło dla każdego elementu
                                    .padding(16.dp)
                            ) {
                                ExerciseItem(exercise = exercise)
                            }
                        }
                    }
                }
            } else {
                // Jeśli nie znaleziono listy
                Text(
                    text = "Nie znaleziono listy o numerze $id.",
                    fontSize = 20.sp,
                )
            }
        }
    )
}

// Komponent dla pojedynczego ćwiczenia
@Composable
fun ExerciseItem(exercise: Exercise) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray.copy(alpha = 0.1f))  // Tło dla każdego elementu
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Punkty wyświetlane całkowicie po prawej
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End // Wypchnięcie punktów na prawą stronę
            ) {
                Text(
                    text = "Pkt: ${exercise.points}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            // Treść zadania
            Text(
                text = "Zadanie ${exercise.index}",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Wyświetlanie treści zadania
            Text(
                text = exercise.content,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )


        }
    }
}


sealed class Screens(val route: String) {
    data object E1 : Screens("home")
    data object E2 : Screens("first")
    data object E3 : Screens("second/{id}") {
        fun createRoute(id: Int) = "second/$id"
    }
}


sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBar(Screens.E1.route, "Listy zadań", Icons.Default.Home)
    data object First : BottomBar(Screens.E2.route, "Oceny", Icons.Default.Info)

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = { BottomNavGraph(navController = navController) }
    )
}

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.E1.route
    ) {
        composable(route = Screens.E1.route) { E1Screen(navController) }
        composable(route = Screens.E2.route) { E2Screen() }
        composable(
            route = Screens.E3.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })  // Oczekujemy argumentu id
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            E3Screen(id = id)
        }
    }
}

@Composable
fun BottomMenu(navController: NavHostController){
    val screens = listOf(
        BottomBar.Home, BottomBar.First
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                label = { Text(text = screen.title) },
                icon = { Icon(imageVector = screen.icon, contentDescription = "icon") },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { navController.navigate(screen.route) }
            )
        }
    }
}


