// data/model/Stock.kt
package com.example.stockexplorer.data.model

data class Stock(
    val symbol: String,
    val price: String,
    val changePercent: String
)

// Extension to parse API response Map to Stock
fun Map<String, String>.toStock(): Stock {
    return Stock(
        symbol = this["01. symbol"] ?: "",
        price = this["05. price"] ?: "",
        changePercent = this["10. change percent"] ?: ""
    )
}
