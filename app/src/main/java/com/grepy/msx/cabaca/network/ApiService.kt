package com.grepy.msx.cabaca.network


import com.grepy.msx.cabaca.utils.Constant
import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.model.CategoryResponse
import com.grepy.msx.cabaca.model.DetailBookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("book/uptodate")
    fun getNewBook(@Header(Constant.X_HEADER) apikey : String, @Query("limit") limit : Int) : Call<BookResponse>

    @GET("cabaca/_table/genre")
    fun getCategoryItems(@Header(Constant.X_HEADER) apikey: String) : Call<CategoryResponse>

    @GET("book/detail/{book_id}")
    fun getBookDetail(@Header(Constant.X_HEADER) apikey: String, @Path("book_id") bookId : Int) : Call<DetailBookResponse>

    @GET("book/category")
    fun getCategoryBook(@Header(Constant.X_HEADER) apikey: String, @Query("id") categoryId : Int) : Call<BookResponse>

}