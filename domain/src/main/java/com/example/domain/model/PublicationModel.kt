package com.example.domain.model

data class PublicationModel(
    val id: Int,
    val title: String,
    val text: String,
    val time: Long?
)