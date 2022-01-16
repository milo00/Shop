package com.example.shop

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import com.example.shop.dataSource.ProductDataSource
import com.example.shop.model.Product
import kotlinx.android.synthetic.main.activity_add_item.*

class EditItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        var product = intent.getParcelableExtra<Product>("product")
        if (product == null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("toast", "Nie ma takiego produktu")
            startActivity(intent)
        } else {
            loadProduct(product)

            val back = findViewById<ImageView>(R.id.back)

            back.setOnClickListener {
                val intent = Intent(this, ProductDetailsActivity::class.java)
                intent.putExtra("product", product)
                startActivity(intent)
            }

            val update = findViewById<Button>(R.id.add)
            update.setOnClickListener {
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

                ProductDataSource().updateProduct(
                    product!!.titleResource,
                    name,
                    capacity,
                    desc,
                    price,
                    categoryId,
                    color
                )
                product = ProductDataSource().getProduct(name)!!
                val intent = Intent(this, ProductDetailsActivity::class.java)
                intent.putExtra("toast", "Pomyślnie zaktualizowano produkt")
                intent.putExtra("product", product)
                startActivity(intent)
            }
        }
    }

    private fun loadProduct(product: Product) {
        oldImage.setImageResource(product.imageResourceId)
        nazwa.setText(product.titleResource)
        pojemnosc.setText(product.capacityResource)
        cena.setText(product.prizeResource)
        opis.setText(product.descriptionResource)
        kolorNowego.setText(product.colorResource?.drop(1))

        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val index = when (product.categoryResourceId) {
            R.string.dla_mezczyzn -> 0
            R.string.krem_na_noc -> 1
            R.string.krem_na_dzien -> 2
            R.string.zel_do_mycia -> 3
            else -> {
                -1
            }
        }
        spinner.setSelection(index)
    }
}