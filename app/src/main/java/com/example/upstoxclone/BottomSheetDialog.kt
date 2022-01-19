package com.example.upstoxclone

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.upstoxclone.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog: BottomSheetDialogFragment() {
  lateinit var binding: BottomSheetLayoutBinding
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = BottomSheetLayoutBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.currentValue.text = arguments?.getString(CURRENT_VALUE).toString()
    binding.totalInvestment.text = arguments?.getInt(TOTAL_INVESTMENT).toString()
    binding.todayProfitLoss.text = arguments?.getInt(TODAY_PROFIT_LOSS).toString()
    binding.totalProfitLoss.text = arguments?.getInt(TOTAL_PROFIT_LOSS).toString()
    setColours(binding.todayProfitLoss,arguments?.getInt(TODAY_PROFIT_LOSS)?:0)
    setColours(binding.totalProfitLoss,arguments?.getInt(TOTAL_PROFIT_LOSS)?:0)
  }

  private fun setColours(textView: TextView,no: Int){
    if(no<0){
      textView.setTextColor(Color.parseColor("#B71C1C"))
    }else{
      textView.setTextColor(Color.parseColor("#1DE9B6"))
    }
  }


  companion object{
    const val CURRENT_VALUE="current_value"
    const val TOTAL_INVESTMENT="total_investment"
    const val TODAY_PROFIT_LOSS="today_profit_loss"
    const val TOTAL_PROFIT_LOSS="total_profit_loss"
    fun newInstance(bundle: Bundle): BottomSheetDialog{
      val fragment = BottomSheetDialog()
      fragment.arguments = bundle
      return fragment
    }
  }
}