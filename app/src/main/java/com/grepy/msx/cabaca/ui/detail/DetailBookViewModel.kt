package com.grepy.msx.cabaca.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grepy.msx.cabaca.model.DetailBookResponse
import com.grepy.msx.cabaca.model.WriterResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.repository.RemoteRepository
import com.grepy.msx.cabaca.utils.ResultResponse
import kotlinx.coroutines.launch

class DetailBookViewModel : ViewModel() {

    private val remoteRepository : RemoteRepository by lazy { RemoteRepository() }
    private val bookLiveData : MutableLiveData<ResultResponse<DetailBookResponse>> = MutableLiveData()
    private val writerData : MutableLiveData<ResultResponse<WriterResponse>> = MutableLiveData()

    fun getDetailBookById(id : Int) : LiveData<ResultResponse<DetailBookResponse>> {
        viewModelScope.launch {
            bookLiveData.postValue(ResultResponse.loading(null, "Loading"))
            try {
                val response = remoteRepository.getBookDetail(id)
                response.let {
                    bookLiveData.postValue(ResultResponse.success(it))
                }
            }catch (e : Exception) {
                bookLiveData.postValue(ResultResponse.error(null, e.message.toString()))
            }
        }
        return bookLiveData
    }

    fun getWriterId(id: Int) : LiveData<ResultResponse<WriterResponse>> {
        viewModelScope.launch {
            writerData.postValue(ResultResponse.loading(null, "Loading"))
            try {
                val response = remoteRepository.getWriterId(id)
                response.let {
                    writerData.postValue(ResultResponse.success(it))
                }
            }catch (e : Exception) {
                writerData.postValue(ResultResponse.error(null, e.message.toString()))
            }
        }
        return writerData
    }

}