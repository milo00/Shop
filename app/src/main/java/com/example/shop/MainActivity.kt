package com.example.shop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.adapter.CategoryAdapter
import com.example.shop.adapter.ProductAdapter
import com.example.shop.dataSource.CategoryDataSource
import com.example.shop.dataSource.ProductDataSource

class MainActivity : AppCompatActivity() {
    companion object {
        private val favArray = Array(ProductDataSource().getCount()) { false  }
        private val cartArray = Array(ProductDataSource().getCount()) { 0  }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categoriesDataSet = CategoryDataSource().loadProducts()

        val categoriesRecyclerView = findViewById<RecyclerView>(R.id.categories)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        categoriesRecyclerView.adapter = CategoryAdapter(this, categoriesDataSet)

        val productsDataSet = ProductDataSource().loadProducts()

        val position = intent.getIntExtra("position", -1)
        val favorite = intent.getBooleanExtra("favorite", false)
        val cart = intent.getIntExtra("cart", 0)

       /* println(favorite)
        println(cart)*/
        println(favArray.contentToString())

        if (position != -1){
            favArray[position] = favorite
            cartArray[position] = cart
        }

        for ((index, value) in productsDataSet.withIndex()) {
            value.favorite = favArray[index]
            value.quantityInCart = cartArray[index]
        }

        val productsRecyclerView = findViewById<RecyclerView>(R.id.products)
        productsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        productsRecyclerView.adapter = ProductAdapter(this, productsDataSet)
    }
}