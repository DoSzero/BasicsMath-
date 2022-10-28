package com.bass.basicsmath

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bass.basicsmath.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scoreFeedback()

        binding.playAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.quit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }

    @SuppressLint("SetTextI18n")
    fun scoreFeedback(){
        val score = intent.getIntExtra("score",0)
        binding.tvResult.text = "$score"

        if (score == 100) {
            binding.feedBack.text = "Поздравляю!\n" + "Отличный результат !!"
        } else if (score < 100 && score > 50) {
            binding.feedBack.text = "Поздравляю!!\n" + "Ты хорош!"
        }
    }
}