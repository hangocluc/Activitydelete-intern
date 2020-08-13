package com.example.kotlinthemsuaxoa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.kotlinthemsuaxoa.adapter.Adapter
import com.example.kotlinthemsuaxoa.database.DataDAO
import com.example.kotlinthemsuaxoa.them.Student
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add.view.*
import kotlinx.android.synthetic.main.activity_add.view.edtMa
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*
import kotlinx.android.synthetic.main.item.view.*

class MainActivity : AppCompatActivity(), Adapter.onClickListener {
   lateinit  var adapter : Adapter
    lateinit var dataDao : DataDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = AlertDialog.Builder(this)
        val dialog = LayoutInflater.from(this).inflate(R.layout.activity_add,null)
        btn_them.setOnClickListener {
            builder.setView(dialog)
            val dialogFF = builder.create()
            dialogFF.show()
            dialog.btnSave.setOnClickListener {
                val id = dialog.edtMa.text.toString().toInt()
                val name = dialog.edtTen.text.toString()
                val student = Student(id,name)
                dataDao.insert(student)
                adapter.setData(dataDao.getAll())
                dialogFF.dismiss()
                dialog.clearFocus()
                //fjfkfkgfkug
            }
        }


        dataDao = DataDAO(this)
        adapter = Adapter(this)
        listview.adapter = adapter
        adapter.setData(dataDao.getAll())
        adapter.setOnclick(this)



    }

    override fun onClickll(student: Student) {
        val builder = AlertDialog.Builder(this)
        val dialog = LayoutInflater.from(this).inflate(R.layout.activity_add,null)

        builder.setView(dialog)
        val dialogFF = builder.create()
        dialogFF.show()

        dialog.edtMa.setText(student.id.toString())
        dialog.edtTen.setText(student.name)

        dialog.btnSave.setOnClickListener {
            val id = dialog.edtMa.text.toString()
            val name = dialog.edtTen.text.toString()
            val student = Student(id.toInt(),name)
            dataDao.update(student)
            adapter.setData(dataDao.getAll())
            dialogFF.dismiss()
        }



//        val id = dialog.edtMa.text.toString().toInt()
//        val name = dialog.edtTen.text.toString()
//        val student = Student(id,name)
//        dataDao.insert(student)
//        dataDao.update(dataDao.getAll()[position])
//        adapter.notifyDataSetChanged()
//        adapter.setData(dataDao.getAll())

    }

    override fun onClick(position: Int) {
        dataDao.delete(dataDao.getAll()[position])
        adapter.notifyDataSetChanged()
        adapter.setData(dataDao.getAll())
    }

}
