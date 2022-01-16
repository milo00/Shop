package com.example.shop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.adapter.ProductCartAdapter
import com.example.shop.dataSource.ProductDataSource

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val productsDataSet = ProductDataSource().loadProducts { product -> product.quantityInCart > 0 }

        val productsRecyclerView = findViewById<RecyclerView>(R.id.items)
        productsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        productsRecyclerView.adapter = ProductCartAdapter(this, productsDataSet)

        val back = findViewById<ImageView>(R.id.back2)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun loadCart() {
        val productsDataSet = ProductDataSource().loadProducts { product -> product.quantityInCart > 0 }

        val productsRecyclerView = findViewById<RecyclerView>(R.id.items)
        productsRecyclerView.adapter = ProductCartAdapter(this, productsDataSet)

        if (productsDataSet.isEmpty()) {
            val alert = findViewById<TextView>(R.id.alert)
            alert.text = "Twój koszyk jest pusty. Czas to zmienić!"

            val home = findViewById<ImageView>(R.id.home)
            home.visibility = View.VISIBLE
            home.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}