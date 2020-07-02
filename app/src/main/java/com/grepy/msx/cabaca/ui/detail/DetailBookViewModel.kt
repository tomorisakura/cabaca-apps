package com.grepy.msx.cabaca.ui.detail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grepy.msx.cabaca.model.DetailBook
import com.grepy.msx.cabaca.model.Writer
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.repository.RemoteRepository
import com.grepy.msx.cabaca.utils.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailBookViewModel : ViewModel() {

    private val remoteRepository : RemoteRepository by lazy { RemoteRepository(NetworkConfig) }
    private val bookLiveData : MutableLiveData<MutableList<DetailBook>> = MutableLiveData()
    private val writerData : MutableLiveData<MutableList<Writer>> = MutableLiveData()

    fun getDetailBookById(id : Int, context: Context) : LiveData<MutableList<DetailBook>> {
        viewModelScope.launch {
            val response = remoteRepository.getBookDetail(id)
            when(response.status) {
                ResultResponse.Status.SUCCESS -> {
                    response.data?.result?.let {
                        bookLiveData.postValue(mutableListOf(it))
                    }
                }

                ResultResponse.Status.LOADING -> toast("Loading", context)

                ResultResponse.Status.ERROR -> toast("RTO \uD83D\uDE25",context)
            }
        }
        return bookLiveData
    }

    fun getWriterId(id: Int, context: Context) : LiveData<MutableList<Writer>> {
        viewModelScope.launch {
            val response = remoteRepository.getWriterId(id)
            when(response.status) {
                ResultResponse.Status.SUCCESS -> {
                    response.data?.result?.let {
                        writerData.postValue(mutableListOf(it))
                    }
                }
                ResultResponse.Status.LOADING -> toast("Loading", context)
                ResultResponse.Status.ERROR -> toast("RTO \uD83D\uDE25", context)
            }
        }
        return writerData
    }

    private fun toast(msg : String, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}