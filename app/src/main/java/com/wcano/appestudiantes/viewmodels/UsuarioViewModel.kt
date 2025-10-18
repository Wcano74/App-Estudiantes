package com.wcano.appestudiantes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcano.appestudiantes.models.Usuario
import com.wcano.appestudiantes.repository.UsuarioRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UsuariosViewModel(private val repo: UsuarioRepository) : ViewModel() {
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadUsuarios()
    }

    fun loadUsuarios() = viewModelScope.launch {
        _loading.value = true
        _error.value = null
        try {
            repo.fetchUsuarios().onSuccess {
                _usuarios.value = it
            }.onFailure {
                _error.value = "Hubo un error con la conexión"
            }
        } catch (e: Exception) {
            _error.value = "Hubo un error con la conexión"
        }
        _loading.value = false
    }


    fun addUsuario(nombre: String, rol: String, onComplete: (Boolean, String?) -> Unit) = viewModelScope.launch {
        _loading.value = true
        _error.value = null
        try {
            repo.crearUsuario(Usuario(nombre = nombre, rol = rol)).onSuccess {
                _usuarios.value += it
                onComplete(true, null)
            }.onFailure {
                _error.value = "No se pudo crear el usuario"
                onComplete(false, it.message)
            }
        } catch (e: Exception) {
            _error.value = "No se pudo crear el usuario"
            onComplete(false, e.message)
        }
        _loading.value = false
    }


    fun deleteUsuario(id: Int){
        viewModelScope.launch {
            repo.eliminarUsuario(id).onSuccess {
                _usuarios.value = _usuarios.value.filter { it.id != id }
            }.onFailure {
                _error.value = "No se pudo eliminar el usuario"
            }
        }
    }

}
