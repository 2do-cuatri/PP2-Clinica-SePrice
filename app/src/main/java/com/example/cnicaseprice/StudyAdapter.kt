package com.example.cnicaseprice

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class StudyAdapter(val studies: MutableList<Study>, val activity: Activity): BaseAdapter() {

    override fun getCount(): Int {
        return studies.size
    }

    override fun getItem(position: Int): Study {
        return studies[position]
    }

    override fun getItemId(position: Int): Long {
        return studies[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_study, parent, false)
        val txtStudyName = view.findViewById<TextView>(R.id.txtStudyName)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
        val s = this.getItem(position)
        txtStudyName.text = s.name
        btnRegister.setOnClickListener {
            val intent = Intent(view.context, PatientActivity::class.java)
            view.context.startActivity(intent)
        }
        return view;
    }

}