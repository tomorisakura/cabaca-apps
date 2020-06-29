package com.grepy.msx.cabaca.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.utils.Constant
import com.grepy.msx.cabaca.utils.ItemClickedHelper
import kotlinx.android.synthetic.main.list_new_book.view.*

class NewBookAdapter : RecyclerView.Adapter<NewBookAdapter.NewBookViewHolder>() {

    private var book : MutableList<Book> = mutableListOf()

    private var itemClicked : ItemClickedHelper? = null

    internal fun onItemClicked(listenerHelper: ItemClickedHelper) {
        this.itemClicked = listenerHelper
    }

    inner class NewBookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(book: Book) {
            itemView.let {view ->
                val url = Constant.BASE_URL_IMAGE+book.coverUrl+Constant.API_KEY_IMAGE
                Glide.with(view).load(url).into(view.thumb_new_book)
                view.tv_title_new_book.text = book.titleBook
                view.tv_writer_new_book.text = book.writerByWriterId.userByUser.name
                view.tv_rate_new_book.text = book.rateSum.toString()
                view.setOnClickListener { itemClicked?.itemClickedNewBook(book) }
            }
        }
    }

    internal fun addBook(b : List<Book>) {
        notifyDataSetChanged()
        book.clear()
        book.addAll(b)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_new_book, parent, false)
        return NewBookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return book.size
    }

    override fun onBindViewHolder(holder: NewBookViewHolder, position: Int) {
        holder.bind(book[position])
    }

}