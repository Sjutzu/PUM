package com.example.lista4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizScreen()
        }
    }
}

@Composable
fun QuizScreen() {
    val questions = listOf(
        QuizQuestion("Jakie zwierzę jest największym ssakiem na świecie?", listOf("Orangutan", "Płetwal błękitny", "Rekin Wielorybi", "Żarłacz biały"), 1),
        QuizQuestion("Jaki kolor powstanie z połączenia niebieskiego i żółtego?", listOf("Zielony", "Czerwony", "Fioletowy", "Czarny"), 0),
        QuizQuestion("Współczesna stolica Rzeczpospolitej Polski (RP) to:", listOf("Gniezno", "Kraków", "Sosnowiec", "Warszawa"), 3),
        QuizQuestion("Który z tych majonezów dekoracyjnych jest najlepszy? ", listOf("Kielecki", "Winiary", "Pudliszki", "Hellmans"), 0),
        QuizQuestion("Podaj datę/rok, kiedy odbyła się bitwa pod Grunwaldem?", listOf("1939", "1914", "1410", "1014"), 2),
        QuizQuestion("W którym roku Polska wstąpiła do Unii Europejskiej?", listOf("1994", "2004", "1989", "2002"), 1),
        QuizQuestion("Ile wynosi suma kątów wewnętrznych w trójkącie?", listOf("90", "180", "360", "720"), 1),
        QuizQuestion("Które z podanych państw jest położone na Półwyspie Bałkańskim?", listOf("Albania", "Demokratyczna Republika Konga", "Atlantyda", "USA"), 0),
        QuizQuestion("Jaki Polski król widnieje na banknocie o wartości 20 zł?", listOf("Stanisław August Poniatowski", "Bolesław Chrobry", "Władysław Jagiełło", "Zygmunt III Waza"), 1),
        QuizQuestion("Czyjego autorstwa jest lektura szkolna - powieść Quo Vadis?", listOf("H. Sienkiewicz", "A. Mickiewicz", "J. Kochanowski", "J. Tuwim"), 0)
    )

    var questionIndex by remember { mutableIntStateOf(0) }
    var points by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableIntStateOf(-1) }

    if (questionIndex < questions.size) {
        val question = questions[questionIndex]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Pytanie ${questionIndex + 1} / ${questions.size}")
            Text(text = question.text)

            question.answers.forEachIndexed { index, answer ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    RadioButton(
                        selected = selectedAnswer == index,
                        onClick = { selectedAnswer = index }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = answer)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (selectedAnswer == question.correctAnswerIndex) points++
                    selectedAnswer = -1
                    questionIndex++
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Dalej")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = { (questionIndex + 1).toFloat() / questions.size },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    } else {
        ResultScreen(points = points, totalQuestions = questions.size)
    }
}

@Composable
fun ResultScreen(points: Int, totalQuestions: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Koniec Quizu!")
        Text(text = "Zdobyłeś ${points * 10} punktów z ${totalQuestions * 10}.")
    }
}

data class QuizQuestion(
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
