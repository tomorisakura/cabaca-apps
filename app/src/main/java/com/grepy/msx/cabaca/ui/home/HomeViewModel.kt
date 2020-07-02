package com.grepy.msx.cabaca.ui.home


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.model.Category
import com.grepy.msx.cabaca.model.CategoryResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.repository.RemoteRepository
import com.grepy.msx.cabaca.utils.Constant
import com.grepy.msx.cabaca.utils.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val remoteRepository : RemoteRepository by lazy { RemoteRepository(NetworkConfig) }

    private val bookData : MutableLiveData<MutableList<Book>> = MutableLiveData()
    private val categoryData : MutableLiveData<MutableList<Category>> = MutableLiveData()

    fun getCategory(context: Context) : LiveData<MutableList<Category>> {
        viewModelScope.launch {
            val response = remoteRepository.getCategoryItems()
            when(response.status) {
                ResultResponse.Status.SUCCESS -> {
                    response.data?.resource.let {
                        categoryData.postValue(it)
                    }
                }

                ResultResponse.Status.LOADING -> {
                    toast("Loading", context)
                }

                ResultResponse.Status.ERROR -> {
                    toast("RTO 😥", context)
                    Log.e("errResponse", "Connect your internet")
                }
            }
        }
        return categoryData
    }

    fun getNewBook(context: Context) : LiveData<MutableList<Book>> {
        viewModelScope.launch {
            val response = remoteRepository.getNewBookItems()
            when(response.status) {
                ResultResponse.Status.SUCCESS -> {
                    response.data?.result.let {
                        bookData.postValue(it)
                    }
                }

                ResultResponse.Status.LOADING -> {
                    toast("Loading", context)
                }

                ResultResponse.Status.ERROR -> {
                    toast("RTO \uD83D\uDE25", context)
                    Log.e("errResponse", "Connect your internet")
                }
            }
        }
        return bookData
    }

    private fun toast(msg : String, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}
