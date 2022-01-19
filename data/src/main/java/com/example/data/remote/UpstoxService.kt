package com.example.data.remote

import com.example.domain.model.StockHoldings
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UpstoxService {

  companion object {
    fun getRetrofitService(): UpstoxService {
      val okHttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BODY
        }
      }
      val retrofit =
        Retrofit.Builder()
          .baseUrl("https://run.mocky.io/")
          .addConverterFactory(GsonConverterFactory.create())
          .client(OkHttpClient.Builder().addInterceptor(okHttpLoggingInterceptor).build())
          .build()
      return retrofit.create(UpstoxService::class.java)
    }
  }

  @GET("v3/6d0ad460-f600-47a7-b973-4a779ebbaeaf")
  suspend fun getHoldings(): StockHoldings
}