# 📧 Configurar EmailJS (Completamente Gratuito)

## ✅ Ya Tienes Configurado
- **Service ID**: `service_obtn30t` ✅

## 🔧 Solo Necesitas Obtener tu User ID

### Paso 1: Acceder a tu Cuenta EmailJS
1. **Ve a [https://dashboard.emailjs.com](https://dashboard.emailjs.com)**
2. **Inicia sesión** con tu cuenta
3. **Ve a "Integration"** en el menú lateral

### Paso 2: Obtener tu User ID
1. **En la sección "Integration"**
2. **Busca "User ID"** o "Public Key"
3. **Copia tu User ID** (empieza con letras y números)

### Paso 3: Configurar en tu App
1. **Abre**: `app/src/main/java/com/trueque/app/data/config/EmailConfig.kt`
2. **En la línea 13, reemplaza**:
   ```kotlin
   const val EMAILJS_USER_ID = "TU_USER_ID_AQUI"
   ```
   **Por tu User ID real**:
   ```kotlin
   const val EMAILJS_USER_ID = "tu_user_id_real"
   ```

## 🎯 Configurar Template de Email

### Paso 1: Crear Template
1. **En EmailJS dashboard, ve a "Email Templates"**
2. **Haz clic en "Create New Template"**
3. **Dale el nombre**: `template_otp`
4. **Configura el template**:

### Paso 2: Configurar el Template
**Subject**: `Código de verificación - Trueque App`

**Content** (HTML):
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Código de Verificación</title>
    <style>
        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
        .header { background: #4CAF50; color: white; padding: 20px; text-align: center; border-radius: 8px 8px 0 0; }
        .content { background: #f9f9f9; padding: 30px; border-radius: 0 0 8px 8px; }
        .otp-code { 
            background: #4CAF50; 
            color: white; 
            font-size: 32px; 
            font-weight: bold; 
            padding: 20px; 
            text-align: center; 
            border-radius: 8px; 
            margin: 20px 0;
            letter-spacing: 5px;
        }
        .footer { text-align: center; margin-top: 20px; color: #666; font-size: 14px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🔐 Código de Verificación</h1>
        </div>
        <div class="content">
            <h2>Hola,</h2>
            <p>Tu código de verificación para <strong>Trueque App</strong> es:</p>
            <div class="otp-code">{{otp_code}}</div>
            <p><strong>Este código es válido por 5 minutos.</strong></p>
            <p>Si no solicitaste este código, puedes ignorar este correo de forma segura.</p>
            <p>¡Gracias por usar Trueque App!</p>
        </div>
        <div class="footer">
            <p>Este es un correo automático, por favor no respondas.</p>
            <p>© 2024 Trueque App. Todos los derechos reservados.</p>
        </div>
    </div>
</body>
</html>
```

### Paso 3: Configurar Variables
En el template, asegúrate de que estas variables estén disponibles:
- `{{to_email}}` - Email del destinatario
- `{{otp_code}}` - Código OTP
- `{{app_name}}` - Nombre de la app
- `{{message}}` - Mensaje personalizado

## 🚀 ¡Probar el Sistema!

### 1. **Ejecuta tu app**
### 2. **Intenta registrarte con cualquier email**
### 3. **Revisa tu bandeja de entrada**

## 📧 Email que Recibirás

```
🔐 Código de Verificación

Hola,

Tu código de verificación para Trueque App es:

123456

Este código es válido por 5 minutos.

Si no solicitaste este código, puedes ignorar este correo de forma segura.

¡Gracias por usar Trueque App!
```

## 🎯 Ventajas de EmailJS

✅ **Completamente gratuito**  
✅ **Sin límites de emails**  
✅ **No requiere configuración SMTP**  
✅ **Funciona con Gmail, Yahoo, Outlook, etc.**  
✅ **Fácil de configurar**  
✅ **Templates personalizables**  

## 🆘 Si No Funciona

### Verificar User ID
1. ✅ **Copia exactamente tu User ID** (sin espacios)
2. ✅ **Verifica que esté en la sección Integration**
3. ✅ **Asegúrate de que la cuenta esté activa**

### Verificar Template
1. ✅ **El template debe llamarse `template_otp`**
2. ✅ **Debe tener las variables `{{otp_code}}`, `{{to_email}}`**
3. ✅ **Debe estar activo en tu cuenta**

## 📊 Límites de EmailJS

- **200 emails por día** (gratuito)
- **Sin límite de destinatarios por email**
- **Sin límite de templates**

## 🔗 Enlaces Útiles

- **Dashboard**: [https://dashboard.emailjs.com](https://dashboard.emailjs.com)
- **Documentación**: [https://www.emailjs.com/docs/](https://www.emailjs.com/docs/)
- **Templates**: [https://dashboard.emailjs.com/admin/email-templates](https://dashboard.emailjs.com/admin/email-templates)

**¡Solo necesitas tu User ID y funcionará perfectamente!** 🎉
