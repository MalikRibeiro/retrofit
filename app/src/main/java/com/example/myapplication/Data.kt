package com.example.myapplication

data class AdviceResponse(
    val slip: Slip
)

data class Slip(
    val advice: String,
    val slip_id: String,
)