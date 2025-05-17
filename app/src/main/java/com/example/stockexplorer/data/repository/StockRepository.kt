// data/repository/StockRepository.kt
package com.example.stockexplorer.data.repository

import com.example.stockexplorer.data.network.StockApi
import com.example.stockexplorer.ui.screen.StockItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Repository class for stock-related data operations.
 * Uses the Alpha Vantage API to fetch real stock data.
 */
class StockRepository {
    private val apiService = StockApi.api

    /**
     * Get information for a specific stock by symbol.
     */
    suspend fun getStock(symbol: String): StockItem? = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getGlobalQuote(symbol = symbol)
            response.globalQuote?.let { quote ->
                val changeValue = quote.change.toDoubleOrNull() ?: 0.0
                // The change percent comes as "1.25%" so we need to remove the % sign and parse
                val changePercent = quote.changePercent
                    .replace("%", "")
                    .toDoubleOrNull() ?: 0.0

                StockItem(
                    symbol = quote.symbol,
                    name = getCompanyName(quote.symbol), // We'll get the name from company overview
                    price = "$${quote.price}",
                    priceChange = changeValue,
                    percentChange = changePercent
                )
            }
        } catch (e: Exception) {
            // In case of error, return null
            null
        }
    }

    /**
     * Get company name from symbol by calling the company overview endpoint
     */
    private suspend fun getCompanyName(symbol: String): String {
        return try {
            val overview = apiService.getCompanyOverview(symbol = symbol)
            overview.name ?: symbol
        } catch (e: Exception) {
            // If we can't get the company name, return the symbol
            symbol
        }
    }

    suspend fun searchStocks(query: String): List<StockItem> = withContext(Dispatchers.IO) {
        try {
            // If query is empty, return empty list
            if (query.isBlank()) return@withContext emptyList()

            val response = apiService.searchStocks(keywords = query)
            response.bestMatches?.map { match ->
                StockItem(
                    symbol = match.symbol,
                    name = match.name,
                    price = "N/A", // The search endpoint doesn't provide price information
                    priceChange = 0.0,
                    percentChange = 0.0
                )
            } ?: emptyList()
        } catch (e: Exception) {
            // In case of error, return empty list
            emptyList()
        }
    }

    /**
     * Get trending stocks.
     * For this implementation, we'll use the most actively traded stocks
     * from the top gainers & losers endpoint.
     */
    suspend fun getTrendingStocks(): List<StockItem> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getTopGainersLosers()
            response.mostActivelyTraded?.map { stock ->
                val changeValue = stock.changeAmount.toDoubleOrNull() ?: 0.0
                val changePercent = stock.changePercentage
                    .replace("%", "")
                    .toDoubleOrNull() ?: 0.0

                StockItem(
                    symbol = stock.ticker,
                    name = getCompanyName(stock.ticker),
                    price = "$${stock.price}",
                    priceChange = changeValue,
                    percentChange = changePercent
                )
            }?.take(5) ?: emptyList()
        } catch (e: IOException) {
            // In case of network error, return empty list
            emptyList()
        }
    }

    /**
     * Get top gainers
     */
    suspend fun getTopGainers(): List<StockItem> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getTopGainersLosers()
            response.topGainers?.map { stock ->
                val changeValue = stock.changeAmount.toDoubleOrNull() ?: 0.0
                val changePercent = stock.changePercentage
                    .replace("%", "")
                    .toDoubleOrNull() ?: 0.0

                StockItem(
                    symbol = stock.ticker,
                    name = getCompanyName(stock.ticker),
                    price = "$${stock.price}",
                    priceChange = changeValue,
                    percentChange = changePercent
                )
            } ?: emptyList()
        } catch (e: Exception) {
            // In case of error, return empty list
            emptyList()
        }
    }

    /**
     * Get top losers
     */
    suspend fun getTopLosers(): List<StockItem> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getTopGainersLosers()
            response.topLosers?.map { stock ->
                val changeValue = stock.changeAmount.toDoubleOrNull() ?: 0.0
                val changePercent = stock.changePercentage
                    .replace("%", "")
                    .toDoubleOrNull() ?: 0.0

                StockItem(
                    symbol = stock.ticker,
                    name = getCompanyName(stock.ticker),
                    price = "$${stock.price}",
                    priceChange = changeValue,
                    percentChange = changePercent
                )
            } ?: emptyList()
        } catch (e: Exception) {
            // In case of error, return empty list
            emptyList()
        }
    }

    /**
     * Fallback method to provide demo data in case of API failures or when testing
     */
    fun getDemoStocks(): List<StockItem> {
        return listOf(
            StockItem("AAPL", "Apple Inc.", "$186.41", 1.25, 0.67),
            StockItem("MSFT", "Microsoft Corp.", "$417.22", 2.36, 0.57),
            StockItem("GOOGL", "Alphabet Inc.", "$178.25", -1.54, -0.86),
            StockItem("AMZN", "Amazon.com Inc.", "$183.75", 0.95, 0.52),
            StockItem("TSLA", "Tesla Inc.", "$174.90", -5.28, -2.93),
            StockItem("META", "Meta Platforms Inc.", "$496.18", 3.42, 0.69),
            StockItem("NVDA", "NVIDIA Corp.", "$875.33", 12.75, 1.48),
            StockItem("JPM", "JPMorgan Chase & Co.", "$195.73", -0.84, -0.43),
            StockItem("V", "Visa Inc.", "$275.54", 1.67, 0.61),
            StockItem("WMT", "Walmart Inc.", "$62.93", 0.34, 0.54)
        )
    }
}