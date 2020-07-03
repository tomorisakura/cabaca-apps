package com.grepy.msx.cabaca.ui.category

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.repository.RemoteRepository
import com.grepy.msx.cabaca.utils.ResultResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class CategoryBookViewModel : ViewModel() {

    private val remoteRepository : RemoteRepository by lazy { RemoteRepository() }
    private var bookLiveData : MutableLiveData<ResultResponse<BookResponse>> = MutableLiveData()

    internal fun getBookByCategory(id : Int, context: Context) : LiveData<ResultResponse<BookResponse>> {
        viewModelScope.launch {
            bookLiveData.postValue(ResultResponse.loading(null, "Loading"))
            try {
                val response = remoteRepository.getCategoryBook(id)
                bookLiveData.postValue(ResultResponse.success(response))
            } catch (e : Exception) {
                bookLiveData.postValue(ResultResponse.error(null, e.message.toString()))
            }
        }
        return bookLiveData
    }
}