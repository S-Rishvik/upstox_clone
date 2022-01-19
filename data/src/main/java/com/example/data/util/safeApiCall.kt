package com.example.data.util

import android.util.Log
import com.example.domain.util.SafeResult
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.NetworkError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
  dispatcher: CoroutineDispatcher,
  apiCall: suspend () -> T,
): SafeResult<T> {
  return withContext(dispatcher) {
    try {
      SafeResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
      Log.i("Api call error",throwable.message.orEmpty())
      when (throwable) {
        is IOException -> NetworkError
        is HttpException -> Failure(exception = throwable, message = throwable.message())
        else -> Failure(Exception(throwable), throwable.message.orEmpty())
      }
    }
  }
}