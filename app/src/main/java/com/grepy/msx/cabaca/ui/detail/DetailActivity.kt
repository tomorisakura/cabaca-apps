package com.grepy.msx.cabaca.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.RelatedBook
import com.grepy.msx.cabaca.ui.detail.viewpager.ViewPager
import com.grepy.msx.cabaca.utils.Constant
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var bookItem : Book

    companion object {
        const val NEW_BOOK = "new_book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar_detail)
        prepareView()
    }

    private fun prepareView() {
        bookItem = intent.getParcelableExtra(NEW_BOOK) as Book
        viewPager = ViewPager(this, bookItem ,supportFragmentManager)
        view_pager.adapter = viewPager
        tab_layout.setupWithViewPager(view_pager)
        supportActionBar?.title = bookItem.titleBook
        setView(bookItem)
    }

    private fun setView(book: Book) {
        val url = Constant.BASE_URL_IMAGE+book.coverUrl+ Constant.API_KEY_IMAGE
        Glide.with(this).load(url).placeholder(R.drawable.ic_launcher_foreground).into(thumb_detail_book)
    }

    private fun setViewRelated(relatedBook: RelatedBook) {
        val url = Constant.BASE_URL_IMAGE+relatedBook.coverUrl+ Constant.API_KEY_IMAGE
        Glide.with(this).load(url).placeholder(R.drawable.ic_launcher_foreground).into(thumb_detail_book)
    }
}
