package com.example.shop

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import com.example.shop.dataSource.ProductDataSource
import com.example.shop.model.Product

class ProductDetailsActivity : AppCompatActivity() {
    companion object {
        private var name: String = ""
        private var capacity: String = ""
        private var price: String = ""
        private var color: String = ""
        private var desc: String = ""

        fun set(newName: String, newCapacity: String, newPrice: String, newColor: String, newDesc: String) {
            name = newName
            capacity = newCapacity
            price = newPrice
            color = newColor
            desc = newDesc
        }
    }

    fun addItem(newName: String, newCapacity: String, newPrice: String, newColor: String, newDesc: String) {
        set(newName, newCapacity, newPrice, newColor, newDesc)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val product = intent.getParcelableExtra<Product>("product")

        if (product != null) {
            setIntent(product)
        }

        val back = findViewById<ImageView>(R.id.back)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        if (product != null) {
            setFavoriteOption(product)
            setCartOption(product)
        }

    }

    private fun setIntent(product: Product) {
        val productName: TextView = findViewById(R.id.name)
        productName.text = product.titleResource

        val productCapacity: TextView = findViewById(R.id.description)
        productCapacity.text = product.descriptionResource

        val productImage = findViewById<ImageView>(R.id.image)
        productImage.setImageResource(product.imageResourceId)

        val productBackground = findViewById<ImageView>(R.id.background)
        DrawableCompat.setTint(productBackground.drawable, Color.parseColor(product.colorResource))

        val favorite = findViewById<ImageView>(R.id.favorite)
        if (product.favorite) {
            favorite.setImageResource(
                resources.getIdentifier(
                    "ic_baseline_favorite_24", "drawable", packageName
                )
            )
        }

        val cart = findViewById<ImageView>(R.id.emptyCart)
        if (product.quantityInCart == 1) {
            cart.setImageResource(
                resources.getIdentifier(
                    "ic_baseline_remove_shopping_cart_24", "drawable", packageName
                )
            )
        }

        if (name != "" && product.titleResource == "") {
            productName.text = name
            productCapacity.text = capacity
            DrawableCompat.setTint(productBackground.drawable, Color.parseColor("#${color}"))
        }
    }

    private fun setFavoriteOption(product: Product) {
        val favorite = findViewById<ImageView>(R.id.favorite)

        favorite.setOnClickListener {
            if (product.favorite) {
                favorite.setImageResource(
                    resources.getIdentifier(
                        "ic_baseline_favorite_border_24", "drawable", packageName
                    )
                )
                ProductDataSource().setFavorite(product.titleResource)
            } else {
                favorite.setImageResource(
                    resources.getIdentifier(
                        "ic_baseline_favorite_24", "drawable", packageName
                    )
                )
                ProductDataSource().setFavorite(product.titleResource)
            }
        }
    }

    private fun setCartOption(product: Product) {
        val cart = findViewById<ImageView>(R.id.emptyCart)

        cart.setOnClickListener {
            if (product.quantityInCart == 0) {
                cart.setImageResource(
                    resources.getIdentifier(
                        "ic_baseline_remove_shopping_cart_24", "drawable", packageName
                    )
                )
                ProductDataSource().setQuantity(product.titleResource, product.quantityInCart + 1)
            } else if (product.quantityInCart == 1) {
                cart.setImageResource(
                    resources.getIdentifier(
                        "ic_baseline_add_shopping_cart_24", "drawable", packageName
                    )
                )
                ProductDataSource().setQuantity(product.titleResource, product.quantityInCart - 1)
            }
        }
    }

}