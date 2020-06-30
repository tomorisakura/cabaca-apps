package com.grepy.msx.cabaca.repository

import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.model.CategoryResponse
import com.grepy.msx.cabaca.model.DetailBookResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.await

open class RemoteRepository(private val networkConfig: NetworkConfig) {

    suspend fun getCategoryItems() : Call<CategoryResponse> = withContext(Dispatchers.Default) {
        networkConfig.getApi().getCategoryItems(Constant.HEADERS)
    }

    suspend fun getNewBookItems() : Call<BookResponse> = withContext(Dispatchers.Default) {
        networkConfig.getApi().getNewBook(Constant.HEADERS, 7)
    }

    suspend fun getBookDetail(id : Int) : Call<DetailBookResponse> = withContext(Dispatchers.Default) {
        networkConfig.getApi().getBookDetail(Constant.HEADERS, id)
    }

    suspend fun getCategoryBook(id: Int) : Call<BookResponse> = withContext(Dispatchers.Default) {
        networkConfig.getApi().getCategoryBook(Constant.HEADERS, id)
    }

}