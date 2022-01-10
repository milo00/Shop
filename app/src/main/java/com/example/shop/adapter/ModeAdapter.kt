package com.example.shop.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.CartActivity
import com.example.shop.MainActivity
import com.example.shop.R
import com.example.shop.model.Mode

class ModeAdapter(private val context: Context, private val dataSource: List<Mode>) :
    RecyclerView.Adapter<ModeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName: TextView = view.findViewById(R.id.category_name)
        val categoryBackground: ConstraintLayout = view.findViewById(R.id.oval)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mode_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.setText(dataSource[position].nameResourceId)

        if (dataSource[position].chosen) {
            holder.categoryName.setTextColor(Color.parseColor("#FFFFFF"))
            holder.categoryBackground.setBackgroundResource(R.drawable.category_background_green)
        }

        holder.itemView.setOnClickListener {
            (context as MainActivity).loadProducts(dataSource[position].nameResourceId)
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

}