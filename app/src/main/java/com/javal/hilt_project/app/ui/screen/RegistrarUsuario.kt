package com.javal.hilt_project.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.javal.hilt_project.app.ui.viewmodel.LoginViewmodel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import com.javal.hilt_project.app.model.Routes

@Composable
fun RegistrarUsuario(
    viewModel: LoginViewmodel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val username by viewModel.user.observeAsState("")
    val password by viewModel.password.observeAsState("")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registro de Usuario")
        TextField(
            value = username,
            onValueChange = { viewModel.setUser(it) },
            label = { Text(text = "Usuario") }
        )
        TextField(
            value = password,
            onValueChange = { viewModel.setPassword(it) },
            label = { Text(text = "Contraseña") }
        )

        Row(
            modifier = modifier.padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(onClick = {
                navController.navigate(Routes.principalScreen.route)

            }) {
                Text(text = "Registrar")
            }
            Button(onClick = {
                navController.popBackStack(Routes.loginScreen.route, true)
            }) {
                Text(text = "Cancelar")

            }


        }

    }
}