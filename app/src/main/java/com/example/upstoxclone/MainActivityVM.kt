package com.example.upstoxclone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.StockHoldings
import com.example.domain.model.StockHoldings.Stock
import com.example.domain.usecases.GetStockHoldingsUseCase
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.NetworkError
import com.example.domain.util.SafeResult.Success
import com.example.upstoxclone.ViewState.NoInternet
import kotlinx.coroutines.launch

class MainActivityVM(private val getStockHoldingsUseCase: GetStockHoldingsUseCase) : ViewModel() {

  private val _stockHoldingsLiveData = MutableLiveData<ViewState<StockHoldings>>()
  val stockHoldingsLiveData: LiveData<ViewState<StockHoldings>> = _stockHoldingsLiveData

  var currentValue = 0
  var totalInvestment = 0
  var todayPNL = 0
  var totalPNL = 0

  fun getStockHoldings() = viewModelScope.launch {
    _stockHoldingsLiveData.value = ViewState.Loading
    val result = getStockHoldingsUseCase.getStockHoldings()
    _stockHoldingsLiveData.value = when (result) {
      is Failure -> ViewState.Failure(result.message)
      NetworkError -> NoInternet
      is Success -> {
        calculate(result.data)
        ViewState.Success(result.data)
      }
    }
  }

  private fun calculate(stockHoldings: StockHoldings?){
    calculateCurrentValue(stockHoldings)
    calculateTotalInvestment(stockHoldings)
    calculateTotalPNL()
    calculateTodayPNL(stockHoldings)
  }

  private fun calculateCurrentValue(stockHoldings: StockHoldings?){
    stockHoldings?.data?.forEach { stock ->
      stock?.run {
        totalInvestment+= avgPrice?.toDouble()?.toInt()?.times((quantity?:0))?:0
      }
    }
  }

  private fun calculateTotalInvestment(stockHoldings: StockHoldings?){
    stockHoldings?.data?.forEach { stock ->
      stock?.run {
        currentValue+= ltp?.times((quantity?:0))?.toInt()?:0
      }
    }
  }

  private fun calculateTodayPNL(stockHoldings: StockHoldings?){
    stockHoldings?.data?.forEach { stock ->
      stock?.run {
        close?.let { c->
          ltp?.let { l->
            todayPNL += (c-l).times((quantity?:0)).toInt()
          }
        }

      }
    }
  }

  private fun calculateTotalPNL(){
    totalPNL = currentValue - totalInvestment
  }


}