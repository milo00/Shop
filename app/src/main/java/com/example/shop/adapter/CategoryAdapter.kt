package com.example.shop.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.model.Category

class CategoryAdapter(private val context: Context, private val dataSource: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var clicked = false

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName: TextView = view.findViewById(R.id.category_name)
        val categoryBackground: ConstraintLayout = view.findViewById(R.id.oval)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.setText(dataSource[position].nameResourceId)

        holder.itemView.setOnClickListener {
            if (clicked) {
                holder.categoryName.setTextColor(Color.parseColor("#535B63"))
                holder.categoryBackground.setBackgroundResource(R.drawable.category_background)
            } else {
                holder.categoryName.setTextColor(Color.parseColor("#FFFFFF"))
                holder.categoryBackground.setBackgroundResource(R.drawable.category_background_green)
            }
            clicked = !clicked
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

}