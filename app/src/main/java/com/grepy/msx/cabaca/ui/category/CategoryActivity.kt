package com.grepy.msx.cabaca.ui.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.ui.detail.DetailActivity
import com.grepy.msx.cabaca.utils.ItemClickedHelper
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private val args : CategoryActivityArgs by navArgs()
    private val categoryBookViewModel : CategoryBookViewModel by lazy { ViewModelProvider(this).get(CategoryBookViewModel::class.java) }
    private lateinit var categoryBookAdapter : CategoryBookAdapter
    private var catId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setSupportActionBar(category_toolbar)
        supportActionBar?.title = "Kategori"
        catId = args.categoryID
        prepareObserver()
    }

    private fun prepareObserver() {
        categoryBookAdapter = CategoryBookAdapter()
        rv_category_item.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv_category_item.adapter = categoryBookAdapter
        categoryBookViewModel.getBookByCategory(catId).observe(this, Observer {
            categoryBookAdapter.addBook(it)
        })
        categoryBookAdapter.onBookItemClicked(object : ItemClickedHelper{
            override fun itemClickedNewBook(book: Book) {
                sendToDetail(book)
            }

        })
    }

    private fun sendToDetail(book: Book) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.NEW_BOOK, book)
        startActivity(intent)
        Log.d("CATEGORY_ITEM", "${book.titleBook} , ${book.id}")
    }
}