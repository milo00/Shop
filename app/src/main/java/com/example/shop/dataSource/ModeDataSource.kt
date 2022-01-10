package com.example.shop.dataSource

import com.example.shop.R
import com.example.shop.model.Mode

class ModeDataSource {

    companion object {
        private const val initChosen = false
    }

    fun loadModes(): List<Mode> {
        return listOf(
            Mode(R.string.category_name1, initChosen),
            Mode(R.string.category_name2, initChosen),
            Mode(R.string.category_name3, initChosen),
            Mode(R.string.category_name4, initChosen)
        )
    }
}