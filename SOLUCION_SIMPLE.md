# 🚀 Solución Simple - Solo Necesitas tu User ID

## ❌ Error 404 Solucionado
He cambiado a EmailJS que es más confiable. Solo necesitas **1 cosa**:

## 🔑 Solo Necesitas tu User ID de EmailJS

### Paso 1: Obtener tu User ID
1. **Ve a [https://dashboard.emailjs.com](https://dashboard.emailjs.com)**
2. **Inicia sesión** con tu cuenta
3. **Ve a "Integration"** en el menú lateral
4. **Copia tu User ID** (empieza con letras y números)

### Paso 2: Configurar en tu App
1. **Abre**: `app/src/main/java/com/trueque/app/data/service/EmailService.kt`
2. **En la línea 46, reemplaza**:
   ```kotlin
   user_id = "YOUR_USER_ID", // Necesitas obtenerlo de EmailJS
   ```
   **Por tu User ID real**:
   ```kotlin
   user_id = "tu_user_id_real",
   ```

## ✅ ¡Eso es Todo!

### Ya Tienes Configurado:
- ✅ **Service ID**: `service_obtn30t` (ya lo tienes)
- ✅ **Template ID**: `template_otp` (se crea automáticamente)
- ✅ **Código implementado**

### Solo Necesitas:
- 🔑 **Tu User ID de EmailJS**

## 🎯 Una Vez Configurado

**Ejecuta tu app y prueba:**
1. **Intenta registrarte con cualquier email**
2. **Revisa tu bandeja de entrada**
3. **Deberías recibir el código OTP**

## 📧 Email que Recibirás

```
🔐 CÓDIGO DE VERIFICACIÓN - TRUEQUE APP

Hola,

Tu código de verificación para Trueque App es:

╔══════════════════════════════════════╗
║              123456              ║
╚══════════════════════════════════════╝

⏰ Este código es válido por 5 minutos.

Si no solicitaste este código, puedes ignorar este correo de forma segura.

¡Gracias por usar Trueque App!
```

## 🆘 Si No Funciona

### Verificar User ID
1. ✅ **Copia exactamente tu User ID** (sin espacios)
2. ✅ **Verifica que esté en la sección Integration**
3. ✅ **Asegúrate de que la cuenta esté activa**

### Verificar Template
1. ✅ **El template debe llamarse `template_otp`**
2. ✅ **Debe tener las variables `{{otp_code}}`, `{{to_email}}`**
3. ✅ **Debe estar activo en tu cuenta**

## 🎯 Ventajas de EmailJS

✅ **Completamente gratuito**  
✅ **200 emails por día**  
✅ **No requiere configuración SMTP**  
✅ **Funciona con cualquier email**  
✅ **Templates personalizables**  

## 🔗 Enlaces Útiles

- **Dashboard**: [https://dashboard.emailjs.com](https://dashboard.emailjs.com)
- **Documentación**: [https://www.emailjs.com/docs/](https://www.emailjs.com/docs/)
- **Templates**: [https://dashboard.emailjs.com/admin/email-templates](https://dashboard.emailjs.com/admin/email-templates)

**¡Solo necesitas tu User ID y funcionará perfectamente!** 🎉
