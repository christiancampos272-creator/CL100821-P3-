package com.trueque.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trueque.app.data.model.User
import com.trueque.app.data.repository.AuthRepository
import com.trueque.app.data.service.OtpService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val otpService: OtpService
) : ViewModel() {

    private val _uiState = MutableStateFlow(OtpUiState())
    val uiState: StateFlow<OtpUiState> = _uiState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        // Observar cambios en el estado de autenticación
        viewModelScope.launch {
            try {
                authRepository.authState.collect { firebaseUser ->
                    if (firebaseUser != null) {
                        _currentUser.value = User(
                            id = firebaseUser.uid,
                            name = firebaseUser.displayName ?: "Usuario",
                            email = firebaseUser.email ?: "",
                            phone = firebaseUser.phoneNumber ?: "",
                            profileImageUrl = firebaseUser.photoUrl?.toString() ?: "",
                            rating = 4.5,
                            isVerified = firebaseUser.isEmailVerified
                        )
                        _uiState.value = _uiState.value.copy(
                            isAuthenticated = true,
                            isLoading = false,
                            error = null
                        )
                    } else {
                        _currentUser.value = null
                        _uiState.value = _uiState.value.copy(
                            isAuthenticated = false,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error de conexión: ${e.message}"
                )
            }
        }
    }

    fun sendOtpCode(email: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            val result = otpService.generateAndSendOtp(email)
            result.onSuccess { message ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = message
                )
            }.onFailure { e ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error al enviar código"
                )
            }
        }
    }

    fun verifyOtpCode(email: String, code: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            val result = otpService.verifyOtpCode(email, code)
            result.onSuccess { isValid ->
                if (isValid) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isAuthenticated = true,
                        successMessage = "Código verificado correctamente"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Código incorrecto"
                    )
                }
            }.onFailure { e ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error al verificar código"
                )
            }
        }
    }

    fun resendOtpCode(email: String) {
        sendOtpCode(email)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun clearSuccessMessage() {
        _uiState.value = _uiState.value.copy(successMessage = null)
    }
}

data class OtpUiState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)
