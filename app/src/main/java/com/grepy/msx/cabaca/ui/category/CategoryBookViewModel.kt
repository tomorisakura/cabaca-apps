package com.grepy.msx.cabaca.ui.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryBookViewModel : ViewModel() {

    private val remoteRepository : RemoteRepository = RemoteRepository(NetworkConfig())
    private var bookList : MutableList<Book> = mutableListOf()
    private var bookLiveData : MutableLiveData<MutableList<Book>> = MutableLiveData()

    internal fun getBookByCategory(id : Int) : LiveData<MutableList<Book>> {
        GlobalScope.launch(Dispatchers.IO) {
            remoteRepository.getCategoryBook(id).enqueue(object : Callback<BookResponse> {
                override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                    Log.e("failureCategoryBook", t.message.toString())
                }

                override fun onResponse(
                    call: Call<BookResponse>,
                    response: Response<BookResponse>
                ) {
                    response.body()?.let {
                        bookList.clear()
                        bookList.addAll(it.result)
                    }
                    bookLiveData.postValue(bookList)
                }

            })
        }
        return bookLiveData
    }

}