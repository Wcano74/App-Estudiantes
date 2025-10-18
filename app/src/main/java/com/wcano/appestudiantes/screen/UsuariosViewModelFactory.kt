package com.wcano.appestudiantes.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wcano.appestudiantes.repository.UsuarioRepository
import com.wcano.appestudiantes.viewmodels.UsuariosViewModel

class UsuariosViewModelFactory(private val repo: UsuarioRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuariosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsuariosViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}