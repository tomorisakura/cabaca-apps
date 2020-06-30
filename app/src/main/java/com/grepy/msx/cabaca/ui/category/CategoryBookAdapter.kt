package com.grepy.msx.cabaca.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.utils.Constant
import com.grepy.msx.cabaca.utils.ItemClickedHelper
import kotlinx.android.synthetic.main.list_category_book.view.*

class CategoryBookAdapter : RecyclerView.Adapter<CategoryBookAdapter.CategoryBookViewHolder>() {

    private var bookList : MutableList<Book> = mutableListOf()
    private var itemClickedHelper : ItemClickedHelper? = null

    inner class CategoryBookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(book: Book) {
            itemView.let {view ->
                val url = Constant.BASE_URL_IMAGE+book.coverUrl+ Constant.API_KEY_IMAGE
                Glide.with(view).load(url).placeholder(R.drawable.ic_launcher_foreground).into(view.thumb_cat_book)
                view.tv_title_cat_book.text = book.titleBook
                view.tv_writer_cat_book.text = book.writerByWriterId.userByUser.name
                view.tv_rate_cat_book.text = book.rateSum.toString()
                view.setOnClickListener { itemClickedHelper?.itemClickedNewBook(book) }
            }
        }
    }

    internal fun addBook(book : List<Book>) {
        bookList.clear()
        bookList.addAll(book)
        notifyDataSetChanged()
    }

    internal fun onBookItemClicked(itemClickedHelper: ItemClickedHelper) {
        this.itemClickedHelper = itemClickedHelper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryBookViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_category_book, parent, false)
        return CategoryBookViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: CategoryBookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

}