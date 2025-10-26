package com.trueque.app.data.model

import java.util.Date

data class Rating(
    val id: String = "",
    val transactionId: String = "",
    val raterId: String = "",
    val ratedUserId: String = "",
    val serviceId: String = "",
    val rating: Int = 0, // 1-5 stars
    val comment: String = "",
    val createdAt: Date = Date()
)
