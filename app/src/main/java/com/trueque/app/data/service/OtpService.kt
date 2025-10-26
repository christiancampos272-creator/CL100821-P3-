package com.trueque.app.data.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OtpService @Inject constructor(
    private val emailService: EmailService
) {
    
    // Almacenamiento temporal de códigos OTP
    private val otpCodes = mutableMapOf<String, OtpData>()
    
    data class OtpData(
        val code: String,
        val email: String,
        val timestamp: Long,
        val attempts: Int = 0
    )
    
    suspend fun generateAndSendOtp(email: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            // Generar código OTP de 6 dígitos
            val otpCode = generateOtpCode()
            
            // Guardar código temporalmente (5 minutos de validez)
            otpCodes[email] = OtpData(
                code = otpCode,
                email = email,
                timestamp = System.currentTimeMillis()
            )
            
            // Enviar correo usando API gratuita
            val emailResult = emailService.sendOtpEmail(email, otpCode)
            
            if (emailResult.isSuccess) {
                Result.success("Código OTP enviado a $email")
            } else {
                Result.failure(emailResult.exceptionOrNull() ?: Exception("Error al enviar correo"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun verifyOtpCode(email: String, code: String): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            // MODO PROTOTIPO: Aceptar cualquier código de 6 dígitos
            if (code.length == 6 && code.all { it.isDigit() }) {
                println("✅ Código OTP válido: $code para $email")
                Result.success(true)
            } else {
                println("❌ Código OTP inválido: $code (debe ser 6 dígitos)")
                Result.failure(Exception("Código debe ser de 6 dígitos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun generateOtpCode(): String {
        val random = Random()
        return String.format("%06d", random.nextInt(1000000))
    }
    
    
    fun cleanupExpiredCodes() {
        val currentTime = System.currentTimeMillis()
        val expiredKeys = otpCodes.filter { (_, data) ->
            currentTime > (data.timestamp + (5 * 60 * 1000))
        }.keys
        
        expiredKeys.forEach { email ->
            otpCodes.remove(email)
        }
    }
    
    // Método para obtener el código generado (solo para testing)
    fun getGeneratedCode(email: String): String? {
        return otpCodes[email]?.code
    }
}