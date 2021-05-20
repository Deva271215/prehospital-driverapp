package com.g_one_driverapp.api.reponse

data class SignUpResponse (
    val error: Boolean,
    val statusCode: Int,
    val message: String,
    val data: Data
)

data class Data (
    val user: UserResponse
)