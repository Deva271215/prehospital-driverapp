package com.g_one_driverapp.api.reponse

data class UserResponse(
    val id: String,
    val group_name: String,
    val email: String,
    val telp: String,
    val is_deleted: Boolean,
    val created_at: String,
    val updated_at: String,
    val deleted_at: String,
)