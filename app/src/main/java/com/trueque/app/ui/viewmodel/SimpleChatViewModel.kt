package com.trueque.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trueque.app.data.model.Chat
import com.trueque.app.data.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimpleChatViewModel @Inject constructor() : ViewModel() {
    
    private val _uiState = MutableStateFlow(SimpleChatUiState())
    val uiState: StateFlow<SimpleChatUiState> = _uiState.asStateFlow()
    
    private val _chats = MutableStateFlow<List<Chat>>(emptyList())
    val chats: StateFlow<List<Chat>> = _chats.asStateFlow()
    
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()
    
    private val _currentChat = MutableStateFlow<Chat?>(null)
    val currentChat: StateFlow<Chat?> = _currentChat.asStateFlow()
    
    fun loadChats(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            // Simular carga de chats
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = null
            )
        }
    }
    
    fun startChat(userId1: String, userId2: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            // Simular creación de chat
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = null
            )
        }
    }
    
    fun loadMessages(chatId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            // Simular carga de mensajes
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = null
            )
        }
    }
    
    fun sendMessage(message: ChatMessage) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            // Simular envío de mensaje
            _messages.value = _messages.value + message
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = null
            )
        }
    }
    
    fun markMessagesAsRead(chatId: String, userId: String) {
        viewModelScope.launch {
            // Simular marcado como leído
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class SimpleChatUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)
