package com.snapshotandroid.mytourney.Activities

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.snapshotandroid.mytourney.R

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is already logged in
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            redirectToMainActivity()
        }

        // get the registration button
        val registerBtn = findViewById<Button>(R.id.register_btn)

        // set the click listener for the register button
        registerBtn.setOnClickListener {
            // redirect to the register activity
            val regIntent = Intent(this, RegisterActivity::class.java)
            startActivity(regIntent)
            finish()
        }
    }
    private fun redirectToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isInternetEnabled(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            val network = it.activeNetwork
            val capabilities = it.getNetworkCapabilities(network)
            return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        }
        return false
    }

}
