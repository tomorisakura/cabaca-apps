package com.grepy.msx.cabaca.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.BookChapter
import kotlinx.android.synthetic.main.list_book_chapter.view.*

class ChapterAdapter : RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {

    private val chapterList : MutableList<BookChapter> = mutableListOf()

    inner class ChapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(bookChapter: BookChapter) {
            itemView.let {
                it.tv_chapter_count.text = "Chapter ${bookChapter.chapterCount}"
                it.tv_chapter_title.text = bookChapter.title
            }
        }
    }

    internal fun addChapter(cb : MutableList<BookChapter>) {
        chapterList.clear()
        chapterList.addAll(cb)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_book_chapter , parent, false)
        return ChapterViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.bind(chapterList[position])
    }

}