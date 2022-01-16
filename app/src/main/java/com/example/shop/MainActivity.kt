package com.example.shop

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.adapter.ModeAdapter
import com.example.shop.adapter.ProductAdapter
import com.example.shop.dataSource.ModeDataSource
import com.example.shop.dataSource.ProductDataSource
import com.example.shop.model.Mode
import com.example.shop.model.Product

//TODO: create currentCategory and change only one instead of clearing all categories
//TODO: coming back from product to fav not working with cart
class MainActivity : AppCompatActivity() {
    enum class CurrentMode {
        MAIN, FAVORITE, PROMOTIONS, RECOMMENDATION
    }

    companion object {
        private var currentMode: CurrentMode = CurrentMode.MAIN
        val categoryList =
            listOf(R.string.dla_mezczyzn, R.string.krem_na_dzien, R.string.krem_na_noc, R.string.zel_do_mycia)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mode = intent.getParcelableExtra<Mode>("mode")

        if (mode != null) {
            currentMode = when (mode.nameResourceId) {
                R.string.category_name2 -> CurrentMode.FAVORITE
                R.string.category_name3 -> CurrentMode.PROMOTIONS
                R.string.category_name4 -> CurrentMode.RECOMMENDATION
                else -> CurrentMode.MAIN
            }
        }

        val modeId = when (currentMode) {
            CurrentMode.MAIN -> 0
            CurrentMode.FAVORITE -> 1
            CurrentMode.PROMOTIONS -> 2
            CurrentMode.RECOMMENDATION -> 3
        }

        val modesDataSet = ModeDataSource().loadModes()

        modesDataSet[modeId].chosen = true

        val modesRecyclerView = findViewById<RecyclerView>(R.id.categories)
        modesRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        modesRecyclerView.adapter = ModeAdapter(this, modesDataSet)


        val productsDataSet = when (currentMode) {
            CurrentMode.MAIN -> ProductDataSource().loadProductsMain()
            CurrentMode.FAVORITE -> ProductDataSource().loadProducts { product -> product.favorite }
            CurrentMode.PROMOTIONS -> ProductDataSource().loadProductsMain()
            CurrentMode.RECOMMENDATION -> ProductDataSource().loadProductsMain()
        }

        checkCart()

        val productsRecyclerView = findViewById<RecyclerView>(R.id.products)
        productsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        productsRecyclerView.adapter = ProductAdapter(this, productsDataSet)

        addCategoriesListeners()

        val search = findViewById<ImageView>(R.id.search)

        search.setOnClickListener {
            val intent = Intent(this, AddItem::class.java)
            startActivity(intent)
        }
    }

    private fun loadCategoriesBasic() {
        val textList = listOf<TextView>(
            findViewById(R.id.dla_mezczyzn), findViewById(R.id.krem_na_dzien),
            findViewById(R.id.krem_na_noc), findViewById(R.id.zel_do_mycia)
        )

        val bottomList = listOf<ImageView>(
            findViewById(R.id.bottomDlaMezczyzn), findViewById(R.id.bottomKremNaDzien),
            findViewById(R.id.bottomKremNaNoc), findViewById(R.id.bottomZelDoMycia)
        )
        for (text in textList) {
            text.setTextColor(Color.parseColor("#75726F"))
        }
        for (bottom in bottomList) {
            bottom.visibility = View.GONE
        }
    }

    private fun addCategoriesListeners() {

        val textList = listOf<TextView>(
            findViewById(R.id.dla_mezczyzn), findViewById(R.id.krem_na_dzien),
            findViewById(R.id.krem_na_noc), findViewById(R.id.zel_do_mycia)
        )

        val bottomList = listOf<ImageView>(
            findViewById(R.id.bottomDlaMezczyzn), findViewById(R.id.bottomKremNaDzien),
            findViewById(R.id.bottomKremNaNoc), findViewById(R.id.bottomZelDoMycia)
        )

        textList.forEachIndexed { index, element ->
            element.setOnClickListener {
                loadCategoriesBasic()
                loadProducts(categoryResourceId = categoryList[index])
                element.setTextColor(Color.parseColor("#03DBC7"))
                bottomList[index].visibility = View.VISIBLE
            }
        }
    }

    private fun checkCart() {
        var amountInCart = 0

        val productList = ProductDataSource().loadProductsMain()
        for (product in productList) {
            amountInCart += product.quantityInCart
        }

        val emptyCart = findViewById<Button>(R.id.emptyCart)
        val cart = findViewById<ConstraintLayout>(R.id.cart)
        if (amountInCart > 0) {
            emptyCart.visibility = View.GONE
            cart.visibility = View.VISIBLE
            val cartQuantity = findViewById<TextView>(R.id.quantity)
            cartQuantity.text = amountInCart.toString()

            cart.setOnClickListener {
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            }
        } else {
            emptyCart.visibility = View.VISIBLE
            cart.visibility = View.GONE
        }
    }

    fun loadProducts(nameResourceId: Int = 0, categoryResourceId: Int = 0) {
        val productsDataSet: List<Product>
        val modesDataSet = ModeDataSource().loadModes()

        if (categoryList.contains(categoryResourceId)) {
            productsDataSet =
                ProductDataSource().loadProducts { product: Product -> product.categoryResourceId == categoryResourceId }
        } else {
            loadCategoriesBasic()
            currentMode = when (nameResourceId) {
                R.string.category_name2 -> CurrentMode.FAVORITE
                R.string.category_name3 -> CurrentMode.PROMOTIONS
                R.string.category_name4 -> CurrentMode.RECOMMENDATION
                else -> CurrentMode.MAIN
            }

            val modeId = when (currentMode) {
                CurrentMode.MAIN -> 0
                CurrentMode.FAVORITE -> 1
                CurrentMode.PROMOTIONS -> 2
                CurrentMode.RECOMMENDATION -> 3
            }

            modesDataSet[modeId].chosen = true

            productsDataSet = when (currentMode) {
                CurrentMode.MAIN -> ProductDataSource().loadProductsMain()
                CurrentMode.FAVORITE -> ProductDataSource().loadProducts { product -> product.favorite }
                CurrentMode.PROMOTIONS -> ProductDataSource().loadProductsMain()
                CurrentMode.RECOMMENDATION -> ProductDataSource().loadProductsMain()
            }
        }

        val modesRecyclerView = findViewById<RecyclerView>(R.id.categories)
        modesRecyclerView.adapter = ModeAdapter(this, modesDataSet)

        checkCart()

        val productsRecyclerView = findViewById<RecyclerView>(R.id.products)
        productsRecyclerView.adapter = ProductAdapter(this, productsDataSet)
    }
}