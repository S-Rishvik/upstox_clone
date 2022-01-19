package com.example.data.repository

import com.example.data.remote.UpstoxService
import com.example.data.util.safeApiCall
import com.example.domain.model.StockHoldings
import com.example.domain.repository.StockHoldingsRepository
import com.example.domain.util.SafeResult
import kotlinx.coroutines.Dispatchers

class StockHoldingsRepositoryImpl: StockHoldingsRepository {
  private val upstoxService = UpstoxService.getRetrofitService()
  override suspend fun getStockHoldings(): SafeResult<StockHoldings>{
    return safeApiCall(Dispatchers.IO){
      upstoxService.getHoldings()
    }
  }
}