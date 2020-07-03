package com.grepy.msx.cabaca.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grepy.msx.cabaca.model.BookResponse
import com.grepy.msx.cabaca.model.CategoryResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.repository.RemoteRepository
import com.grepy.msx.cabaca.utils.ResultResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val remoteRepository : RemoteRepository by lazy { RemoteRepository() }

    private val bookData : MutableLiveData<ResultResponse<BookResponse>> = MutableLiveData()
    private val categoryData : MutableLiveData<ResultResponse<CategoryResponse>> = MutableLiveData()

    fun getCategory() : LiveData<ResultResponse<CategoryResponse>> {
        viewModelScope.launch {
            categoryData.postValue(ResultResponse.loading(null, "Loading"))
            try {
                val response = remoteRepository.getCategoryItems()
                response.let {
                    categoryData.postValue(ResultResponse.success(it))
                }
            } catch (e : Exception) {
                categoryData.postValue(ResultResponse.error(null, e.message.toString()))
            }
        }
        return categoryData
    }

    fun getNewBook() : LiveData<ResultResponse<BookResponse>> {
        viewModelScope.launch {
            bookData.postValue(ResultResponse.loading(null, "Loading"))
            try {
                val response = remoteRepository.getNewBookItems()
                response.let {
                    bookData.postValue(ResultResponse.success(it))
                }
            }catch (e : Exception) {
                bookData.postValue(ResultResponse.error(null, e.message.toString()))
            }
        }
        return bookData
    }

}
