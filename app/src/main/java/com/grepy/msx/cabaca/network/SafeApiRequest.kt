package com.grepy.msx.cabaca.network




import com.grepy.msx.cabaca.utils.ApiException
import org.json.JSONException
import retrofit2.Response

open class SafeApiRequest {

    suspend fun <T : Any> safeApiCall(call : suspend () -> Response<T>) : T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error= response.errorBody()
            val message = StringBuilder()
            error.let {
                try {
                    message.append(it.toString())
                }catch (e : JSONException) {
                    message.append("\n")
                }
            }
            message.append("Error Code : ${response.code()}")
            throw ApiException(message.toString())
        }
    }

}