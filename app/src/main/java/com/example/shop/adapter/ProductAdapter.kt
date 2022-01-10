package com.example.shop.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.ProductDetails
import com.example.shop.model.Product
import com.example.shop.R

class ProductAdapter(private val context: Context, private val dataSource: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.name)
        val productCapacity: TextView = view.findViewById(R.id.capacity)
        val productPrice: TextView = view.findViewById(R.id.price)
        val productColor: ConstraintLayout = view.findViewById(R.id.background)
        val productImage: ImageView = view.findViewById(R.id.image)
        val productFavorite: ImageView = view.findViewById(R.id.favorite)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_row_new, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.setText(dataSource[position].titleResourceId)
        holder.productCapacity.setText(dataSource[position].capacityResourceId)
        holder.productPrice.setText(dataSource[position].prizeResourceId)
        holder.productColor.setBackgroundColor(Color.parseColor(dataSource[position].colorResourceId))
        holder.productImage.setImageResource(dataSource[position].imageResourceId)

        if (dataSource[position].favorite) {
            holder.productFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetails::class.java)
            intent.putExtra("product", dataSource[position])
            println("adapter $position")
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}