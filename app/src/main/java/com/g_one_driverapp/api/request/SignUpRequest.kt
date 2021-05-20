package com.g_one_driverapp.api.request

data class SignUpRequest(
    val group_name: String,
    val email: String,
    val password: String,
    val telp: String,
)