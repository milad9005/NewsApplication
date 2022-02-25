package com.example.newsapp.data.Network

import com.example.newsapp.common.NetworkResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.*
import java.lang.reflect.Type

internal class DeferredNetworkResponseAdapter<T : Any, U : Any>(
    private val successBodyType: Type,
    private val errorConverter: Converter<ResponseBody, U>
) : CallAdapter<T, Deferred<NetworkResponse<T, U>>> {

    /**
     * This is used to determine the parameterize type of the object
     * being handled by this adapter. For example, the response type
     * in Call<Repo> is Repo.
     */
    override fun responseType(): Type = successBodyType

    /**
     * Returns an instance of [T] by modifying a [Call] object
     *
     * @param call The call object to be converted
     * @return The T instance wrapped in a [NetworkResponse] class wrapped in [Deferred]
     */
    override fun adapt(call: Call<T>): Deferred<NetworkResponse<T, U>> {
        val deferred = CompletableDeferred<NetworkResponse<T, U>>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                call.cancel()
            }
        }

        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, throwable: Throwable) {
                try {
                    val networkResponse = throwable.extractNetworkResponse<T, U>(errorConverter)
                    deferred.complete(networkResponse)
                } catch (t: Throwable) {
                    deferred.completeExceptionally(t)
                }
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResponse =
                    ResponseHandler.handle(response, successBodyType, errorConverter)
                deferred.complete(networkResponse)
            }
        })

        return deferred
    }
}