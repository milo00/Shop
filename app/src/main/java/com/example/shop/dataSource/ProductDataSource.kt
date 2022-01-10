package com.example.shop.dataSource

import com.example.shop.model.Product
import com.example.shop.R

class ProductDataSource {
    companion object {
        private const val initFavourite = false
        private const val category = R.string.zel_do_mycia
        private const val initCart = 0
        val products: List<Product> = listOf(
            Product(
                R.string.product1,
                R.string.capacity1,
                R.string.description1,
                R.string.prize1,
                category,
                "#FFEEE3",
                R.drawable.image1,
                initFavourite,
                initCart
            ),
            Product(
                R.string.product2,
                R.string.capacity2,
                R.string.description2,
                R.string.prize2,
                category,
                "#DEACBA",
                R.drawable.image2,
                initFavourite,
                initCart
            ),
            Product(
                R.string.product3,
                R.string.capacity3,
                R.string.description3,
                R.string.prize3,
                category,
                "#8EC7FA",
                R.drawable.image3,
                initFavourite,
                initCart
            ),
            Product(
                R.string.product4,
                R.string.capacity4,
                R.string.description4,
                R.string.prize4,
                category,
                "#E0CAB6",
                R.drawable.image4,
                initFavourite,
                initCart
            ),
            Product(
                R.string.product5,
                R.string.capacity5,
                R.string.description5,
                R.string.prize5,
                category,
                "#D5749A",
                R.drawable.image5,
                initFavourite,
                initCart
            ),
            Product(
                R.string.product6,
                R.string.capacity6,
                R.string.description6,
                R.string.prize6,
                category,
                "#6ACC8A",
                R.drawable.image6,
                initFavourite,
                initCart
            ),
            Product(
                R.string.product7,
                R.string.capacity7,
                R.string.description7,
                R.string.prize7,
                category,
                "#2BA8DC",
                R.drawable.image7,
                initFavourite,
                initCart
            )
        )
    }

    fun loadProductsMain(): List<Product> {
        return products
    }

    fun loadProducts(condition: (product: Product) -> Boolean): List<Product> {
        return products.filter { product -> condition(product) }
    }
}