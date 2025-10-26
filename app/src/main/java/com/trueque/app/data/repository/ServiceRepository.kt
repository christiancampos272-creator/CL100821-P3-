package com.trueque.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.trueque.app.data.model.Service
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    
    private val servicesCollection = firestore.collection("services")
    
    suspend fun createService(service: Service): Result<Service> {
        return try {
            servicesCollection.document(service.id).set(service).await()
            Result.success(service)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getService(serviceId: String): Result<Service?> {
        return try {
            val document = servicesCollection.document(serviceId).get().await()
            val service = document.toObject(Service::class.java)
            Result.success(service)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateService(service: Service): Result<Service> {
        return try {
            servicesCollection.document(service.id).set(service).await()
            Result.success(service)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deleteService(serviceId: String): Result<Unit> {
        return try {
            servicesCollection.document(serviceId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getAllServices(): Result<List<Service>> {
        return try {
            val snapshot = servicesCollection
                .whereEqualTo("isActive", true)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val services = snapshot.documents.mapNotNull { it.toObject(Service::class.java) }
            Result.success(services)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getServicesByProvider(providerId: String): Result<List<Service>> {
        return try {
            val snapshot = servicesCollection
                .whereEqualTo("providerId", providerId)
                .whereEqualTo("isActive", true)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val services = snapshot.documents.mapNotNull { it.toObject(Service::class.java) }
            Result.success(services)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun searchServices(query: String): Result<List<Service>> {
        return try {
            val snapshot = servicesCollection
                .whereEqualTo("isActive", true)
                .whereGreaterThanOrEqualTo("title", query)
                .whereLessThanOrEqualTo("title", query + "\uf8ff")
                .get()
                .await()
            val services = snapshot.documents.mapNotNull { it.toObject(Service::class.java) }
            Result.success(services)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getServicesByCategory(category: String): Result<List<Service>> {
        return try {
            val snapshot = servicesCollection
                .whereEqualTo("category", category)
                .whereEqualTo("isActive", true)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val services = snapshot.documents.mapNotNull { it.toObject(Service::class.java) }
            Result.success(services)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getServicesByPriceRange(minPrice: Double, maxPrice: Double): Result<List<Service>> {
        return try {
            val snapshot = servicesCollection
                .whereEqualTo("isActive", true)
                .whereGreaterThanOrEqualTo("price", minPrice)
                .whereLessThanOrEqualTo("price", maxPrice)
                .orderBy("price", Query.Direction.ASCENDING)
                .get()
                .await()
            val services = snapshot.documents.mapNotNull { it.toObject(Service::class.java) }
            Result.success(services)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
