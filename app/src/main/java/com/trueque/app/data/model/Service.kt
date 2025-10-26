package com.trueque.app.data.model

import java.util.Date

data class Service(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val price: Double = 0.0,
    val priceType: PriceType = PriceType.CREDITS,
    val providerId: String = "",
    val providerName: String = "",
    val providerImageUrl: String = "",
    val images: List<String> = emptyList(),
    val isActive: Boolean = true,
    val isAvailable: Boolean = true,
    val tags: List<String> = emptyList(),
    val rating: Double = 0.0,
    val totalRatings: Int = 0,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

enum class PriceType {
    CREDITS,
    MONEY,
    BOTH
}
