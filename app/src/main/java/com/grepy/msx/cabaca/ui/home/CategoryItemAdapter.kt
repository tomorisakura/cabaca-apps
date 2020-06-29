package com.grepy.msx.cabaca.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Category
import kotlinx.android.synthetic.main.list_category.view.*

class CategoryItemAdapter : RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {

    private val categoryItem : MutableList<Category> = mutableListOf()

    inner class CategoryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {
            itemView.let { item ->
                item.tv_category.text = category.title
            }
        }
    }

    internal fun addItems(items : MutableList<Category>) {
        notifyDataSetChanged()
        categoryItem.clear()
        categoryItem.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_category, parent, false)
        return CategoryItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(categoryItem[position])
    }

}