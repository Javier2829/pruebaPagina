package com.javal.hilt_project.app.model

data class User(
    val id: Int?,
    val userId: String,
    val displayName: String,
    val email: String,
    val cargo: String
) {
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "userId" to this.userId,
            "displayName" to this.displayName,
            "email" to this.email,
            "cargo" to this.cargo,
        )
    }
}
