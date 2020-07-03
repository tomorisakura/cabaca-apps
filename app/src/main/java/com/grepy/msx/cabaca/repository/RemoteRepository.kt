package com.grepy.msx.cabaca.repository

import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.model.CategoryResponse
import com.grepy.msx.cabaca.model.DetailBookResponse
import com.grepy.msx.cabaca.model.WriterResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.network.SafeApiRequest
import com.grepy.msx.cabaca.utils.Constant
import com.grepy.msx.cabaca.utils.ResultResponse

open class RemoteRepository : SafeApiRequest() {

    suspend fun getCategoryItems() : CategoryResponse {
        return safeApiCall { NetworkConfig.getApi().getCategoryItems(Constant.HEADERS) }
    }

    suspend fun getNewBookItems() : BookResponse {
        return safeApiCall { NetworkConfig.getApi().getNewBook(Constant.HEADERS, 10) }
    }

    suspend fun getBookDetail(id : Int) : DetailBookResponse {
        return safeApiCall { NetworkConfig.getApi().getBookDetail(Constant.HEADERS, id) }
    }

    suspend fun getCategoryBook(id: Int) : BookResponse {
        return safeApiCall { NetworkConfig.getApi().getCategoryBook(Constant.HEADERS, id) }
    }

    suspend fun getWriterId(id: Int) : WriterResponse {
        return safeApiCall { NetworkConfig.getApi().getUserById(Constant.HEADERS, id) }
    }

}