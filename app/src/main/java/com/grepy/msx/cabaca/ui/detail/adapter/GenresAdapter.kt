package com.grepy.msx.cabaca.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.DetailBook
import com.grepy.msx.cabaca.model.Genres
import kotlinx.android.synthetic.main.list_genres_detail.view.*

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private val listGenres : MutableList<Genres> = mutableListOf()

    inner class GenresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(genres: Genres) {
            itemView.tv_genres.text = genres.title
        }
    }

    internal fun addListGenres(gr : MutableList<Genres>) {
        listGenres.clear()
        listGenres.addAll(gr)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_genres_detail, parent, false)
        return GenresViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return listGenres.size
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(listGenres[position])
    }
}