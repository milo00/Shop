package com.example.shop.dataSource

import com.example.shop.model.Product
import com.example.shop.R

class ProductDataSource {

    fun loadProducts(): List<Product> {
        return listOf(
            Product(R.string.product1, R.string.capacity1, R.string.description1, R.string.prize1, "#FFEEE3", R.drawable.image1),
            Product(R.string.product2, R.string.capacity2, R.string.description2, R.string.prize2, "#DEACBA", R.drawable.image2),
            Product(R.string.product3, R.string.capacity3, R.string.description3, R.string.prize3, "#8EC7FA", R.drawable.image3),
            Product(R.string.product4, R.string.capacity4, R.string.description4, R.string.prize4, "#E0CAB6", R.drawable.image4),
            Product(R.string.product5, R.string.capacity5, R.string.description5, R.string.prize5, "#D5749A", R.drawable.image5),
            Product(R.string.product6, R.string.capacity6, R.string.description6, R.string.prize6, "#6ACC8A", R.drawable.image6),
            Product(R.string.product7, R.string.capacity7, R.string.description7, R.string.prize7, "#2BA8DC", R.drawable.image7)
        )
    }

    fun getCount(): Int {
        return loadProducts().size
    }
}