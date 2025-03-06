package com.shruti.firebaseall

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams
import android.widget.Toast
import androidx.privacysandbox.tools.core.model.Type
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.core.View.DocumentChanges
import com.google.firebase.firestore.remote.WatchChange
import com.google.firestore.v1.DocumentChange
import com.shruti.firebaseall.databinding.ActivityFirestoreBinding
import com.shruti.firebaseall.databinding.CustomDialogBinding
import org.w3c.dom.Document
import java.util.ArrayList

class FirestoreActivity : AppCompatActivity(), StudentAdapter.studentInterface {
    lateinit var binding : ActivityFirestoreBinding
    var item = ArrayList<StudentDataClass>()
    lateinit var linearLayoutManager: LinearLayoutManager
    val db = FirebaseFirestore.getInstance()
    lateinit var studentDataClass: StudentDataClass
    lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirestoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = StudentAdapter(item,this)
        binding.recyler.adapter = adapter
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyler.layoutManager = linearLayoutManager

        db.collection("users").addSnapshotListener { snapshot, e ->
            if (e != null){
                return@addSnapshotListener
            }
            for (snap in snapshot!!.documentChanges){
                val model = convertObject(snap.document)

                when (snap.type) {
                    DocumentChange.Type.ADDED -> {
                        model?.let {item.add(it) }
                        Log.e("", "List ${item.size}")
                        Log.e("", "List ${item}")

                    }

                    DocumentChange.Type.MODIFIED -> {
                        model?.let {
                            var index = getIndex(model)
                            if (index > -1)
                                item.set(index, it)
                        }
                    }

                    DocumentChange.Type.REMOVED -> {
                        model?.let {
                            var index = getIndex(model)
                            if (index > -1)
                                item.removeAt(index)
                        }
                    }
                }
            }
        }


        binding.fab.setOnClickListener {
            val dialog = Dialog(this)
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            dialogBinding.btnSubmit.setOnClickListener {
                if (dialogBinding.etClass.text.isNullOrEmpty()){
                    dialogBinding.etClass.error = "Enter class"
                }else if (dialogBinding.etName.text.isNullOrEmpty()){
                    dialogBinding.etName.error = "Enter name"
                }else{
                    db.collection("users").add(StudentDataClass(name = dialogBinding.etName.text.toString(), classStudent = dialogBinding.etClass.text.toString().toInt()))
                        .addOnSuccessListener {
                            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this,"Error ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }

    private fun convertObject(snapshot: QueryDocumentSnapshot) : StudentDataClass{
        val studentModel : StudentDataClass = snapshot.toObject(StudentDataClass::class.java)
        studentModel?.id = snapshot.id ?: ""
        return studentModel
    }

    private fun getIndex(studentDataClass: StudentDataClass): Int{
        var index = -1
        index = studentDataClass.indexOfFirst { element ->
            element.id?.equals(studentDataClass.id) == true
        }
        return index
    }

    override fun updateStudent(studentDataClass: StudentDataClass, position: Int) {
        val dialog = Dialog(this)
        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        dialogBinding.etClass.setText(studentDataClass.classStudent.toString())
        dialogBinding.etName.setText(studentDataClass.name)
        dialogBinding.btnSubmit.setText("Update")
        dialogBinding.btnSubmit.setOnClickListener {
            if (dialogBinding.etClass.text.isNullOrEmpty()){
                dialogBinding.etClass.error = "Enter class"
            }else if (dialogBinding.etName.text.isNullOrEmpty()){
                dialogBinding.etName.error = "Enter name"
            }else{
                db.collection("users").document(studentDataClass.id).set(
                    StudentDataClass(name = dialogBinding.etName.text.toString(), classStudent = dialogBinding.etClass.text.toString().toInt())
                )
                    .addOnSuccessListener {
                        Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    override fun deleteStudent(studentDataClass: StudentDataClass, position: Int) {
        db.collection("users").document(studentDataClass.id).delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Data Delete", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }
}