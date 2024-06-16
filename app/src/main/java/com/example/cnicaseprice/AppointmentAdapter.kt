package com.example.cnicaseprice

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AppointmentAdapter(val appointments: MutableList<Appointment>, val activity: Activity): BaseAdapter() {

    override fun getCount(): Int {
        return appointments.size
    }

    override fun getItem(position: Int): Appointment {
        return appointments[position]
    }

    override fun getItemId(position: Int): Long {
        return appointments[position].ID.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_appointment, parent, false)
        val txtStudyType = view.findViewById<TextView>(R.id.tvAppType)
        val txtStudyName = view.findViewById<TextView>(R.id.tvAppName)
        val txtDate = view.findViewById<TextView>(R.id.tvAppDate)
        val s = this.getItem(position)
        txtStudyName.text = s.Name
        txtDate.text = s.Date
        txtStudyType.text = s.Type
        return view;
    }

}