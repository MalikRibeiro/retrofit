package com.example.myapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AdviceViewModel : ViewModel () {
    val advice = mutableStateOf("Carregando...")

    init {
        fetchAdvice()
    }



    fun fetchAdvice(){
        viewModelScope.launch {

            try {
                val resp = RetrofitInstance.api.getAdvice()
                advice.value = resp.slip.advice

            } catch (e: Exception){
              advice.value = "Erro - ${e.message}"
            }
        }
    }
}