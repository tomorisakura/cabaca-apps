package com.grepy.msx.cabaca.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grepy.msx.cabaca.model.DetailBook
import com.grepy.msx.cabaca.model.DetailBookResponse
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailBookViewModel : ViewModel() {

    private val remoteRepository : RemoteRepository by lazy { RemoteRepository(NetworkConfig()) }
    private val detailBookList : MutableList<DetailBook> = mutableListOf()
    private val bookLiveData : MutableLiveData<MutableList<DetailBook>> = MutableLiveData()

    fun getDetailBookById(id : Int) : LiveData<MutableList<DetailBook>> {
        GlobalScope.launch(Dispatchers.IO) {
            remoteRepository.getBookDetail(id).enqueue(object : Callback<DetailBookResponse>{
                override fun onFailure(call: Call<DetailBookResponse>, t: Throwable) {
                    Log.e("DetailBookErr", t.message.toString())
                }

                override fun onResponse(
                    call: Call<DetailBookResponse>,
                    response: Response<DetailBookResponse>
                ) {
                    response.body()?.let {
                        detailBookList.clear()
                        detailBookList.addAll(listOf(it.result))
                    }
                    bookLiveData.postValue(detailBookList)
                }

            })
        }
        return bookLiveData
    }
}