package com.example.shop.dataSource

import com.example.shop.R
import com.example.shop.model.Category

class CategoryDataSource {

    fun loadProducts(): List<Category> {
        return listOf(
            Category(R.string.category_name1),
            Category(R.string.category_name2),
            Category(R.string.category_name3),
            Category(R.string.category_name4)
        )
    }
}