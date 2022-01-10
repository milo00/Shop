package com.example.shop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.adapter.ModeAdapter
import com.example.shop.adapter.ProductAdapter
import com.example.shop.adapter.ProductCartAdapter
import com.example.shop.dataSource.ModeDataSource
import com.example.shop.dataSource.ProductDataSource
import com.example.shop.model.Mode
import com.example.shop.model.Product

class MainActivity : AppCompatActivity() {
    enum class CurrentMode {
        MAIN, FAVORITE, PROMOTIONS, RECOMMENDATION
    }

    private var currentMode: CurrentMode = CurrentMode.MAIN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mode = intent.getParcelableExtra<Mode>("mode")

        if (mode != null) {
            currentMode = when (mode.nameResourceId) {
                R.string.category_name2 -> CurrentMode.FAVORITE
                R.string.category_name3 -> CurrentMode.PROMOTIONS
                R.string.category_name4 -> CurrentMode.RECOMMENDATION
                else -> CurrentMode.MAIN
            }
        }

        val modeId = when (currentMode) {
            CurrentMode.MAIN -> 0
            CurrentMode.FAVORITE -> 1
            CurrentMode.PROMOTIONS -> 2
            CurrentMode.RECOMMENDATION -> 3
        }

        val modesDataSet = ModeDataSource().loadModes()

        modesDataSet[modeId].chosen = true

        val modesRecyclerView = findViewById<RecyclerView>(R.id.categories)
        modesRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        modesRecyclerView.adapter = ModeAdapter(this, modesDataSet)


        val productsDataSet = when (currentMode) {
            CurrentMode.MAIN -> ProductDataSource().loadProductsMain()
            CurrentMode.FAVORITE -> ProductDataSource().loadProductsFav()
            CurrentMode.PROMOTIONS -> ProductDataSource().loadProductsMain()
            CurrentMode.RECOMMENDATION -> ProductDataSource().loadProductsMain()
        }


        val position = intent.getIntExtra("position", -1)
        val favorite = intent.getBooleanExtra("favorite", false)
        val cart = intent.getIntExtra("cart", 0)

        if (position != -1) {
            productsDataSet[position].favorite = favorite
            productsDataSet[position].quantityInCart = cart
        }

        checkCart()

        val productsRecyclerView = findViewById<RecyclerView>(R.id.products)
        productsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        productsRecyclerView.adapter = ProductAdapter(this, productsDataSet)
    }

    private fun checkCart() {
        var amountInCart = 0

        val productList = ProductDataSource().loadProductsMain()
        for (product in productList) {
            amountInCart += product.quantityInCart
        }

        val emptyCart = findViewById<Button>(R.id.emptyCart)
        val cart = findViewById<ConstraintLayout>(R.id.cart)
        if (amountInCart > 0) {
            emptyCart.visibility = View.GONE
            cart.visibility = View.VISIBLE
            val cartQuantity = findViewById<TextView>(R.id.quantity)
            cartQuantity.text = amountInCart.toString()

            cart.setOnClickListener {
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            }
        } else {
            emptyCart.visibility = View.VISIBLE
            cart.visibility = View.GONE
        }
    }
}