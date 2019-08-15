package com.example.picassotutorial.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.picassotutorial.R
import kotlinx.android.synthetic.main.middle_sheet.*
import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picassotutorial.adapters.ImagesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.INTERNET),
                123
            )
        }

        val list: RecyclerView = view_list

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            list.layoutManager = GridLayoutManager(this, 4)
        } else {
            list.layoutManager = GridLayoutManager(this, 2)
        }

        list.adapter = ImagesAdapter(this@MainActivity)

        menu?.setOnClickListener {
            if (middleSheet.isEnabled) {
                middleSheet.animate()
                    .translationY(bottomSheet.height.toFloat())
            } else {
                middleSheet.animate()
                    .translationY(0f)
            }
            middleSheet.isEnabled = !middleSheet.isEnabled
        }

        btn_action?.setOnClickListener {
            var bundle: Bundle? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                val view: View? = this@MainActivity.findViewById(R.id.iv_scull)
                if (view != null) {
                    val options: ActivityOptions? = ActivityOptions.makeSceneTransitionAnimation(
                        this@MainActivity,
                        view,
                        this@MainActivity.getString(R.string.this_is_not_a_shrine)
                    )
                    bundle = options?.toBundle()
                }
            }

            val intent = Intent(this@MainActivity, DialogActivity::class.java)
            if (bundle == null) this@MainActivity.startActivity(intent)
            else this@MainActivity.startActivity(intent, bundle)
        }
    }
}
