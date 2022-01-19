package com.example.domain.repository

import com.example.domain.model.StockHoldings
import com.example.domain.util.SafeResult

interface StockHoldingsRepository {

  suspend fun getStockHoldings(): SafeResult<StockHoldings>
}