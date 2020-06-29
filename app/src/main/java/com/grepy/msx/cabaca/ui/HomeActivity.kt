package com.grepy.msx.cabaca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        Navigation.findNavController(this, R.id.fragment_home_container)
    }
}