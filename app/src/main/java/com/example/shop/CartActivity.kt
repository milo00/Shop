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
import kotlinx.android.synthetic.main.activity_cart.*
import java.math.RoundingMode

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val productsDataSet = ProductDataSource().loadProducts { product -> product.quantityInCart > 0 }

        val productsRecyclerView = findViewById<RecyclerView>(R.id.items)
        productsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        productsRecyclerView.adapter = ProductCartAdapter(this, productsDataSet)

        var sum = 0.0
        productsDataSet.forEach {
            sum += (it.prizeResource?.replace(",", ".")?.toDouble() ?: 0.0) * it.quantityInCart
        }
        suma.text = sum.toBigDecimal().setScale(2, RoundingMode.UP).toString() + "zł"

        val back = findViewById<ImageView>(R.id.back2)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        kup.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
    }

    fun loadCart() {
        val productsDataSet = ProductDataSource().loadProducts { product -> product.quantityInCart > 0 }

        var sum = 0.0
        productsDataSet.forEach {
            sum += (it.prizeResource?.replace(",", ".")?.toDouble() ?: 0.0) * it.quantityInCart
        }
        suma.text = sum.toBigDecimal().setScale(2, RoundingMode.UP).toString() + "zł"

        val productsRecyclerView = findViewById<RecyclerView>(R.id.items)
        productsRecyclerView.adapter = ProductCartAdapter(this, productsDataSet)

        if (productsDataSet.isEmpty()) {
            alert.text = "Twój koszyk jest pusty. Czas to zmienić!"

            home.visibility = View.VISIBLE
            powrot.visibility = View.VISIBLE

            w_sumie.visibility = View.GONE
            suma.visibility = View.GONE
            kup.visibility = View.GONE

            home.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}