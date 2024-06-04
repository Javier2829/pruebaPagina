package com.javal.hilt_project.app.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javal.hilt_project.app.model.Routes
import com.javal.hilt_project.app.ui.viewmodel.LoginViewmodel

@Composable
fun PrincipalScreen(
    viewModel: LoginViewmodel = viewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Bienvenido a la App :",
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}