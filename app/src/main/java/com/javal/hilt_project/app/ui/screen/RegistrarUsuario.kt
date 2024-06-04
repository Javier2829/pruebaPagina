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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.javal.hilt_project.app.model.Routes

@Composable
fun RegistrarUsuario(
    viewModel: LoginViewmodel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val username by viewModel.newUser.observeAsState(initial = "")
    val password by viewModel.newPass.observeAsState(initial = "")

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
            onValueChange = { viewModel.onChangeUser(it) },
            label = { Text(text = "Usuario") },
            modifier = modifier.padding(top = 10.dp),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        TextField(
            value = password,
            onValueChange = { viewModel.onChangePass(it) },
            label = { Text(text = "Contraseña") },
            modifier = modifier.padding(top = 10.dp),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Row(
            modifier = modifier.padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.creaUsuario(username, password) {
                        navController.navigate(Routes.principalScreen.route)
                    }
                }

            }) {
                Text(text = "Registrar")
            }
            Button(onClick = {
                navController.navigate(Routes.loginScreen.route)
            }) {
                Text(text = "Cancelar")

            }


        }

    }
}