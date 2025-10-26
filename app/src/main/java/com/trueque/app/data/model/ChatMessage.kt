package com.trueque.app.data.model

import java.util.Date

data class ChatMessage(
    val id: String = "",
    val chatId: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val message: String = "",
    val timestamp: Date = Date(),
    val isRead: Boolean = false,
    val messageType: MessageType = MessageType.TEXT
)

data class Chat(
    val id: String = "",
    val participants: List<String> = emptyList(),
    val lastMessage: String = "",
    val lastMessageTime: Date = Date(),
    val isActive: Boolean = true,
    val createdAt: Date = Date()
)

enum class MessageType {
    TEXT,
    IMAGE,
    SYSTEM
}
