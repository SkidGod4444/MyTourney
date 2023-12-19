package com.snapshotandroid.mytourney.Activities

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.snapshotandroid.mytourney.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        // get the input fields
        val nameLayout = findViewById<TextInputLayout>(R.id.name)
        val usernameLayout = findViewById<TextInputLayout>(R.id.email)
        val phoneLayout = findViewById<TextInputLayout>(R.id.phnNumber)
        val pswdLayout = findViewById<TextInputLayout>(R.id.passwd)
        val confpswdLayout = findViewById<TextInputLayout>(R.id.confpasswd)

        val username = usernameLayout.editText
        val phone = phoneLayout.editText
        val pswd = pswdLayout.editText
        val confpswd = confpswdLayout.editText
        val name = nameLayout.editText

        val registerButton = findViewById<Button>(R.id.continueBtn)

        registerButton.setOnClickListener {
            if (isInternetEnabled()) {
                val usernameVal = username?.text.toString()
                val phoneVal = phone?.text.toString()
                val pswdVal = pswd?.text.toString()
                val confpswdVal = confpswd?.text.toString()
                val nameVal = name?.text.toString()

                if (usernameVal.isEmpty()) {
                    // show error message
                    usernameLayout.error = "Email can't be empty"
                } else {
                    usernameLayout.error = null
                }

                if (phoneVal.isEmpty()) {
                    // show error message
                    phoneLayout.error = "Phone number can't be empty"
                } else {
                    phoneLayout.error = null
                }

                if (pswdVal.isEmpty()) {
                    // show error message
                    pswdLayout.error = "Password can't be empty"
                } else {
                    pswdLayout.error = null
                }

                if (confpswdVal.isEmpty()) {
                    // show error message
                    confpswdLayout.error = "Confirm password can't be empty"
                } else {
                    confpswdLayout.error = null
                }

                if (pswdVal != confpswdVal) {
                    // show error message
                    confpswdLayout.error = "Passwords doesn't match"
                } else {
                    confpswdLayout.error = null
                }

                if (usernameVal.isNotEmpty() && phoneVal.isNotEmpty() && pswdVal.isNotEmpty() && confpswdVal.isNotEmpty() && pswdVal == confpswdVal) {
                    // register the user
                    firebaseAuth.createUserWithEmailAndPassword(usernameVal, pswdVal)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success.
                                val user = firebaseAuth.currentUser
                                val mail = user?.email

                                val userDB = database.getReference("Users")
                                    .child(mail!!.replace(".", ""))



                                    userDB.child("Name").setValue(nameVal)
                                    userDB.child("Phone").setValue(phoneVal)
                                    userDB.child("TourneyCoins").setValue(0)
                                    userDB.child("Wins").setValue(0)
                                    userDB.child("Losses").setValue(0)
                                    userDB.child("Withdraws").setValue(0)

                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                // If email is already registered, show error message
                                if (task.exception?.message == "The email address is already in use by another account.") {
                                    usernameLayout.error = "Email is already registered"
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        "Registration failed. Try again after some time.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }


                            }

                        }

                }
            }
            else {
                Toast.makeText(
                    baseContext,
                    "No internet connection available",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

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


