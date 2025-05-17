package com.example.stockexplorer.data.repository

import com.example.stockexplorer.ui.screen.StockItem
import kotlinx.coroutines.delay

/**
 * Repository class for stock-related data operations.
 * This is a simplified implementation that returns mock data.
 * In a real app, this would make API calls to a stock service.
 */
class StockRepository {

    // Demo data for trending stocks
    private val demoStocks = listOf(
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

    // Additional stocks for gainers & losers
    private val topGainersData = listOf(
        StockItem("NVDA", "NVIDIA Corporation", "$950.50", 8.2, 1.85),
        StockItem("AMD", "Advanced Micro Devices, Inc.", "$165.70", 7.1, 1.65),
        StockItem("PLTR", "Palantir Technologies Inc.", "$24.85", 6.7, 1.58),
        StockItem("TSLA", "Tesla, Inc.", "$238.45", 6.2, 1.43),
        StockItem("AAPL", "Apple Inc.", "$185.25", 5.7, 1.32),
        StockItem("CRM", "Salesforce, Inc.", "$275.50", 4.6, 1.09),
        StockItem("MSFT", "Microsoft Corporation", "$415.75", 4.3, 0.98),
        StockItem("AMZN", "Amazon.com Inc.", "$178.30", 3.8, 0.87),
        StockItem("META", "Meta Platforms, Inc.", "$482.35", 3.5, 0.79),
        StockItem("GOOG", "Alphabet Inc.", "$175.90", 2.9, 0.65)
    )

    private val topLosersData = listOf(
        StockItem("PFE", "Pfizer Inc.", "$28.15", -5.1, -1.45),
        StockItem("INTC", "Intel Corporation", "$32.45", -4.8, -1.38),
        StockItem("NKE", "Nike, Inc.", "$87.95", -4.5, -1.25),
        StockItem("DIS", "The Walt Disney Company", "$98.25", -3.2, -0.98),
        StockItem("KO", "The Coca-Cola Company", "$58.70", -2.3, -0.76),
        StockItem("JPM", "JPMorgan Chase & Co.", "$172.40", -2.1, -0.67),
        StockItem("WMT", "Walmart Inc.", "$65.80", -1.9, -0.58),
        StockItem("IBM", "International Business Machines", "$165.30", -1.7, -0.52),
        StockItem("T", "AT&T Inc.", "$18.40", -1.5, -0.45),
        StockItem("VZ", "Verizon Communications Inc.", "$41.25", -1.2, -0.37)
    )

    /**
     * Get information for a specific stock by symbol.
     * In a real app, this would fetch data from an API.
     */
    suspend fun getStock(symbol: String): String {
        // Simulate network delay
        delay(800)

        // Find the stock by symbol
        val stock = demoStocks.find { it.symbol == symbol }

        return if (stock != null) {
            "${stock.name} (${stock.symbol}): ${stock.price}"
        } else {
            "Stock not found: $symbol"
        }
    }

    /**
     * Search for stocks by query.
     * Returns stocks whose symbol or name contains the query.
     */
    suspend fun searchStocks(query: String): List<StockItem> {
        // Simulate network delay
        delay(800)

        // If query is empty, return empty list
        if (query.isBlank()) return emptyList()

        // Case-insensitive search
        val lowerCaseQuery = query.lowercase()

        // Filter stocks by symbol or name
        return demoStocks.filter { stock ->
            stock.symbol.lowercase().contains(lowerCaseQuery) ||
                    stock.name.lowercase().contains(lowerCaseQuery)
        }
    }

    /**
     * Get trending stocks.
     * In a real app, this would fetch data from an API.
     */
    suspend fun getTrendingStocks(): List<StockItem> {
        // Simulate network delay
        delay(600)

        // In a real app, this would return actual trending stocks
        // For now, just return the first 5 demo stocks
        return demoStocks.take(5)
    }

    /**
     * Get top gainers - stocks with the highest positive percentage change
     * In a real app, this would fetch data from an API.
     */
    suspend fun getTopGainers(): List<StockItem> {
        // Simulate network delay
        delay(700)

        // Return pre-defined top gainers data
        return topGainersData
    }

    /**
     * Get top losers - stocks with the highest negative percentage change
     * In a real app, this would fetch data from an API.
     */
    suspend fun getTopLosers(): List<StockItem> {
        // Simulate network delay
        delay(700)

        // Return pre-defined top losers data
        return topLosersData
    }
}