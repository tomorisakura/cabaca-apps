package com.grepy.msx.cabaca.network


import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.model.CategoryResponse
import com.grepy.msx.cabaca.model.DetailBookResponse
import com.grepy.msx.cabaca.model.WriterResponse
import com.grepy.msx.cabaca.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("book/uptodate")
    suspend fun getNewBook(@Header(Constant.X_HEADER) apikey : String, @Query("limit") limit : Int) : Response<BookResponse>

    @GET("cabaca/_table/genre")
    suspend fun getCategoryItems(@Header(Constant.X_HEADER) apikey: String) : Response<CategoryResponse>

    @GET("book/detail/{book_id}")
    suspend fun getBookDetail(@Header(Constant.X_HEADER) apikey: String, @Path("book_id") bookId : Int) : Response<DetailBookResponse>

    @GET("book/category")
    suspend fun getCategoryBook(@Header(Constant.X_HEADER) apikey: String, @Query("id") categoryId : Int) : Response<BookResponse>

    @GET("writer/detail/{user_id}")
    suspend fun getUserById(@Header(Constant.X_HEADER) apikey: String, @Path("user_id") userId : Int) : Response<WriterResponse>

}