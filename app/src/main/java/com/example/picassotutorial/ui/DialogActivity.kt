package com.example.picassotutorial.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.picassotutorial.R

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_dialog)
        title = "Attention!"
    }
}