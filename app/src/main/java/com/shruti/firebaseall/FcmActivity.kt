package com.shruti.firebaseall

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.shruti.firebaseall.databinding.ActivityFcmBinding

class FcmActivity : AppCompatActivity() {
    lateinit var binding : ActivityFcmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFcmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        FirebaseMessaging.getInstance().token.addOnCompleteListener{task->
            if (!task.isSuccessful){
                Log.w("Cloud Messaging","failed for cloud messaging",task.exception)
                return@addOnCompleteListener
            }
            val token = task.result
            Log.d("Clound Messaging", token)

        }
    }
}
