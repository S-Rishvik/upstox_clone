package com.example.domain.usecases

import com.example.domain.model.StockHoldings
import com.example.domain.repository.StockHoldingsRepository
import com.example.domain.util.SafeResult

class GetStockHoldingsUseCase(private val stockHoldingsRepository: StockHoldingsRepository) {

  suspend fun getStockHoldings(): SafeResult<StockHoldings>{
    return stockHoldingsRepository.getStockHoldings()
  }
}