package com.grepy.msx.cabaca.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.grepy.msx.cabaca.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        Navigation.findNavController(this, R.id.fragment_home_container)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about -> {
                val title = getString(R.string.app_name)
                val url = Uri.parse("https://github.com/tomorisakura")
                val builder = MaterialAlertDialogBuilder(this)
                .setTitle(title)
                .setMessage("made with ♥ grepppi ")
                .setPositiveButton("Oke Oce !") { dialog, _ ->
                    dialog.dismiss()
                }
                    .setNeutralButton("Github") { dialog, _ ->
                        val intent = Intent(Intent.ACTION_VIEW, url)
                        startActivity(intent)
                    }
                builder.show()

            }
        }
        return true
    }
}