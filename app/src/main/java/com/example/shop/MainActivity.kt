package com.example.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.adapter.CategoryAdapter
import com.example.shop.adapter.ProductAdapter
import com.example.shop.dataSource.CategoryDataSource
import com.example.shop.dataSource.ProductDataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categoriesDataSet = CategoryDataSource().loadProducts()

        val categoriesRecyclerView = findViewById<RecyclerView>(R.id.categories)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        categoriesRecyclerView.adapter = CategoryAdapter(this, categoriesDataSet)

        val productsDataSet = ProductDataSource().loadProducts()

        val productsRecyclerView = findViewById<RecyclerView>(R.id.products)
        productsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        productsRecyclerView.adapter = ProductAdapter(this, productsDataSet)
    }
}