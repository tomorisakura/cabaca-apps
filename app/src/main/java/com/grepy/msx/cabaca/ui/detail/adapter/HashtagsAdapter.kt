package com.grepy.msx.cabaca.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Hashtags
import kotlinx.android.synthetic.main.list_hashtag_detail.view.*

class HashtagsAdapter : RecyclerView.Adapter<HashtagsAdapter.HashtagsViewHolder>() {

    private val hashtagsList : MutableList<Hashtags> = mutableListOf()

    inner class HashtagsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(hashtags: Hashtags) {
            itemView.tv_hashtags.text = hashtags.name
        }
    }

    internal fun addHashtags(hs : MutableList<Hashtags>) {
        hashtagsList.clear()
        hashtagsList.addAll(hs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashtagsViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_hashtag_detail, parent, false)
        return HashtagsViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return hashtagsList.size
    }

    override fun onBindViewHolder(holder: HashtagsViewHolder, position: Int) {
        holder.bind(hashtagsList[position])
    }

}