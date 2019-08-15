package com.example.picassotutorial.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.picassotutorial.R
import kotlinx.android.synthetic.main.middle_sheet.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
