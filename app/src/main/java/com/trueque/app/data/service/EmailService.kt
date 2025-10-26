package com.trueque.app.data.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Singleton
class EmailService @Inject constructor() {
    
    private val retrofit: Retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
            
        Retrofit.Builder()
            .baseUrl("https://api.emailjs.com/api/v1.0/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    private val emailjsApi: EmailjsApi by lazy {
        retrofit.create(EmailjsApi::class.java)
    }
    
    suspend fun sendOtpEmail(email: String, otpCode: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            // Simular envío de email para prototipo
            kotlinx.coroutines.delay(1000) // Simular tiempo de envío
            
            // Mostrar el código en consola para desarrollo
            println("🔐 CÓDIGO OTP GENERADO:")
            println("Email: $email")
            println("Código: $otpCode")
            println("═══════════════════════════════════════")
            
            Result.success("✅ Correo enviado exitosamente (modo prototipo)")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun createOtpMessage(otpCode: String): String {
        return """
            🔐 CÓDIGO DE VERIFICACIÓN - TRUEQUE APP
            
            Hola,
            
            Tu código de verificación para Trueque App es:
            
            ╔══════════════════════════════════════╗
            ║              $otpCode              ║
            ╚══════════════════════════════════════╝
            
            ⏰ Este código es válido por 5 minutos.
            
            Si no solicitaste este código, puedes ignorar este correo de forma segura.
            
            ¡Gracias por usar Trueque App!
            
            ---
            Este es un correo automático, por favor no respondas.
            © 2024 Trueque App. Todos los derechos reservados.
        """.trimIndent()
    }
    
    /**
     * Método de prueba para verificar que el servicio funciona
     */
    suspend fun testEmailService(email: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val testCode = "123456"
            val result = sendOtpEmail(email, testCode)
            
            if (result.isSuccess) {
                Result.success("✅ Servicio de email funcionando correctamente. Revisa tu bandeja de entrada.")
            } else {
                Result.failure(result.exceptionOrNull() ?: Exception("Error en la prueba"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

// Interfaces para la API de EmailJS
interface EmailjsApi {
    @POST("email/send")
    suspend fun sendEmail(
        @Body emailRequest: EmailjsRequest
    ): retrofit2.Response<EmailjsResponse>
}

data class EmailjsRequest(
    val service_id: String,
    val template_id: String,
    val user_id: String,
    val template_params: Map<String, String>
)

data class EmailjsResponse(
    val status: Int,
    val text: String
)