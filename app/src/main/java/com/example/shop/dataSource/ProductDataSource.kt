package com.example.shop.dataSource

import com.example.shop.model.Product
import com.example.shop.R

//TODO: connect those categories with the rest of the code
class ProductDataSource {
    companion object {
        private const val initFavourite = false
        private const val menCategory = R.string.dla_mezczyzn
        private const val dayCategory = R.string.krem_na_dzien
        private const val nightCategory = R.string.krem_na_noc
        private const val gelCategory = R.string.zel_do_mycia
        private const val initCart = 0
        val products = mutableListOf(
            Product(R.string.product1, R.string.capacity1, R.string.description1, R.string.prize1, gelCategory, "#FFEEE3", R.drawable.image1, initFavourite, initCart),
            Product(R.string.product2, R.string.capacity2, R.string.description2, R.string.prize2, gelCategory, "#DEACBA", R.drawable.image2, initFavourite, initCart),
            Product(R.string.product3, R.string.capacity3, R.string.description3, R.string.prize3, gelCategory, "#8EC7FA", R.drawable.image3, initFavourite, initCart),
            Product(R.string.product4, R.string.capacity4, R.string.description4, R.string.prize4, gelCategory, "#E0CAB6", R.drawable.image4, initFavourite, initCart),
            Product(R.string.product5, R.string.capacity5, R.string.description5, R.string.prize5, gelCategory, "#D5749A", R.drawable.image5, initFavourite, initCart),
            Product(R.string.product6, R.string.capacity6, R.string.description6, R.string.prize6, gelCategory, "#6ACC8A", R.drawable.image6, initFavourite, initCart),
            Product(R.string.product7, R.string.capacity7, R.string.description7, R.string.prize7, gelCategory, "#2BA8DC", R.drawable.image7, initFavourite, initCart),
            Product(R.string.product8, R.string.capacity8, R.string.description8, R.string.prize8, dayCategory, "#D4BFB9", R.drawable.image8, initFavourite, initCart),
            Product(R.string.product9, R.string.capacity9, R.string.description9, R.string.prize9, dayCategory, "#3965A3", R.drawable.image9, initFavourite, initCart),
            Product(R.string.product10, R.string.capacity10, R.string.description10, R.string.prize10, dayCategory, "#F9A76E", R.drawable.image10, initFavourite, initCart),
            Product(R.string.product11, R.string.capacity11, R.string.description11, R.string.prize11, dayCategory, "#CD9D6C", R.drawable.image11, initFavourite, initCart),
            Product(R.string.product12, R.string.capacity12, R.string.description12, R.string.prize12, dayCategory, "#74BF27", R.drawable.image12, initFavourite, initCart),
            Product(R.string.product13, R.string.capacity13, R.string.description13, R.string.prize13, dayCategory, "#9BC37C", R.drawable.image13, initFavourite, initCart),
            Product(R.string.product14, R.string.capacity14, R.string.description14, R.string.prize14, dayCategory, "#AAC9E5", R.drawable.image14, initFavourite, initCart),
            Product(R.string.product15, R.string.capacity15, R.string.description15, R.string.prize15, nightCategory, "#B28A9B", R.drawable.image15, initFavourite, initCart),
            Product(R.string.product16, R.string.capacity16, R.string.description16, R.string.prize16, nightCategory, "#F08F3F", R.drawable.image16, initFavourite, initCart),
            Product(R.string.product17, R.string.capacity17, R.string.description17, R.string.prize17, nightCategory, "#EDE3D8", R.drawable.image17, initFavourite, initCart),
            Product(R.string.product18, R.string.capacity18, R.string.description18, R.string.prize18, nightCategory, "#E2C66B", R.drawable.image18, initFavourite, initCart),
            Product(R.string.product19, R.string.capacity19, R.string.description19, R.string.prize19, menCategory, "#BF935A", R.drawable.image19, initFavourite, initCart),
            Product(R.string.product20, R.string.capacity20, R.string.description20, R.string.prize20, menCategory, "#AC978E", R.drawable.image20, initFavourite, initCart),
            Product(R.string.product21, R.string.capacity21, R.string.description21, R.string.prize21, menCategory, "#DC2C31", R.drawable.image21, initFavourite, initCart),
            Product(R.string.product22, R.string.capacity22, R.string.description22, R.string.prize22, menCategory, "#A68764", R.drawable.image22, initFavourite, initCart)
        )

        val shuffledProducts = products.shuffled().toMutableList()
    }

    fun loadProductsMain(): List<Product> {
        return shuffledProducts
    }

    fun loadProducts(condition: (product: Product) -> Boolean): List<Product> {
        return products.filter { product -> condition(product) }
    }

    fun addProduct(product: Product) {
        products.add(product)
        shuffledProducts.add(product)
    }
}