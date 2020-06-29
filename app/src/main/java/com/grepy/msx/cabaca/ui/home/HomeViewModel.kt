package com.grepy.msx.cabaca.ui.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.model.Category
import com.grepy.msx.cabaca.model.CategoryResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.repository.RemoteRepository
import com.grepy.msx.cabaca.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val remoteRepository : RemoteRepository by lazy { RemoteRepository(NetworkConfig()) }

    private var bookList : MutableList<Book> = mutableListOf()
    private val bookData : MutableLiveData<MutableList<Book>> = MutableLiveData()

    private var categoryList : MutableList<Category> = mutableListOf()
    private val categoryData : MutableLiveData<MutableList<Category>> = MutableLiveData()

    fun getCategory() : LiveData<MutableList<Category>> {
        GlobalScope.launch(Dispatchers.IO) {
            remoteRepository.getCategoryItems().enqueue(object : Callback<CategoryResponse>{
                override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                    Log.e(Constant.FAIL_TAG, t.message.toString())
                }

                override fun onResponse(call: Call<CategoryResponse>,response: Response<CategoryResponse>) {
                    response.body()?.let {
                        categoryList.addAll(it.resource)
                    }
                    categoryData.postValue(categoryList)
                }
            })
        }
        return categoryData
    }

    fun getNewBook() : LiveData<MutableList<Book>> {
        GlobalScope.launch(Dispatchers.IO) {
            remoteRepository.getNewBookItems().enqueue(object : Callback<BookResponse>{

                override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                    Log.e(Constant.FAIL_TAG, t.message.toString())
                }

                override fun onResponse(call: Call<BookResponse>,response: Response<BookResponse>) {
                    response.body()?.let {
                        bookList.addAll(it.result)
                    }
                    bookData.postValue(bookList)
                }

            })
        }
        return bookData
    }

}
