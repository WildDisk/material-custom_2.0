package com.example.picassotutorial.ui.java;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.picassotutorial.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressLint("Registered")
public class PictureActivity extends AppCompatActivity {
    private static final String IMAGE_URL_KEY = "IMAGE_URL";
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        mImageView = findViewById(R.id.imageView);

        findViewById(R.id.back).setOnClickListener(v -> finish());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        try {
            final URL newUrl = new URL(getIntent().getStringExtra(IMAGE_URL_KEY));

            mImageView.setImageBitmap(
                    BitmapFactory.decodeStream(
                            newUrl
                                    .openConnection()
                                    .getInputStream()
                    )
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
