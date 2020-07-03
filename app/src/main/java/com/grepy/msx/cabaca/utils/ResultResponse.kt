package com.grepy.msx.cabaca.utils

import android.content.Context
import android.widget.Toast
import java.io.IOException

data class ResultResponse<out T>(val status : Status, val data : T?, val msg : String?) {

    companion object {
        fun <T> success(data : T?) : ResultResponse<T> = ResultResponse(Status.SUCCESS, data, null)
        fun <T> loading(data : T? , msg : String) : ResultResponse<T> = ResultResponse(Status.LOADING, data, msg)
        fun <T> error(data : T?, msg: String?) : ResultResponse<T> = ResultResponse(Status.ERROR, data, msg)
    }
}

enum class Status{
    SUCCESS,
    LOADING,
    ERROR
}

class ApiException(msg : String) : IOException(msg)

fun toast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

