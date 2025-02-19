package com.shruti.firebaseall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.shruti.firebaseall.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            if (binding.etEmail.text.isNullOrEmpty()){
                binding.etEmail.error = "Enter email"
            }else if (binding.etPassword.text.isNullOrEmpty()){
                binding.etPassword.error = "Enter password"
            }else{
                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){task->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Login Succeed",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}