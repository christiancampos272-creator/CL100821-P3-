package com.trueque.app.data.config

object EmailConfig {
    // Configuración de EmailJS (completamente gratuito)
    // Ya tienes el service_id: service_obtn30t
    const val EMAILJS_SERVICE_ID = "service_obtn30t"
    
    // Template ID para el email OTP (puedes usar cualquier nombre)
    const val EMAILJS_TEMPLATE_ID = "template_otp"
    
    // User ID de EmailJS (necesitas obtenerlo de tu cuenta)
    // Ve a https://dashboard.emailjs.com/admin/integration
    const val EMAILJS_USER_ID = "4Qq3jweqaYTL4uTa1"
    
    // Configuración de timeouts
    const val CONNECT_TIMEOUT_SECONDS = 30L
    const val READ_TIMEOUT_SECONDS = 30L
    const val WRITE_TIMEOUT_SECONDS = 30L
}
