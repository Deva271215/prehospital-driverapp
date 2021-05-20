package com.g_one_driverapp.api.reponse

import com.google.gson.annotations.SerializedName

data class SignUpResponse (
    @field:SerializedName("error")
    val error: Boolean?,

    @field:SerializedName("statusCode")
    val statusCode: Int?,

    @field:SerializedName("message")
    val message: String?,

    @field:SerializedName("data")
    val data: Data?
)

data class Data (
    val user: UserResponse?
)