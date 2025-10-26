package com.trueque.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trueque.app.data.model.Service
import com.trueque.app.data.model.DemoData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimpleServiceViewModel @Inject constructor() : ViewModel() {
    
    private val _uiState = MutableStateFlow(SimpleServiceUiState())
    val uiState: StateFlow<SimpleServiceUiState> = _uiState.asStateFlow()
    
    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services: StateFlow<List<Service>> = _services.asStateFlow()
    
    private val _filteredServices = MutableStateFlow<List<Service>>(emptyList())
    val filteredServices: StateFlow<List<Service>> = _filteredServices.asStateFlow()
    
    init {
        loadDemoServices()
    }
    
    private fun loadDemoServices() {
        _services.value = DemoData.demoServices
        _filteredServices.value = DemoData.demoServices
        _uiState.value = _uiState.value.copy(
            categories = DemoData.categories
        )
    }
    
    fun loadServices() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            // Simular carga de servicios
            loadDemoServices()
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = null
            )
        }
    }
    
    fun searchServices(query: String) {
        if (query.isEmpty()) {
            _filteredServices.value = _services.value
        } else {
            val filtered = _services.value.filter { service ->
                service.title.contains(query, ignoreCase = true) ||
                service.description.contains(query, ignoreCase = true) ||
                service.tags.any { tag -> tag.contains(query, ignoreCase = true) }
            }
            _filteredServices.value = filtered
        }
    }
    
    fun filterByCategory(category: String) {
        if (category == "Todas") {
            _filteredServices.value = _services.value
        } else {
            val filtered = _services.value.filter { service ->
                service.category == category
            }
            _filteredServices.value = filtered
        }
    }
    
    fun filterByPriceRange(minPrice: Double, maxPrice: Double) {
        val filtered = _services.value.filter { service ->
            service.price in minPrice..maxPrice
        }
        _filteredServices.value = filtered
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class SimpleServiceUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categories: List<String> = emptyList()
)
