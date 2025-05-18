package com.example.stockexplorer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Card displaying detailed information about a stock
 *
 * @param stock Stock to display information for
 */
@Composable
fun StockInfoCard(stock: com.example.stockexplorer.data.model.StockItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Company Information",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // This would ideally be populated with actual company info
            // from an API response, but for now we'll display placeholder data
            InfoRow("Symbol", stock.symbol)
            InfoRow("Name", stock.name)
            InfoRow("Current Price", stock.price)
            InfoRow("Change", "${stock.priceChange} (${stock.percentChange}%)")

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = DividerDefaults.Thickness,
                color = DividerDefaults.color
            )

            Text(
                text = "Key Metrics",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Placeholder metrics - these would be populated from the API
            InfoRow("Market Cap", "N/A")
            InfoRow("P/E Ratio", "N/A")
            InfoRow("52-Week High", "N/A")
            InfoRow("52-Week Low", "N/A")
            InfoRow("Volume", "N/A")
        }
    }
}

/**
 * Row displaying a label and value
 */
@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}