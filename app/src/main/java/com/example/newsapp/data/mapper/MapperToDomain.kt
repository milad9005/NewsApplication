package com.example.newsapp.data.mapper

interface MapperToDomain<From, To> {
    fun mapToDomain(from: From): To
}