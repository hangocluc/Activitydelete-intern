package com.example.kotlinthemsuaxoa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kotlinthemsuaxoa.R
import com.example.kotlinthemsuaxoa.them.Student
import kotlinx.android.synthetic.main.item.view.*

class Adapter: BaseAdapter {
    var context:Context
    private var student: List<Student> = listOf()
    private lateinit var listener : onClickListener

    fun setOnclick(listener: onClickListener){
        this.listener = listener
    }
    fun setData(student: List<Student>){
        this.student = student
    }

    constructor(context: Context){
        this.context = context
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        var txt_id:TextView = convertView.findViewById(R.id.txt_id)
        var txt_name:TextView= convertView.findViewById(R.id.txt_name)
        var student:Student = getItem(position) as Student
        txt_id.setText(student.id.toString())
        txt_name.setText(student.name)
        convertView.btn_capnhat.setOnClickListener {
          listener.onClickll(student)
}
        convertView.btn_xoa.setOnClickListener {
            listener.onClick(position)
        }
        return convertView

    }

    override fun getItem(position: Int): Any {
       return student.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return student.size
    }
    interface onClickListener{
        fun onClick(position : Int)
        fun onClickll(student : Student)
    }
}