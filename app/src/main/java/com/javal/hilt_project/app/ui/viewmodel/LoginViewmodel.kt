package com.javal.hilt_project.app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.javal.hilt_project.app.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewmodel @Inject constructor() : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    private var _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private var _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private var _newUser = MutableLiveData<String>()
    val newUser: LiveData<String> = _newUser

    private var _newPass = MutableLiveData<String>()
    val newPass: LiveData<String> = _newPass

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setUser(user: String) {
        _user.value = user
    }

    //autentificacion de usuario
    fun singAuthEmail(email: String, password: String, home: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Login", "signInWithEmail:success")
                        home()
                    } else {
                        Log.w("Login", "signInWithEmail:failure ${task.result.toString()}")
                    }
                }
        } catch (e: Exception) {
            Log.w("Login", "signInWithEmail:failure: ${e.message}")

        }
    }

    //creacion de usuario
    fun creaUsuario(email: String, password: String, home: () -> Unit) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName =
                            task.result.user?.email?.split('@')
                                ?.get(0)//se obtiene el nombre del usuario
                        crearUsuario(displayName)
                        Log.d("Login", "Creacion de usuario exitosa")
                        home()
                    } else {
                        Log.w("Login", "Error al crear usuario: ${task.exception}")
                    }
                    _loading.value = false

                }
        }

    }

    private fun crearUsuario(displayName: String?) {
        val userId = auth.currentUser?.uid
        // val user = mutableMapOf<String, Any>()
        // user["uid"] = userId.toString()
        //user["nombre"] = displayName.toString()
        // user["email"] = auth.currentUser?.email.toString()
        val user = User(
            userId = userId.toString(),
            displayName = displayName.toString(),
            email = auth.currentUser?.email.toString(),
            cargo = "Administrador",
            id = null
        ).toMap()
        Log.d("Login", "Usuario: $user")
        //guardar en firebase
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("Login", "Usuario creado con exito${it.id}")
            }
            .addOnFailureListener {
                Log.w("Login", "Error al crear usuario: ${it.message}")
            }


    }

    fun onChangeUser(nuser: String) {
        _newUser.value = nuser
    }

    fun onChangePass(npass: String) {
        _newPass.value = npass
    }
}