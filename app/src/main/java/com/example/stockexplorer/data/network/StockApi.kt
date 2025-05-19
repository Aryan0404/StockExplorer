package com.example.stockexplorer.data.network

import com.example.stockexplorer.data.model.CompanyOverviewResponse
import com.example.stockexplorer.data.model.GlobalQuoteResponse
import com.example.stockexplorer.data.model.SearchResponse
import com.example.stockexplorer.data.model.StockHistoricalData
import com.example.stockexplorer.data.model.TopGainersLosersResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {

    @GET("query")
    suspend fun getGlobalQuote(
        @Query("function") function: String = "GLOBAL_QUOTE",
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = StockApi.API_KEY
    ): GlobalQuoteResponse
    @GET("query")
    suspend fun searchStocks(
        @Query("function") function: String = "SYMBOL_SEARCH",
        @Query("keywords") keywords: String,
        @Query("apikey") apiKey: String = StockApi.API_KEY
    ): SearchResponse

    /**
     * Get company overview information
     */
    @GET("query")
    suspend fun getCompanyOverview(
        @Query("function") function: String = "OVERVIEW",
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = StockApi.API_KEY
    ): CompanyOverviewResponse

    /**
     * Get top gainers and losers
     */
    @GET("query")
    suspend fun getTopGainersLosers(
        @Query("function") function: String = "TOP_GAINERS_LOSERS",
        @Query("apikey") apiKey: String = StockApi.API_KEY
    ): TopGainersLosersResponse

    /**
     * Get historical time series stock data (daily)
     */
    @GET("query")
    suspend fun getHistoricalData(
        @Query("function") function: String = "TIME_SERIES_DAILY",
        @Query("symbol") symbol: String,
        @Query("outputsize") outputSize: String = "compact",
        @Query("apikey") apiKey: String = StockApi.API_KEY
    ): List<StockHistoricalData>
}

object StockApi {
    private const val BASE_URL = "https://www.alphavantage.co/"
    const val API_KEY = "Z8R9IT39SR42KD1K"
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