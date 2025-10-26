package com.trueque.app.data.model

import java.util.Date

object DemoData {
    
    val demoUsers = listOf(
        User(
            id = "user1",
            name = "María García",
            email = "maria@example.com",
            phone = "+1234567890",
            description = "Diseñadora gráfica con 5 años de experiencia",
            skills = listOf("Diseño Gráfico", "Photoshop", "Illustrator", "UI/UX"),
            profileImageUrl = "https://images.unsplash.com/photo-1494790108755-2616b612b786?w=150",
            credits = 150,
            rating = 4.8,
            totalRatings = 23,
            isVerified = true
        ),
        User(
            id = "user2",
            name = "Carlos López",
            email = "carlos@example.com",
            phone = "+1234567891",
            description = "Desarrollador Full Stack especializado en React y Node.js",
            skills = listOf("JavaScript", "React", "Node.js", "MongoDB", "AWS"),
            profileImageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150",
            credits = 200,
            rating = 4.9,
            totalRatings = 45,
            isVerified = true
        ),
        User(
            id = "user3",
            name = "Ana Rodríguez",
            email = "ana@example.com",
            phone = "+1234567892",
            description = "Traductora profesional inglés-español",
            skills = listOf("Traducción", "Inglés", "Español", "Redacción", "Corrección"),
            profileImageUrl = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=150",
            credits = 75,
            rating = 4.7,
            totalRatings = 18,
            isVerified = false
        ),
        User(
            id = "user4",
            name = "Roberto Silva",
            email = "roberto@example.com",
            phone = "+1234567893",
            description = "Fotógrafo profesional especializado en retratos y eventos",
            skills = listOf("Fotografía", "Photoshop", "Lightroom", "Retratos", "Eventos"),
            profileImageUrl = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=150",
            credits = 300,
            rating = 4.9,
            totalRatings = 67,
            isVerified = true
        )
    )
    
    val demoServices = listOf(
        Service(
            id = "service1",
            title = "Diseño de Logo Profesional",
            description = "Creo logos únicos y profesionales para tu marca. Incluye 3 propuestas iniciales y 2 revisiones.",
            category = "Diseño",
            price = 50.0,
            priceType = PriceType.CREDITS,
            providerId = "user1",
            providerName = "María García",
            providerImageUrl = "https://images.unsplash.com/photo-1494790108755-2616b612b786?w=150",
            images = listOf(
                "https://images.unsplash.com/photo-1558655146-d09347e92766?w=400",
                "https://images.unsplash.com/photo-1561070791-2526d30994b5?w=400"
            ),
            tags = listOf("Logo", "Diseño", "Branding", "Identidad Visual"),
            rating = 4.8,
            totalRatings = 15
        ),
        Service(
            id = "service2",
            title = "Desarrollo de Aplicación Web",
            description = "Desarrollo completo de aplicaciones web con React y Node.js. Incluye diseño responsive y base de datos.",
            category = "Desarrollo",
            price = 200.0,
            priceType = PriceType.CREDITS,
            providerId = "user2",
            providerName = "Carlos López",
            providerImageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150",
            images = listOf(
                "https://images.unsplash.com/photo-1461749280684-dccba630e2f6?w=400",
                "https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=400"
            ),
            tags = listOf("Desarrollo", "Web", "React", "Node.js", "JavaScript"),
            rating = 4.9,
            totalRatings = 28
        ),
        Service(
            id = "service3",
            title = "Traducción de Documentos",
            description = "Traducción profesional de documentos del inglés al español o viceversa. Especializada en textos técnicos y comerciales.",
            category = "Traducción",
            price = 25.0,
            priceType = PriceType.CREDITS,
            providerId = "user3",
            providerName = "Ana Rodríguez",
            providerImageUrl = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=150",
            images = listOf(
                "https://images.unsplash.com/photo-1450101499163-c8848c66ca85?w=400"
            ),
            tags = listOf("Traducción", "Inglés", "Español", "Documentos"),
            rating = 4.7,
            totalRatings = 12
        ),
        Service(
            id = "service4",
            title = "Sesión de Fotografía de Retrato",
            description = "Sesión de fotografía profesional de retrato. Incluye 20 fotos editadas y entrega digital.",
            category = "Fotografía",
            price = 80.0,
            priceType = PriceType.CREDITS,
            providerId = "user4",
            providerName = "Roberto Silva",
            providerImageUrl = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=150",
            images = listOf(
                "https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=400",
                "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400"
            ),
            tags = listOf("Fotografía", "Retrato", "Profesional", "Edición"),
            rating = 4.9,
            totalRatings = 34
        ),
        Service(
            id = "service5",
            title = "Clases de Programación",
            description = "Clases personalizadas de programación en JavaScript, Python o Java. Nivel principiante a intermedio.",
            category = "Educación",
            price = 30.0,
            priceType = PriceType.CREDITS,
            providerId = "user2",
            providerName = "Carlos López",
            providerImageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150",
            images = listOf(
                "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=400"
            ),
            tags = listOf("Programación", "Clases", "JavaScript", "Python", "Java"),
            rating = 4.8,
            totalRatings = 19
        )
    )
    
    val categories = listOf(
        "Todas",
        "Diseño",
        "Desarrollo",
        "Traducción",
        "Fotografía",
        "Educación",
        "Marketing",
        "Consultoría",
        "Escritura",
        "Música",
        "Otros"
    )
}
