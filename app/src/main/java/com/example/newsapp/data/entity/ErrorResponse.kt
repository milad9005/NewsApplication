package com.example.newsapp.data.entity

import com.example.newsapp.data.json.SingleToArray

data class ErrorResponse(
    @SingleToArray val message: List<String>
)