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
import com.example.shop.MainActivity
import com.example.shop.ProductDetailsActivity
import com.example.shop.R
import com.example.shop.model.Product

class ProductAdapter(
    private val context: Context, private val dataSource: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    companion object {
        private var name: String = ""
        private var capacity: String = ""
        private var price: String = ""
        private var color: String = ""

        fun set(newName: String, newCapacity: String, newPrice: String, newColor: String) {
            name = newName
            capacity = newCapacity
            price = newPrice
            color = newColor
        }
    }

    fun addItem(newName: String, newCapacity: String, newPrice: String, newColor: String) {
        set(newName, newCapacity, newPrice, newColor)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.name)
        val productCapacity: TextView = view.findViewById(R.id.capacity)
        val productPrice: TextView = view.findViewById(R.id.price)
        val productColor: ConstraintLayout = view.findViewById(R.id.background)
        val productImage: ImageView = view.findViewById(R.id.image)
        val productFavorite: ImageView = view.findViewById(R.id.favorite)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = dataSource[position].titleResource
        holder.productCapacity.text = dataSource[position].capacityResource
        holder.productPrice.text = dataSource[position].prizeResource
        holder.productColor.setBackgroundColor(Color.parseColor(dataSource[position].colorResource))
        holder.productImage.setImageResource(dataSource[position].imageResourceId)

        val favorite = dataSource[position].favorite
        if (favorite) {
            holder.productFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        if (name != "" && dataSource[position].titleResource == "") {
            holder.productName.text = name
            holder.productCapacity.text = capacity
            holder.productPrice.text = price
            holder.productColor.setBackgroundColor(Color.parseColor("#$color"))
            holder.productImage.setImageResource(dataSource[position].imageResourceId)
        }

        holder.productFavorite.setOnClickListener {
            dataSource[position].favorite = !favorite
            if (dataSource[position].favorite) {
                holder.productFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                holder.productFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                //TODO:change
                (context as MainActivity).loadProducts(R.string.category_name2)
            }
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("product", dataSource[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}