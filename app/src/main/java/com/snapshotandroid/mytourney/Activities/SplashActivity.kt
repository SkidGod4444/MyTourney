package com.snapshotandroid.mytourney.Activities

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Pair
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.snapshotandroid.mytourney.R

class SplashActivity : AppCompatActivity() {


    private val SPLASH_DELAY: Long = 5000 // 5 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)




        Handler().postDelayed({
            // Redirect to LoginActivity after the delay
            val intent = Intent(this, LoginActivity::class.java)
            val image = findViewById<View>(R.id.logo)
            val txt = findViewById<View>(R.id.name_txt)
            val pairs = arrayOf(
                Pair<View, String>(image, "logo_img"),
                Pair<View, String>(txt, "name_txt")
            )
            val options = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity, *pairs)
            startActivity(intent, options.toBundle())
            finish()
        }, SPLASH_DELAY)
    }
}
