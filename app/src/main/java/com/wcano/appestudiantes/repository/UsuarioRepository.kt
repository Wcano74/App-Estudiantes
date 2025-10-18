package com.wcano.appestudiantes.repository

import com.wcano.appestudiantes.models.Usuario
import com.wcano.appestudiantes.services.ApiService


class UsuarioRepository(private val api: ApiService) {
    suspend fun fetchUsuarios(): Result<List<Usuario>> {
        return try {
            val list = api.getUsuarios()
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun crearUsuario(usuario: Usuario): Result<Usuario> {
        return try {
            val response = api.crearUsuario(usuario)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun eliminarUsuario(id: Int): Result<Unit> {
        return try {
            val response = api.eliminarUsuario(id)
            Result.success(response)
            } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
