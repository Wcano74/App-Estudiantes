package com.wcano.appestudiantes

import android.R.attr.password
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.wcano.appestudiantes.repository.UsuarioRepository
import com.wcano.appestudiantes.screen.UsuariosScreen
import com.wcano.appestudiantes.screen.UsuariosViewModelFactory
import com.wcano.appestudiantes.services.RetrofitClient
import com.wcano.appestudiantes.viewmodels.UsuariosViewModel

class MainActivity : ComponentActivity() {
    private val baseUrl = "http://umg2025-001-site1.qtempurl.com/"
    private val username = "11269739"
    private val password = "60-dayfreetrial"


    private val viewModel: UsuariosViewModel by viewModels {
       val api =  RetrofitClient.create(baseUrl, username, password)
        val repo = UsuarioRepository(api)
        UsuariosViewModelFactory(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsuariosScreen(viewModel = viewModel)
        }
    }
}


