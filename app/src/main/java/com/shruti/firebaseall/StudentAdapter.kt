package com.shruti.firebaseall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class StudentAdapter(val item : ArrayList<StudentDataClass>, val interfaceStudent : studentInterface) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {
        val nameStudent = view.findViewById<TextView>(R.id.tvName)
        val classStudent = view.findViewById<TextView>(R.id.tvClass)
        val updateStudent = view.findViewById<Button>(R.id.btnUpdate)
        val deleteStudent = view.findViewById<Button>(R.id.btnDelete)    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent,false)
        return StudentAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.ViewHolder, position: Int) {
        holder.nameStudent.setText(item[position].name)
        holder.classStudent.setText(item[position].classStudent.toString())
        holder.updateStudent.setOnClickListener {
            interfaceStudent.updateStudent(item[position],position)
        }
        holder.deleteStudent.setOnClickListener {
            interfaceStudent.deleteStudent(item[position],position)
        }
    }

    override fun getItemCount(): Int {
        return  item.size
    }
    interface studentInterface{
        fun updateStudent(studentDataClass: StudentDataClass,position: Int)
        fun deleteStudent(studentDataClass: StudentDataClass,position: Int)
    }
}