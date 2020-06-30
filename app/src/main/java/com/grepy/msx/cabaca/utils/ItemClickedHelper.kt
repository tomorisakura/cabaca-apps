package com.grepy.msx.cabaca.utils

import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.Category
import com.grepy.msx.cabaca.model.RelatedBook

interface ItemClickedHelper {
    fun itemClickedNewBook(book: Book)
}

interface RelatedBookHelper{
    fun itemClickRelated(relatedBook: RelatedBook)
}

interface CategoryBookHelper{
    fun itemCategoryClicked(category: Category)
}