package com.bass.basicsmath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bass.basicsmath.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private var keyAddition = 1
    private var keySubtraction = 2
    private var keyMultiplication = 3
    private var keyDivide = 4

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addition.setOnClickListener {
            val intent = Intent(this@MenuActivity, MathActivity::class.java)
            intent.putExtra("key",keyAddition)
            startActivity(intent)
        }

        binding.subtraction.setOnClickListener {
            val intent = Intent(this@MenuActivity, MathActivity::class.java)
            intent.putExtra("key",keySubtraction)
            startActivity(intent)
        }

        binding.multiplication.setOnClickListener {
            val intent = Intent(this@MenuActivity, MathActivity::class.java)
            intent.putExtra("key",keyMultiplication)
            startActivity(intent)
        }

        binding.divide.setOnClickListener {
            val intent = Intent(this@MenuActivity, MathActivity::class.java)
            intent.putExtra("key",keyDivide)
            startActivity(intent)
        }

    }
}