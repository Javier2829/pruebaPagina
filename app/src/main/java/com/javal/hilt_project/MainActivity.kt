package com.javal.hilt_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.javal.hilt_project.app.model.Routes
import com.javal.hilt_project.app.ui.screen.LoginScreen
import com.javal.hilt_project.app.ui.screen.PrincipalScreen
import com.javal.hilt_project.app.ui.screen.RegistrarUsuario
import com.javal.hilt_project.app.ui.viewmodel.LoginViewmodel
import com.javal.hilt_project.ui.theme.Hilt_projectTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val LoginViewmodel: LoginViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hilt_projectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: LoginViewmodel = viewModel(
                        factory = ViewModelProvider.AndroidViewModelFactory()
                    )
                    NavHost(
                        navController = navController,
                        startDestination = Routes.loginScreen.route
                    ) {
                        composable(Routes.loginScreen.route) {
                            LoginScreen(
                                viewModel,
                                navController
                            )
                        }
                        composable(Routes.principalScreen.route) {
                            PrincipalScreen(viewModel,navController)
                        }
                        composable(Routes.resgistrarScreen.route) {
                            RegistrarUsuario(viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}
