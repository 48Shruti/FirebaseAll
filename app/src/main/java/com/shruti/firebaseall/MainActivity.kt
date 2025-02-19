package com.shruti.firebaseall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shruti.firebaseall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCrash.setOnClickListener {
            throw RuntimeException("Test crash")
        }
    }
}