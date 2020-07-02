package com.grepy.msx.cabaca.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Writer
import kotlinx.android.synthetic.main.activity_writer.*

class WriterActivity : AppCompatActivity() {

    private lateinit var detailBookViewModel: DetailBookViewModel

    companion object {
        const val WRITER_ITEM = "writer_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writer)
        val writer = intent.getParcelableExtra(WRITER_ITEM) as Writer
        detailBookViewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
        observer(writer)
    }

    private fun observer(writer: Writer) {
        detailBookViewModel.getWriterId(writer.user_id, this).observe(this, Observer {
            writer_data.text = it.toString()
        })
    }
}