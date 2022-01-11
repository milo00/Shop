package com.example.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.app.Activity
import android.net.Uri
import androidx.activity.result.ActivityResult

import androidx.activity.result.ActivityResultCallback

import androidx.activity.result.contract.ActivityResultContracts

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult


class AddItem : AppCompatActivity() {
    private val image by lazy { findViewById<ImageView>(R.id.newImage) }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        image.setImageURI(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val add = findViewById<Button>(R.id.add)

        add.setOnClickListener {
            getContent.launch("image/*")
        }
    }
}