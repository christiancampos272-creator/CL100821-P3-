package com.trueque.app.data.model

import java.util.Date

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val description: String = "",
    val skills: List<String> = emptyList(),
    val profileImageUrl: String = "",
    val credits: Int = 0,
    val rating: Double = 0.0,
    val totalRatings: Int = 0,
    val isVerified: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
