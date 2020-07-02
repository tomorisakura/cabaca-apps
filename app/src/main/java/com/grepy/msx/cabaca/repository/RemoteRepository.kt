package com.grepy.msx.cabaca.repository

import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.model.CategoryResponse
import com.grepy.msx.cabaca.model.DetailBookResponse
import com.grepy.msx.cabaca.model.WriterResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.network.SafeApiRequest
import com.grepy.msx.cabaca.utils.Constant
import com.grepy.msx.cabaca.utils.ResultResponse

open class RemoteRepository(private val networkConfig: NetworkConfig) : SafeApiRequest() {

    suspend fun getCategoryItems() : ResultResponse<CategoryResponse> {
        return safeApiCall { networkConfig.getApi().getCategoryItems(Constant.HEADERS) }
    }

    suspend fun getNewBookItems() : ResultResponse<BookResponse> {
        return safeApiCall { networkConfig.getApi().getNewBook(Constant.HEADERS, 7) }
    }

    suspend fun getBookDetail(id : Int) : ResultResponse<DetailBookResponse> {
        return safeApiCall { networkConfig.getApi().getBookDetail(Constant.HEADERS, id) }
    }

    suspend fun getCategoryBook(id: Int) : ResultResponse<BookResponse> {
        return safeApiCall { networkConfig.getApi().getCategoryBook(Constant.HEADERS, id) }
    }

    suspend fun getWriterId(id: Int) : ResultResponse<WriterResponse> {
        return safeApiCall { networkConfig.getApi().getUserById(Constant.HEADERS, id) }
    }

}