package com.example.stockexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.stockexplorer.data.repository.StockRepository

class StockViewModel(
    private val repository: StockRepository = StockRepository()
) : ViewModel() {

    private val _stockState = MutableStateFlow<String?>(null)
    val stockState: StateFlow<String?> = _stockState

    fun fetchStock(symbol: String) {
        viewModelScope.launch {
            val result = repository.getStock(symbol)
            _stockState.value = result
        }
    }
}

