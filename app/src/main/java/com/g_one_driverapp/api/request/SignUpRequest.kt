package com.g_one_driverapp.api.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @field:SerializedName("group_name")
    val group_name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("telp")
    val telp: String,
)