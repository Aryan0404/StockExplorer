package com.example.stockexplorer.data.model

/**
 * Data class representing a stock item for display in the UI
 */
data class StockItem(
    val symbol: String,
    val name: String,
    val price: String,
    val change: String,
    val changePercent: String,
    val marketCap: String,
    val sector: String,
    // Additional optional fields for more detailed information
    val open: String? = null,
    val high: String? = null,
    val low: String? = null,
    val volume: String? = null,
    val peRatio: String? = null,
    val dividend: String? = null,
    val yield: String? = null,
    val eps: String? = null,
    val beta: String? = null,
    val fiftyTwoWeekHigh: String? = null,
    val fiftyTwoWeekLow: String? = null
) {
    /**
     * Returns whether the stock price is up or down
     */
    val isPositiveChange: Boolean
        get() = change.startsWith("+")

    /**
     * Price change as a double for UI display
     */
    val priceChange: Double
        get() = change.replace("+", "").toDoubleOrNull() ?: 0.0

    /**
     * Percentage change as a double for UI display
     */
    val percentChange: Double
        get() = changePercent.replace("%", "").replace("+", "").toDoubleOrNull() ?: 0.0
}

/**
 * Data class representing historical stock price data
 */
data class StockHistoricalData(
    val date: String,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Long
)

/**
 * Response from Alpha Vantage for TimeSeries data
 */
data class TimeSeriesResponse(
    val metaData: MetaData? = null,
    val timeSeriesData: Map<String, TimeSeriesDataPoint>? = null
)

/**
 * Time series metadata
 */
data class MetaData(
    val information: String? = null,
    val symbol: String? = null,
    val lastRefreshed: String? = null,
    val outputSize: String? = null,
    val timeZone: String? = null
)

/**
 * Individual time series data point
 */
data class TimeSeriesDataPoint(
    val open: String? = null,
    val high: String? = null,
    val low: String? = null,
    val close: String? = null,
    val volume: String? = null
)