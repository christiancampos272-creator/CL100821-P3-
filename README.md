# Trueque - Aplicación de Intercambio de Servicios

Una aplicación Android tipo LinkedIn pero enfocada en el intercambio de servicios y talentos mediante un sistema de créditos.

## 🚀 Características

### ✅ Funcionalidades Implementadas

- **Autenticación**: Login con Google y OTP por teléfono
- **Perfil de Usuario**: Creación completa con nombre, descripción, skills, foto y precio
- **Sistema de Créditos**: Acumulables y canjeables por servicios
- **Pasarela de Pago Ficticia**: Sin procesar datos reales de pago
- **Modo Demo**: Usuarios y servicios ficticios para pruebas
- **Búsqueda y Filtros**: Buscar servicios por categoría, precio, etc.
- **Intercambio por Créditos**: Sistema de transacciones
- **Chat Básico**: Comunicación entre usuarios
- **Sistema de Valoraciones**: Reseñas y calificaciones
- **Historial de Operaciones**: Seguimiento de transacciones
- **Material Design**: Interfaz moderna y sencilla

### 🛠 Tecnologías Utilizadas

- **Android**: Kotlin, Jetpack Compose
- **Arquitectura**: MVVM con Hilt (Dependency Injection)
- **Backend**: Firebase (Auth, Firestore, Storage, FCM)
- **Navegación**: Navigation Compose
- **UI**: Material Design 3
- **Imágenes**: Coil para carga de imágenes

## 📱 Pantallas Principales

1. **Login**: Autenticación con Google/OTP y modo demo
2. **Home**: Servicios destacados y categorías
3. **Búsqueda**: Filtros avanzados y resultados
4. **Detalle de Servicio**: Información completa y compra
5. **Perfil**: Información del usuario y sus servicios
6. **Chat**: Comunicación entre usuarios
7. **Compra**: Proceso de pago con créditos/tarjeta

## 🎯 Modo Demo

La aplicación incluye datos demo para pruebas inmediatas:

### Usuarios Demo
- **María García**: Diseñadora gráfica (150 créditos)
- **Carlos López**: Desarrollador Full Stack (200 créditos)
- **Ana Rodríguez**: Traductora (75 créditos)
- **Roberto Silva**: Fotógrafo (300 créditos)

### Servicios Demo
- Diseño de Logo Profesional (50 créditos)
- Desarrollo de Aplicación Web (200 créditos)
- Traducción de Documentos (25 créditos)
- Sesión de Fotografía (80 créditos)
- Clases de Programación (30 créditos)

## 🔧 Configuración

### Prerrequisitos
- Android Studio Hedgehog o superior
- SDK Android 24+
- Kotlin 2.0+

### Instalación
1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza las dependencias
4. Ejecuta la aplicación

### Firebase Setup (Opcional)
Para funcionalidad completa:
1. Crea un proyecto en Firebase Console
2. Descarga `google-services.json`
3. Reemplaza el archivo en `app/google-services.json`
4. Configura Authentication, Firestore y Storage

## 🎨 Diseño

La aplicación sigue Material Design 3 con:
- **Colores**: Esquema de colores dinámico
- **Tipografía**: Material Typography
- **Componentes**: Cards, Buttons, TextFields modernos
- **Navegación**: Bottom Navigation y Navigation Drawer
- **Responsive**: Adaptable a diferentes tamaños de pantalla

## 🔒 Seguridad

- **Autenticación**: Firebase Auth con Google Sign-In
- **Datos**: Encriptación en tránsito y reposo
- **Pagos**: Pasarela ficticia sin datos reales
- **Permisos**: Mínimos necesarios para funcionalidad

## 📊 Arquitectura

```
app/
├── data/
│   ├── model/          # Modelos de datos
│   └── repository/     # Repositorios de datos
├── di/                 # Inyección de dependencias
├── ui/
│   ├── components/      # Componentes reutilizables
│   ├── navigation/    # Navegación
│   ├── screen/        # Pantallas
│   └── viewmodel/     # ViewModels
└── MainActivity.kt    # Actividad principal
```

## 🚀 Próximas Mejoras

- [ ] Notificaciones push
- [ ] Geolocalización
- [ ] Video llamadas
- [ ] Sistema de reputación avanzado
- [ ] Analytics
- [ ] Modo offline
- [ ] Internacionalización

## 📝 Licencia

Este proyecto es un MVP educativo. No incluye datos reales de pago ni procesamiento de transacciones reales.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:
1. Fork el proyecto
2. Crea una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Abre un Pull Request

## 📞 Soporte

Para soporte o preguntas, contacta al equipo de desarrollo.

---

**Nota**: Esta es una aplicación demo. No procesa pagos reales ni almacena datos sensibles.
