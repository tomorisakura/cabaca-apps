package com.grepy.msx.cabaca.ui.category

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.network.NetworkConfig
import com.grepy.msx.cabaca.repository.RemoteRepository
import com.grepy.msx.cabaca.utils.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryBookViewModel : ViewModel() {

    private val remoteRepository : RemoteRepository = RemoteRepository(NetworkConfig)
    private var bookLiveData : MutableLiveData<MutableList<Book>> = MutableLiveData()

    internal fun getBookByCategory(id : Int, context: Context) : LiveData<MutableList<Book>> {
        viewModelScope.launch {
            val response = remoteRepository.getCategoryBook(id)
            when(response.status) {
                ResultResponse.Status.SUCCESS -> {
                    response.data?.result.let {
                        bookLiveData.postValue(it)
                    }
                }

                ResultResponse.Status.LOADING -> toast("loading", context)

                ResultResponse.Status.ERROR -> toast("RTO \uD83D\uDE25", context)
            }
        }
        return bookLiveData
    }

    private fun toast(msg : String, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}