package com.grepy.msx.cabaca.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Karya
import com.grepy.msx.cabaca.utils.Constant
import kotlinx.android.synthetic.main.list_new_book.view.*

class KaryaAdapter : RecyclerView.Adapter<KaryaAdapter.KaryaViewHolder>() {

    private val karyaList : MutableList<Karya> = mutableListOf()

    inner class KaryaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(karya: Karya) {
            val url = Constant.BASE_URL_IMAGE+karya.cover+ Constant.API_KEY_IMAGE
            itemView.let {
                Glide.with(it.context).load(url).into(it.thumb_new_book)
                it.tv_title_new_book.text = karya.title
                it.tv_writer_new_book.text = karya.status
                it.tv_rate_new_book.text = karya.rateSum.toString()
            }
        }
    }

    internal fun addKarya(ky : List<Karya>) {
        karyaList.clear()
        karyaList.addAll(ky)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KaryaViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_new_book, parent, false)
        return KaryaViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return karyaList.size
    }

    override fun onBindViewHolder(holder: KaryaViewHolder, position: Int) {
        holder.bind(karyaList[position])
    }
}