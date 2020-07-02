package com.grepy.msx.cabaca.network



import android.accounts.NetworkErrorException
import com.grepy.msx.cabaca.utils.ResultResponse
import retrofit2.Response
import java.util.concurrent.TimeoutException

open class SafeApiRequest {

    suspend fun <T : Any> safeApiCall(call : suspend () -> Response<T>) : ResultResponse<T> {

        try {
            val response = call.invoke()
            var data : T? = null

            return if (response.isSuccessful) {
                data = response.body()
                ResultResponse.success(data)
            } else {
                ResultResponse.error(null, "${response.code()}, ${response.message()}")
            }

        }catch (e : Exception) {
            return when(e) {
                is TimeoutException -> ResultResponse.loading(null, "Loading")
                is NetworkErrorException -> ResultResponse.error(null, "Network Connection Error")
                else -> error(e.message.toString())
            }
        }
    }

}