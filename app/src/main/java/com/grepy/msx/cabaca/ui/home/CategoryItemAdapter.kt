package com.grepy.msx.cabaca.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Category
import com.grepy.msx.cabaca.utils.CategoryBookHelper
import kotlinx.android.synthetic.main.list_category.view.*

class CategoryItemAdapter : RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {

    private val categoryItem : MutableList<Category> = mutableListOf()
    private var categoryBookHelper : CategoryBookHelper? = null

    inner class CategoryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {
            itemView.let { item ->
                item.tv_category.text = category.title
                item.setOnClickListener { categoryBookHelper?.itemCategoryClicked(category) }
            }
        }
    }

    internal fun addItems(items : MutableList<Category>) {
        notifyDataSetChanged()
        categoryItem.clear()
        categoryItem.addAll(items)
    }

    internal fun itemCategoryClicked(categoryBookHelper: CategoryBookHelper) {
        this.categoryBookHelper = categoryBookHelper
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