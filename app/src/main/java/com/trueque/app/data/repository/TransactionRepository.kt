package com.trueque.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.trueque.app.data.model.Transaction
import com.trueque.app.data.model.TransactionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    
    private val transactionsCollection = firestore.collection("transactions")
    
    suspend fun createTransaction(transaction: Transaction): Result<Transaction> {
        return try {
            transactionsCollection.document(transaction.id).set(transaction).await()
            Result.success(transaction)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getTransaction(transactionId: String): Result<Transaction?> {
        return try {
            val document = transactionsCollection.document(transactionId).get().await()
            val transaction = document.toObject(Transaction::class.java)
            Result.success(transaction)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateTransaction(transaction: Transaction): Result<Transaction> {
        return try {
            transactionsCollection.document(transaction.id).set(transaction).await()
            Result.success(transaction)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getTransactionsByUser(userId: String): Result<List<Transaction>> {
        return try {
            val snapshot = transactionsCollection
                .whereEqualTo("buyerId", userId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val transactions = snapshot.documents.mapNotNull { it.toObject(Transaction::class.java) }
            Result.success(transactions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getTransactionsBySeller(sellerId: String): Result<List<Transaction>> {
        return try {
            val snapshot = transactionsCollection
                .whereEqualTo("sellerId", sellerId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val transactions = snapshot.documents.mapNotNull { it.toObject(Transaction::class.java) }
            Result.success(transactions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateTransactionStatus(transactionId: String, status: TransactionStatus): Result<Unit> {
        return try {
            transactionsCollection.document(transactionId)
                .update("status", status.name)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getAllTransactions(): Result<List<Transaction>> {
        return try {
            val snapshot = transactionsCollection
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val transactions = snapshot.documents.mapNotNull { it.toObject(Transaction::class.java) }
            Result.success(transactions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
