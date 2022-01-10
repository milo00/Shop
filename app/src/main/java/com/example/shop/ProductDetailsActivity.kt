package com.example.shop

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import com.example.shop.model.Product

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val product = intent.getParcelableExtra<Product>("product")
        val position = intent.getIntExtra("position", -1)

        if (product != null) {
            setIntent(product)
        }

        val back = findViewById<ImageView>(R.id.back)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            if (product != null) {
                intent.putExtra("favorite", product.favorite)
                intent.putExtra("cart", product.quantityInCart)
                intent.putExtra("position", position)
            }
            startActivity(intent)
        }

        if (product != null) {
            setFavoriteOption(product)
            setCartOption(product)
        }

    }

    private fun setIntent(product: Product) {
        val productName: TextView = findViewById(R.id.name)
        productName.setText(product.titleResourceId)

        val productCapacity: TextView = findViewById(R.id.description)
        productCapacity.setText(product.descriptionResourceId)

        val productImage = findViewById<ImageView>(R.id.image)
        productImage.setImageResource(product.imageResourceId)

        val productBackground = findViewById<ImageView>(R.id.background)
        DrawableCompat.setTint(productBackground.drawable, Color.parseColor(product.colorResourceId))

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
                product.favorite = !product.favorite
            } else {
                favorite.setImageResource(
                    resources.getIdentifier(
                        "ic_baseline_favorite_24", "drawable", packageName
                    )
                )
                product.favorite = !product.favorite
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
                product.quantityInCart++
            } else if (product.quantityInCart == 1) {
                cart.setImageResource(
                    resources.getIdentifier(
                        "ic_baseline_add_shopping_cart_24", "drawable", packageName
                    )
                )
                product.quantityInCart--
            }
            println(product.quantityInCart)
        }
    }

}