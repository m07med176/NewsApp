package com.biteam.compose.utils

data class UIScreenState<T>(val error:String?=null,val loading:Boolean = false,val data:T?=null)
