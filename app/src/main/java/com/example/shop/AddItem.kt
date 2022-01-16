package com.example.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.net.Uri

import androidx.activity.result.contract.ActivityResultContracts
import com.example.shop.adapter.ProductAdapter
import com.example.shop.dataSource.ProductDataSource
import kotlinx.android.synthetic.main.activity_add_item.*


class AddItem : AppCompatActivity() {
    private val image by lazy { findViewById<ImageView>(R.id.newImage) }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        image.setImageURI(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val addPhoto = findViewById<Button>(R.id.addPhoto)

        addPhoto.setOnClickListener {
            getContent.launch("image/*")
        }

        val add = findViewById<Button>(R.id.add)
        add.setOnClickListener {
            addItem()
        }
    }

    private fun addItem() {
        val name = nazwa.text.toString()
        val capacity = pojemnosc.text.toString()
        val price = cena.text.toString()
        val color = kolorNowego.text.toString()
        val desc = opis.text.toString()

        ProductDataSource().addItem()
        ProductDetailsActivity().addItem(name, capacity, price, color, desc)
        ProductAdapter(this, ProductDataSource().loadProductsMain()).addItem(name, capacity, price, color)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}