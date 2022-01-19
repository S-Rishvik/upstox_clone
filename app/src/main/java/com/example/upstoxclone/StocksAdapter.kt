package com.example.upstoxclone

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.StockHoldings.Stock
import com.example.upstoxclone.StocksAdapter.StockVH
import com.example.upstoxclone.databinding.ItemStockBinding

class StocksAdapter(private val stocks: List<Stock?>): RecyclerView.Adapter<StockVH>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockVH {
    return StockVH(ItemStockBinding.inflate(LayoutInflater.from(parent.context),parent,false))
  }

  override fun onBindViewHolder(holder: StockVH, position: Int) {
    holder.bind(stocks[position])
  }

  override fun getItemCount(): Int = stocks.size

  class StockVH(private val itemStockBinding: ItemStockBinding): RecyclerView.ViewHolder(itemStockBinding.root){
    fun bind(stock: Stock?){
      itemStockBinding.apply {
        name.text = stock?.companyName
        quantity.text = stock?.quantity.toString()
        ltp.text = stock?.ltp.toString()
        val no=getProfitAndLoss(stock)?:0
        psl.text = no.toString()
        if(no<0){
          psl.setTextColor(Color.parseColor("#B71C1C"))
        }else{
          psl.setTextColor(Color.parseColor("#1DE9B6"))
        }
      }
    }

    private fun getProfitAndLoss(stock: Stock?): Int? {
      return stock?.run {
        val bought = avgPrice?.toDouble()?.toInt()?.times((quantity?:0))?:0
        val current =  ltp?.times((quantity?:0))?.toInt()?:0
        current-bought
      }
    }
  }
}