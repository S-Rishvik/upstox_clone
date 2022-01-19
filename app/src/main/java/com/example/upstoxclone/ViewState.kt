package com.example.upstoxclone

sealed class ViewState<out T> {
  object Loading: ViewState<Nothing>()
  data class Success<T>(val data: T): ViewState<T>()
  data class Failure(val msg: String): ViewState<Nothing>()
  object NoInternet: ViewState<Nothing>()
}