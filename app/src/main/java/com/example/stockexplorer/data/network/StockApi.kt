// data/network/StockApiService.kt
package com.example.stockexplorer.data.network

import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {
    @GET("query?function=GLOBAL_QUOTE")
    suspend fun getGlobalQuote(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = StockApi.API_KEY
    ):  Map<String, Any>

    // You can add endpoints here for top gainers / losers if your API provides them or
    // use dummy data for now and later integrate proper endpoints.
}

object StockApi {
    private const val BASE_URL = "https://www.alphavantage.co/"

    const val API_KEY = "YOUR_API_KEY_HERE"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: StockApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockApiService::class.java)
    }
}
