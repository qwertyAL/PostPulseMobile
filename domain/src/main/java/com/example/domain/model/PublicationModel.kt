package com.example.domain.model

data class PublicationModel(
    val id: Int,
    val channelId: Long,
    val text: String,
    val comment: String,
    val scheduledAt: String?,
    val updatedAt: String,
    val name: String
)