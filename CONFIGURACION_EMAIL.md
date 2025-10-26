# 🔧 Configuración del Sistema de Email OTP

## ❌ Problema Actual
Tu sistema OTP **NO está funcionando** porque el `EmailService` solo simula el envío de emails (no envía emails reales).

## ✅ Solución Implementada
He implementado **Resend API** - una API gratuita que:
- ✅ **No requiere configuración manual**
- ✅ **Funciona con cualquier email (Gmail, Yahoo, Outlook, etc.)**
- ✅ **3,000 emails gratuitos por mes**
- ✅ **Sin límites de destinatarios**

## 🚀 Pasos para Activar

### 1. Obtener API Key Gratuita
1. Ve a [https://resend.com](https://resend.com)
2. Haz clic en **"Sign Up"**
3. Regístrate con cualquier email
4. Verifica tu email
5. En el dashboard, ve a **"API Keys"**
6. Haz clic en **"Create API Key"**
7. Dale un nombre: `Trueque App`
8. **Copia la API key** (empieza con `re_`)

### 2. Configurar en tu App
1. Abre: `app/src/main/java/com/trueque/app/data/config/EmailConfig.kt`
2. En la línea 8, reemplaza:
   ```kotlin
   const val RESEND_API_KEY = "re_1234567890abcdef"
   ```
   Por tu API key real:
   ```kotlin
   const val RESEND_API_KEY = "re_tu_api_key_aqui"
   ```

### 3. ¡Listo! 🎉
- **Ejecuta tu app**
- **Intenta registrarte con cualquier email**
- **Revisa tu bandeja de entrada**
- **Deberías recibir el código OTP**

## 🧪 Probar el Sistema

Para verificar que funciona, puedes usar el método de prueba:

```kotlin
// En tu ViewModel o Activity
val emailService = EmailService()
val result = emailService.testEmailService("tu-email@gmail.com")
```

## 📧 Ejemplo de Email que Recibirás

```
🔐 Código de Verificación

Hola,

Tu código de verificación para Trueque App es:

123456

Este código es válido por 5 minutos.

Si no solicitaste este código, puedes ignorar este correo de forma segura.

¡Gracias por usar Trueque App!
```

## 🔍 Verificar que Funciona

1. **Revisa tu bandeja de entrada**
2. **Revisa la carpeta de spam** (a veces llega ahí)
3. **Busca el remitente**: `Trueque App <noreply@truequeapp.com>`

## 🆘 Si No Funciona

### No recibo el email
1. ✅ **Revisa la carpeta de spam**
2. ✅ **Verifica que la API key sea correcta**
3. ✅ **Revisa los logs de la app** (se muestran en consola)

### Error de API Key
- ✅ Asegúrate de que empiece con `re_`
- ✅ No debe tener espacios extra
- ✅ La cuenta de Resend debe estar activa

## 📊 Límites Gratuitos

- **3,000 emails por mes** (suficiente para desarrollo)
- **100 emails por día**
- **Sin límite de destinatarios por email**

## 🎯 Ventajas de esta Solución

✅ **No necesitas configurar SMTP**  
✅ **No necesitas servidor de email**  
✅ **Funciona con cualquier proveedor**  
✅ **API moderna y simple**  
✅ **Dashboard para monitoreo**  
✅ **Completamente gratuito**  

## 📱 Código Ya Implementado

El sistema está **completamente implementado**:

- ✅ `EmailService.kt` - Servicio de envío real
- ✅ `EmailConfig.kt` - Configuración centralizada  
- ✅ `OtpService.kt` - Generación y validación
- ✅ `OtpViewModel.kt` - Lógica de UI
- ✅ Templates HTML y texto plano
- ✅ Manejo de errores
- ✅ Timeouts configurables

**Solo necesitas la API key de Resend y ¡funcionará!**

## 🔗 Enlaces Útiles

- **Resend Dashboard**: [https://resend.com/emails](https://resend.com/emails)
- **Documentación**: [https://resend.com/docs](https://resend.com/docs)
- **API Reference**: [https://resend.com/docs/api-reference](https://resend.com/docs/api-reference)
