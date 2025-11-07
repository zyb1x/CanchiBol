package com.example.canchibol.domain.entity

data class UserEntity(
    val id: String,
    val email: String,
    val nombre: String,
    val token: String? = null
)