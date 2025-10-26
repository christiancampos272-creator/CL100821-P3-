# 🔑 Cómo Obtener tu API Key de Resend

## ❌ Error Actual: 401 Unauthorized
Esto significa que la API key no es válida. Necesitas obtener tu API key real.

## 🚀 Pasos para Obtener tu API Key

### Paso 1: Crear Cuenta
1. **Ve a [https://resend.com](https://resend.com)**
2. **Haz clic en "Sign Up"**
3. **Regístrate con cualquier email** (Gmail, Yahoo, etc.)
4. **Verifica tu email** (revisa tu bandeja de entrada)

### Paso 2: Acceder al Dashboard
1. **Inicia sesión en [https://resend.com](https://resend.com)**
2. **Verás el dashboard principal**

### Paso 3: Crear API Key
1. **En el menú lateral, haz clic en "API Keys"**
2. **Haz clic en "Create API Key"**
3. **Dale un nombre: "Trueque App"**
4. **Haz clic en "Create"**
5. **Copia la API key** (empieza con `re_`)

### Paso 4: Configurar en tu App
1. **Abre el archivo**: `app/src/main/java/com/trueque/app/data/config/EmailConfig.kt`
2. **En la línea 10, reemplaza**:
   ```kotlin
   const val RESEND_API_KEY = "re_TU_API_KEY_AQUI"
   ```
   **Por tu API key real**:
   ```kotlin
   const val RESEND_API_KEY = "re_AbCdEf1234567890..."
   ```

## ✅ Verificar que Funciona

### Método 1: Probar en la App
1. **Ejecuta tu app**
2. **Intenta registrarte con cualquier email**
3. **Deberías recibir el email OTP**

### Método 2: Usar el Método de Prueba
```kotlin
// En tu ViewModel o Activity
val emailService = EmailService()
val result = emailService.testEmailService("tu-email@gmail.com")
```

## 🔍 Formato Correcto de API Key

Tu API key debe verse así:
```
re_AbCdEf1234567890GhIjKlMnOpQrStUvWxYz
```

**Características:**
- ✅ Empieza con `re_`
- ✅ Tiene al menos 32 caracteres
- ✅ Contiene letras y números
- ✅ No tiene espacios

## 🆘 Si Sigue Sin Funcionar

### Verificar API Key
1. ✅ **Copia exactamente la API key** (sin espacios extra)
2. ✅ **Verifica que empiece con `re_`**
3. ✅ **Asegúrate de que la cuenta de Resend esté activa**

### Verificar Cuenta
1. ✅ **Inicia sesión en [https://resend.com](https://resend.com)**
2. ✅ **Ve a "API Keys" y verifica que esté activa**
3. ✅ **Revisa que no haya límites excedidos**

## 📊 Límites Gratuitos

- **3,000 emails por mes**
- **100 emails por día**
- **Sin límite de destinatarios**

## 🎯 Una Vez Configurado

Tu sistema OTP funcionará con:
- ✅ **Gmail**
- ✅ **Yahoo**
- ✅ **Outlook**
- ✅ **Hotmail**
- ✅ **Cualquier proveedor de email**

## 📱 Resultado Final

Recibirás un email profesional con:
```
🔐 Código de Verificación

Hola,

Tu código de verificación para Trueque App es:

123456

Este código es válido por 5 minutos.
```

**¡Solo necesitas la API key real y funcionará!** 🎉
