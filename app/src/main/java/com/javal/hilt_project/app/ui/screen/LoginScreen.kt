package com.javal.hilt_project.app.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.javal.hilt_project.app.model.Routes
import com.javal.hilt_project.app.ui.viewmodel.LoginViewmodel

@Composable
fun LoginScreen(
    viewmodel: LoginViewmodel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val username by viewmodel.user.observeAsState("")
    val password by viewmodel.password.observeAsState("")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TextField(
            value = username,
            onValueChange = { viewmodel.setUser(it) },
            label = { Text("Usuario") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        TextField(
            value = password,
            onValueChange = { viewmodel.setPassword(it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(), // Mostrar texto como contraseña
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Text(text = "Registrar Usuario", modifier = Modifier.clickable {
            navController.navigate(Routes.resgistrarScreen.route) {
                popUpTo(Routes.resgistrarScreen.route) {
                    inclusive = true
                }
            }
        })
        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {// Verificar que los campos no estén vacíos
                    viewmodel.singAuthEmail(
                        username,
                        password
                    ) {// Llamar a la función de autenticación
                        navController.navigate(Routes.principalScreen.route)// Navegar a la pantalla principal
                    }
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Iniciar sesión")
        }
    }

}