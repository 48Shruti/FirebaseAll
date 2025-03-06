package com.shruti.firebaseall

import android.app.Dialog
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import com.shruti.firebaseall.databinding.ActivityRealTimeBinding
import com.shruti.firebaseall.databinding.CustomDialogBinding
import java.util.ArrayList

class RealTimeActivity : AppCompatActivity() {
    lateinit var binding : ActivityRealTimeBinding
    lateinit var item : ArrayList<StudentDataClass>
    lateinit var adapter : StudentAdapter
    var database : DatabaseReference = FirebaseDatabase.getInstance().getReference("student")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        database.addChildEventListener(object : ChildEventListener{
//            override fun onChildAdded(snapshot : DataSnapshot, previousChildName: String?) {
//                val menuModel : StudentDataClass ?= snapshot.getValue(StudentDataClass::class.java)
//                    menuModel?.id  = snapshot.key
//                if (menuModel != null){
//                    item.add(menuModel)
//                    adapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//                val menuModel : StudentDataClass ?= snapshot.getValue(StudentDataClass::class.java)
//                menuModel?.id  = snapshot.key
//                if (menuModel != null){
//                    item.forEachIndexed{index,studentData->
//                        if (studentData.id == menuModel.id){
//                            item[index] = menuModel
//                            return@forEachIndexed
//                        }
//
//                    }
//                    adapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onChildRemoved(snapshot: DataSnapshot) {
//
//                val menuModel : StudentDataClass ?= snapshot.getValue(StudentDataClass::class.java)
//                menuModel?.id  = snapshot.key
//                if (menuModel != null){
//                    item.remove(menuModel)
//                    adapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//        binding.fab.setOnClickListener {
//            var dialog = Dialog(this)
//            var dialogBinding = CustomDialogBinding.inflate(layoutInflater)
//            dialog.setContentView(dialogBinding.root)
//            val name = dialogBinding.etName.text.toString().trim()
//            val classStudent = dialogBinding.etClass.text.toString().trim()
//
//            val student = StudentDataClass(name,classStudent)
//            dialogBinding.btnSubmit.setOnClickListener {
//                if (dialogBinding.etName.text.isNullOrEmpty()){
//                    dialogBinding.etName.error = "Enter name"
//                }else if (dialogBinding.etClass.text.isNullOrEmpty()){
//                    dialogBinding.etClass.error = "Enter class"
//                }else{
//                    database.child("employee").child("1").setValue(student)
//                        .addOnSuccessListener {
//                            Toast.makeText(this,"Successfully",Toast.LENGTH_SHORT).show()
//                            dialog.dismiss()
//                        }
//                        .addOnFailureListener {
//                            Toast.makeText(this,"failure",Toast.LENGTH_SHORT).show()
//                        }
//                }
//            }
//            dialog.show()
//        }
//
//    }
    }
}