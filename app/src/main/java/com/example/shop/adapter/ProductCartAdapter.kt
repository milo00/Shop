package com.example.shop.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.CartActivity
import com.example.shop.MainActivity
import com.example.shop.ProductDetailsActivity
import com.example.shop.R
import com.example.shop.model.Product

class ProductCartAdapter (private val context: Context, private val dataSource: List<Product>) :
    RecyclerView.Adapter<ProductCartAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.name)
        val productCapacity: TextView = view.findViewById(R.id.capacity)
        val productPrice: TextView = view.findViewById(R.id.price)
        val productCategory: TextView = view.findViewById(R.id.category)
        val productImage: ImageView = view.findViewById(R.id.image)
        val productQuantity: TextView = view.findViewById(R.id.quantity)

        val removeItem: ImageView = view.findViewById(R.id.remove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_cart_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.setText(dataSource[position].titleResource)
        holder.productCapacity.setText(dataSource[position].capacityResource)
        holder.productPrice.setText(dataSource[position].prizeResource)
        holder.productCategory.setText(dataSource[position].categoryResourceId)
        holder.productImage.setImageResource(dataSource[position].imageResourceId)
        holder.productQuantity.text = dataSource[position].quantityInCart.toString()

        holder.removeItem.setOnClickListener {
            dataSource[position].quantityInCart = 0
            (context as CartActivity).loadCart()
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}