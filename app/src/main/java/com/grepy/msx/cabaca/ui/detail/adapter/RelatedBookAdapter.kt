package com.grepy.msx.cabaca.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.RelatedBook
import com.grepy.msx.cabaca.utils.Constant
import com.grepy.msx.cabaca.utils.RelatedBookHelper
import kotlinx.android.synthetic.main.list_related_book.view.*

class RelatedBookAdapter : RecyclerView.Adapter<RelatedBookAdapter.RelatedViewHolder>() {

    private val relatedBook: MutableList<RelatedBook> = mutableListOf()
    private var relatedBookHelper : RelatedBookHelper? = null

    inner class RelatedViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(relatedBook: RelatedBook) {
            itemView.let {
                val url = Constant.BASE_URL_IMAGE+relatedBook.coverUrl+ Constant.API_KEY_IMAGE
                Glide.with(it).load(url).placeholder(R.drawable.ic_launcher_foreground).into(it.thumb_related_book)
                it.tv_title_related_book.text = relatedBook.title
                it.setOnClickListener { relatedBookHelper?.itemClickRelated(relatedBook) }
            }
        }
    }

    internal fun addItem(rb : MutableList<RelatedBook>) {
        relatedBook.clear()
        relatedBook.addAll(rb)
        notifyDataSetChanged()
    }

    internal fun onItemRelatedClicked(item : RelatedBookHelper) {
        this.relatedBookHelper = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_related_book, parent, false)
        return RelatedViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return relatedBook.size
    }

    override fun onBindViewHolder(holder: RelatedViewHolder, position: Int) {
        holder.bind(relatedBook[position])
    }
}