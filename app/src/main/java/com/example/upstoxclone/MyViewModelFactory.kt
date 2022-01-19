package com.example.upstoxclone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.StockHoldingsRepositoryImpl
import com.example.domain.usecases.GetStockHoldingsUseCase
import java.lang.Exception
import java.lang.RuntimeException

class MyViewModelFactory: ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    when(modelClass){
      MainActivityVM::class.java -> {
        val stockHoldingsRepository = StockHoldingsRepositoryImpl()
        val getStockHoldingsUseCase = GetStockHoldingsUseCase(stockHoldingsRepository)
        return MainActivityVM(getStockHoldingsUseCase) as T
      }
      else -> throw RuntimeException("ViewModel Not Found")
    }

  }
}