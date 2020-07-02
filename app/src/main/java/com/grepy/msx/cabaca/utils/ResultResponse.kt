package com.grepy.msx.cabaca.utils

data class ResultResponse<out T>(val status : Status, val data : T?, val msg : String?) {

    companion object {
        fun <T> success(data : T?) : ResultResponse<T> = ResultResponse(Status.SUCCESS, data, msg = null)
        fun <T> loading(data : T? , msg : String) : ResultResponse<T> = ResultResponse(Status.LOADING, data, msg)
        fun <T> error(data : T?, msg: String?) : ResultResponse<T> = ResultResponse(Status.ERROR, null, msg)
    }

    enum class Status{
        SUCCESS,
        LOADING,
        ERROR
    }
}

