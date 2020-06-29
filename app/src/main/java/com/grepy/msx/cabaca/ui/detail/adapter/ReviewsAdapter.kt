package com.grepy.msx.cabaca.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Reviews
import com.grepy.msx.cabaca.utils.Constant
import kotlinx.android.synthetic.main.list_reviewer.view.*

class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.ReviewerViewHolder>() {

    private val reviewsList : MutableList<Reviews> = mutableListOf()

    inner class ReviewerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(reviews: Reviews) {
            itemView.let {
                val url = Constant.BASE_URL_IMAGE+reviews.reviewer.photo+ Constant.API_KEY_IMAGE
                Glide.with(it.context).load(url).placeholder(R.drawable.ic_writer_icon).into(it.img_reviewer)
                it.tv_name_reviewer.text = reviews.reviewer.name
                it.tv_email_reviewer.text = reviews.reviewer.email
                it.tv_review.text = reviews.review
            }
        }
    }

    internal fun addReviewer(rv : MutableList<Reviews>) {
        reviewsList.clear()
        reviewsList.addAll(rv)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewerViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_reviewer, parent, false)
        return ReviewerViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return reviewsList.size
    }

    override fun onBindViewHolder(holder: ReviewerViewHolder, position: Int) {
        holder.bind(reviewsList[position])
    }

}