package com.shruti.firebaseall

import android.content.Intent
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
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnReal.setOnClickListener {
            val intent = Intent(this,RealTimeActivity::class.java)
            startActivity(intent)
        }
        binding.btnFirestore.setOnClickListener {
            val intent = Intent(this, FirestoreActivity::class.java)
            startActivity(intent)
        }
    }
}