package com.trueque.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trueque.app.data.repository.ServiceRepository
import com.trueque.app.data.model.Service
import com.trueque.app.data.model.DemoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val serviceRepository: ServiceRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ServiceUiState())
    val uiState: StateFlow<ServiceUiState> = _uiState.asStateFlow()
    
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
            serviceRepository.getAllServices().fold(
                onSuccess = { services ->
                    _services.value = services
                    _filteredServices.value = services
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    // En caso de error, usar datos demo
                    loadDemoServices()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Usando datos demo: ${error.message}"
                    )
                }
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
    
    fun createService(service: Service) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            serviceRepository.createService(service).fold(
                onSuccess = {
                    _services.value = _services.value + service
                    _filteredServices.value = _filteredServices.value + service
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
    }
    
    fun updateService(service: Service) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            serviceRepository.updateService(service).fold(
                onSuccess = {
                    val updatedList = _services.value.map { 
                        if (it.id == service.id) service else it 
                    }
                    _services.value = updatedList
                    _filteredServices.value = _filteredServices.value.map { 
                        if (it.id == service.id) service else it 
                    }
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
    }
    
    fun deleteService(serviceId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            serviceRepository.deleteService(serviceId).fold(
                onSuccess = {
                    _services.value = _services.value.filter { it.id != serviceId }
                    _filteredServices.value = _filteredServices.value.filter { it.id != serviceId }
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class ServiceUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categories: List<String> = emptyList()
)
