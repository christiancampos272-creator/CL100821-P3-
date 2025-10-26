package com.trueque.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trueque.app.data.repository.TransactionRepository
import com.trueque.app.data.repository.UserRepository
import com.trueque.app.data.model.Transaction
import com.trueque.app.data.model.TransactionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()
    
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()
    
    fun loadTransactions(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            transactionRepository.getTransactionsByUser(userId).fold(
                onSuccess = { transactions ->
                    _transactions.value = transactions
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
    }
    
    fun loadSellerTransactions(sellerId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            transactionRepository.getTransactionsBySeller(sellerId).fold(
                onSuccess = { transactions ->
                    _transactions.value = transactions
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
    }
    
    fun createTransaction(transaction: Transaction) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            transactionRepository.createTransaction(transaction).fold(
                onSuccess = {
                    _transactions.value = _transactions.value + transaction
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
    }
    
    fun updateTransactionStatus(transactionId: String, status: TransactionStatus) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            transactionRepository.updateTransactionStatus(transactionId, status).fold(
                onSuccess = {
                    // Actualizar la lista local
                    _transactions.value = _transactions.value.map { transaction ->
                        if (transaction.id == transactionId) {
                            transaction.copy(status = status)
                        } else {
                            transaction
                        }
                    }
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
    }
    
    fun processPayment(transaction: Transaction) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            // Deduct credits from buyer
            userRepository.deductCredits(transaction.buyerId, transaction.amount.toInt()).fold(
                onSuccess = {
                    // Add credits to seller
                    userRepository.addCredits(transaction.sellerId, transaction.amount.toInt()).fold(
                        onSuccess = {
                            // Update transaction status
                            updateTransactionStatus(transaction.id, TransactionStatus.CONFIRMED)
                        },
                        onFailure = { error ->
                            // Rollback: return credits to buyer
                            userRepository.addCredits(transaction.buyerId, transaction.amount.toInt())
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = error.message
                            )
                        }
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class TransactionUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)
