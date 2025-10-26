package com.trueque.app.data.model

import java.util.Date

data class Transaction(
    val id: String = "",
    val serviceId: String = "",
    val serviceTitle: String = "",
    val buyerId: String = "",
    val sellerId: String = "",
    val amount: Double = 0.0,
    val paymentType: PaymentType = PaymentType.CREDITS,
    val status: TransactionStatus = TransactionStatus.PENDING,
    val createdAt: Date = Date(),
    val completedAt: Date? = null,
    val notes: String = ""
)

enum class PaymentType {
    CREDITS,
    MONEY
}

enum class TransactionStatus {
    PENDING,
    CONFIRMED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    DISPUTED
}
