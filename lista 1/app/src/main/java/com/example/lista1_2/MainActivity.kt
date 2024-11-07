package com.example.lista1_2

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lista1_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var questionIndex = 0
    private var points = 0
    //list of questions
    private val questions = listOf(
        QuizQuestion("Jakie zwierzę jest największym ssakiem na świecie?", listOf("Orangutan", "Płetwal błękitny", "Rekin Wielorybi", "Żarłacz biały"), 1),
        QuizQuestion("Jaki kolor powstanie z połączenia niebieskiego i żółtego?", listOf("Zielony", "Czerwony", "Fioletowy", "Czarny"), 0),
        QuizQuestion("Współczesna stolica Rzeczpospolitej Polski (RP) to:", listOf("Gniezno", "Kraków", "Sosnowiec", "Warszawa"), 3),
        QuizQuestion("Który z tych majonezów dekoracyjnych jest najlepszy? ", listOf("Kielecki", "Winiary", "Pudliszki", "Hellmans"), 0),
        QuizQuestion("Podaj datę/rok, kiedy odbyła się bitwa pod Grunwaldem?", listOf("1939", "1914", "1410", "1014"), 2),
        QuizQuestion("W którym roku Polska wstąpiła do Unii Europejskiej?", listOf("1994", "2004", "1989", "2002"), 1),
        QuizQuestion("Ile wynonsi suma kątów wewnętrznych w trójkącie?", listOf("90", "180", "360", "720"), 1),
        QuizQuestion("Które z podanych państw jest położone na Półwyspie Bałkańskim?   ", listOf("Albania", "Demokratyczna Republika Konga", "Atlantyda", "USA"), 0),
        QuizQuestion("Jaki Polski król widnieje na banknocie o wartośći 20 zł?", listOf("Stanisław August Poniatowski", "Bolesław Chrobry", "Władysław Jagiełło", "Zygmunt III Waza"), 1),
        QuizQuestion("Czyjego autorstwa jest lektura szkolna - powieśc Quo Vadis?", listOf("H. Sienkiewicz", "A. Mickiewicz", "J. Kochanowski", "J. Tuwim"), 0)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
        binding.button2.setOnClickListener{
            check()
            next()
        }
    }

    private fun setup(){
        showQuestion()
        updateProgress()
    }

    private fun showQuestion(){
        val question = questions[questionIndex]
        binding.questionNum.text = "Pytanie ${questionIndex + 1}/${questions.size}"
        binding.question.text = question.text

        // Wyświetlanie odpowiedzi
        binding.answer1.text = question.answers[0]
        binding.answer2.text = question.answers[1]
        binding.answer3.text = question.answers[2]
        binding.answer4.text = question.answers[3]

        // Czyszczenie wyboru użytkownika
        binding.answerGroup.clearCheck()
    }

    private fun check(){
        val selectedAnswerId = binding.answerGroup.checkedRadioButtonId
        if (selectedAnswerId != -1) {
            val selectedAnswerIndex = when (selectedAnswerId) {
                R.id.answer1 -> 0
                R.id.answer2 -> 1
                R.id.answer3 -> 2
                R.id.answer4 -> 3
                else -> -1
            }
            if (selectedAnswerIndex == questions[questionIndex].correctAnswerIndex) {
                points++
            }
        }
    }

    private fun updateProgress() {
        val progress = ((questionIndex + 1).toDouble() / questions.size * 100).toInt()
        binding.progressBar.progress = progress
    }

    private fun next() {
        questionIndex++
        if (questionIndex < questions.size) {
            setup()
        } else {
            end()
        }
    }

    private fun end() {
        binding.startScreenLayout.visibility = View.GONE
        binding.endScreenLayout.visibility = View.VISIBLE
        binding.scoreText.text = "Zdobyłeś ${points*10} pkt"
    }


}
//class which define the structure of question
data class QuizQuestion(
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)