package com.example.picassotutorial.ui.java;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.picassotutorial.R;
import com.example.picassotutorial.adapters.java.ImagesAdapter;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView list = findViewById(R.id.view_list);
        final View middleSheet = findViewById(R.id.middleSheet);
        final View bottomSheet = findViewById(R.id.bottomSheet);
        final Button mButtonAction = findViewById(R.id.btn_action);

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
        ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.INTERNET},
                    123
            );
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            list.setLayoutManager(new GridLayoutManager(this, 4));
        } else {
            list.setLayoutManager(new GridLayoutManager(this, 2));
        }

        list.setAdapter(new ImagesAdapter(this));

        middleSheet.setOnClickListener(v -> {
            if (middleSheet.isEnabled()) {
                middleSheet.animate()
                        .translationY(bottomSheet.getHeight());
            } else {
                middleSheet.animate()
                        .translationY(0f);
            }
        });

        mButtonAction.setOnClickListener(v -> {
            Bundle bundle = null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                final View view = findViewById(R.id.iv_scull);
                if (view != null) {
                    final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                            this,
                            view,
                            this.getString(R.string.this_is_not_a_shrine)
                    );
                    bundle = options.toBundle();
                }
            }

            final Intent intent = new Intent(this, DialogActivity.class);
            if (bundle == null) startActivity(intent);
            else startActivity(intent, bundle);
        });
    }
}
