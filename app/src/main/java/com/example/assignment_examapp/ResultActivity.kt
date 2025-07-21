package com.example.assignment_examapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment_examapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding : ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        my approach , so that this could be used in multiple activies easily
        binding.textView2.text = MainActivity.marksCounter.toString() // reason why we made it a companion object
    // could be bring through intent passing too
        val result = intent.getIntExtra("marksScored",0)
        binding.textView2.text = result.toString()

        binding.textView2.text = MainActivity.marksCounter.toString() // reason why we made it a companion object
    }
}