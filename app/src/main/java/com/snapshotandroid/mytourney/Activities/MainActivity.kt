package com.snapshotandroid.mytourney.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.snapshotandroid.mytourney.R
import com.snapshotandroid.mytourney.Fragments.*
import com.snapshotandroid.mytourney.Fragments.TourneyFragment
import com.snapshotandroid.mytourney.Fragments.UserFragment
import com.snapshotandroid.mytourney.Fragments.MatchesFragment


class MainActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNav)

        // Set the default item to be selected when the app starts
        bottomNavigationView.selectedItemId = R.id.bottom_tourney

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_sword -> {
                    replaceFragment(MatchesFragment())
                    true
                }
                R.id.bottom_tourney -> {
                    replaceFragment(TourneyFragment())
                    true
                }
                R.id.bottom_leaderboard -> {
                    replaceFragment(LbFragment())
                    true
                }
                R.id.bottom_wallet -> {
                    replaceFragment(WalletFragment())
                    true
                }
                R.id.bottom_user -> {
                    replaceFragment(UserFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(TourneyFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}