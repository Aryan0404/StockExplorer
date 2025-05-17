//package com.example.stockexplorer.ui
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.stockexplorer.data.model.StockViewModel
//
//@Composable
//fun StockScreen(viewModel: StockViewModel) {
//    // This collects the stock data from the ViewModel
//    val stock = viewModel.stockState.collectAsState().value
//
//    Column(
//        modifier = Modifier.Companion
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Stock Explorer",
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//        Spacer(modifier = Modifier.Companion.height(24.dp))
//
//        Button(onClick = { viewModel.fetchStock("GOOGL") }) {
//            Text("Fetch AAPL Price")
//        }
//
//        Spacer(modifier = Modifier.Companion.height(24.dp))
//
//        Text(
//            text = stock ?: "No data yet",
//            style = MaterialTheme.typography.bodyLarge
//        )
//    }
//}