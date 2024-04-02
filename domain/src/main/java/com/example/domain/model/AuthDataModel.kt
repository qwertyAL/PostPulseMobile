package com.example.domain.model

data class AuthDataModel(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val username: String?,
    val photoUrl: String?,
    val authDate: String,
    val hash: String
)
