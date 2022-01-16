package com.example.shop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.shop.adapter.ProductAdapter
import com.example.shop.dataSource.ProductDataSource
import com.example.shop.model.Product
import kotlinx.android.synthetic.main.activity_add_item.*


class AddItemActivity : AppCompatActivity() {
    private val image by lazy { findViewById<ImageView>(R.id.newImage) }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        image.setImageURI(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val addPhoto = findViewById<Button>(R.id.addPhoto)

        addPhoto.setOnClickListener {
            getContent.launch("image/*")
            oldImage.visibility = View.GONE
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
        val categoryId = when (spinner.selectedItem.toString()) {
            getString(R.string.dla_mezczyzn) -> R.string.dla_mezczyzn
            getString(R.string.krem_na_noc) -> R.string.krem_na_noc
            getString(R.string.krem_na_dzien) -> R.string.krem_na_dzien
            getString(R.string.zel_do_mycia) -> R.string.zel_do_mycia
            else -> {
                0
            }
        }

        if (name == "" || capacity == "" || price == "" || color == "" || desc == "" || categoryId == 0) {
            val toast = Toast.makeText(this, "Musisz wypelnić wszystkie pola!", Toast.LENGTH_LONG)
            toast.show()
        } else {
            val newProduct = Product(name, capacity, desc, price, categoryId, "#$color", R.drawable.image23, false, 0)
            ProductDataSource().addProduct(newProduct)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}