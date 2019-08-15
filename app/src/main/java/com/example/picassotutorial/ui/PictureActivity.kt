package com.example.picassotutorial.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.picassotutorial.R
import kotlinx.android.synthetic.main.activity_picture.*
import java.net.URL

class PictureActivity : AppCompatActivity() {
    private lateinit var mImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        mImageView = imageView

        back?.setOnClickListener {
            finish()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val newUrl: URL? = URL(intent?.getStringExtra(IMAGE_URL_KEY))

        mImageView.setImageBitmap(
            BitmapFactory.decodeStream(
                newUrl
                    ?.openConnection()
                    ?.getInputStream()
            )
        )
    }

    companion object {
        private const val IMAGE_URL_KEY = "IMAGE_URL"
    }
}