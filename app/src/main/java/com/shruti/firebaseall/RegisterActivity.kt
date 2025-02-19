package com.shruti.firebaseall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.shruti.firebaseall.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            if (binding.etEmail.text.isNullOrEmpty()){
                binding.etEmail.error = "Enter email"
            }else if (binding.etPassword.text.isNullOrEmpty()){
                binding.etPassword.error = "Enter password"
            }else {
                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){task->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Register Succeed", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Register failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}