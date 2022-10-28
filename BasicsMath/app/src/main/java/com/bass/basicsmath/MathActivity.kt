package com.bass.basicsmath

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bass.basicsmath.databinding.ActivityMathBinding
import java.util.*
import kotlin.random.Random

class MathActivity : AppCompatActivity() {

    private var correctAnswer = 0
    private var userScore = 0
    private var userLife = 3

    private lateinit var timer: CountDownTimer
    private val startTimerInMilli: Long = 10000
    private var timeLeft: Long = startTimerInMilli

    private lateinit var binding: ActivityMathBinding

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameContinue()

        binding.btnOk.setOnClickListener {
            val input = binding.reply.text.toString()
            if (input == "") {
                Toast.makeText(applicationContext, "Введите свой ответ !!", Toast.LENGTH_SHORT).show()
            } else {
                pauseTimer()

                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer) {
                    userScore += 10
                    binding.task.text = "Поздравляю! Ваш ответ правильный!"
                    binding.score.text = userScore.toString()
                } else {

                    if(userLife <= 0 ) {
                        val intent = Intent(this@MathActivity, ResultActivity::class.java)
                        intent.putExtra("score",userScore)
                        startActivity(intent)
                        finish()
                    } else {
                        userLife--
                        binding.task.text = "Извините, но ваш ответ неверен!"
                        binding.life.text = userLife.toString()
                    }
                }

            }
        }

        binding.btnNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            binding.reply.setText("")

            if (userLife == 0) {
                Toast.makeText(applicationContext, "Игра окончена!!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MathActivity, ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            } else if (userScore == 100) {
                Toast.makeText(applicationContext, "Вы победили !", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MathActivity, ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            } else {
                gameContinue()
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun gameContinue(){

        val key = intent.getIntExtra("key",0)
        val getKey: Int = key

        val num1 = Random.nextInt(0,100)
        val num2 = Random.nextInt(0,100)

        if (getKey == 1){
            binding.task.text = "$num1 + $num2"
            correctAnswer = num1 + num2
        } else if (getKey == 2) {
            if (num1 >= num2){
                binding.task.text = "$num1 - $num2"
                correctAnswer = num1 - num2
                } else {
                binding.task.text = "$num2 - $num1"
                correctAnswer = num2 - num1
            }
        } else if (getKey == 3) {
            binding.task.text = "$num1 * $num2"
            correctAnswer = num1 * num2
        } else if (getKey == 4) {
            if (num1 >= num2){
                binding.task.text = "$num1 / $num2"
                correctAnswer = num1 / num2
            } else {
                binding.task.text = "$num2 / $num1"
                correctAnswer = num2 / num1
            }
        }

        startTimer()
    }

    private fun startTimer(){
        timer = object: CountDownTimer(timeLeft,1000){

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateText()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLife--

                binding.task.text = "Время вышло!"
                binding.life.text = userLife.toString()
            }

        }.start()
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {

        timeLeft = startTimerInMilli
        updateText()
    }

    fun updateText() {
        val remainingTime: Int = (timeLeft/1000).toInt()
        binding.time.text = String.format(Locale.getDefault(),"%02d",remainingTime)
    }
}