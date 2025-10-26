package com.trueque.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.trueque.app.data.model.Chat
import com.trueque.app.data.model.ChatMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    
    private val chatsCollection = firestore.collection("chats")
    private val messagesCollection = firestore.collection("messages")
    
    suspend fun createChat(chat: Chat): Result<Chat> {
        return try {
            chatsCollection.document(chat.id).set(chat).await()
            Result.success(chat)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getChat(chatId: String): Result<Chat?> {
        return try {
            val document = chatsCollection.document(chatId).get().await()
            val chat = document.toObject(Chat::class.java)
            Result.success(chat)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getChatsByUser(userId: String): Result<List<Chat>> {
        return try {
            val snapshot = chatsCollection
                .whereArrayContains("participants", userId)
                .whereEqualTo("isActive", true)
                .orderBy("lastMessageTime", Query.Direction.DESCENDING)
                .get()
                .await()
            val chats = snapshot.documents.mapNotNull { it.toObject(Chat::class.java) }
            Result.success(chats)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getOrCreateChat(userId1: String, userId2: String): Result<Chat> {
        return try {
            // Buscar chat existente
            val snapshot = chatsCollection
                .whereArrayContains("participants", userId1)
                .whereArrayContains("participants", userId2)
                .whereEqualTo("isActive", true)
                .get()
                .await()
            
            if (!snapshot.isEmpty) {
                val chat = snapshot.documents.first().toObject(Chat::class.java)
                Result.success(chat!!)
            } else {
                // Crear nuevo chat
                val chatId = "${userId1}_${userId2}_${System.currentTimeMillis()}"
                val chat = Chat(
                    id = chatId,
                    participants = listOf(userId1, userId2),
                    isActive = true
                )
                createChat(chat)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun sendMessage(message: ChatMessage): Result<ChatMessage> {
        return try {
            messagesCollection.document(message.id).set(message).await()
            
            // Actualizar último mensaje del chat
            chatsCollection.document(message.chatId)
                .update(
                    "lastMessage", message.message,
                    "lastMessageTime", message.timestamp
                )
                .await()
            
            Result.success(message)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getMessages(chatId: String): Result<List<ChatMessage>> {
        return try {
            val snapshot = messagesCollection
                .whereEqualTo("chatId", chatId)
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .get()
                .await()
            val messages = snapshot.documents.mapNotNull { it.toObject(ChatMessage::class.java) }
            Result.success(messages)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun markMessagesAsRead(chatId: String, userId: String): Result<Unit> {
        return try {
            val snapshot = messagesCollection
                .whereEqualTo("chatId", chatId)
                .whereEqualTo("receiverId", userId)
                .whereEqualTo("isRead", false)
                .get()
                .await()
            
            val batch = firestore.batch()
            snapshot.documents.forEach { doc ->
                batch.update(doc.reference, "isRead", true)
            }
            batch.commit().await()
            
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
