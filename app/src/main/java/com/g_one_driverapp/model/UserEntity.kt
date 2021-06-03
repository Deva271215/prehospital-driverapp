package com.g_one_driverapp.model

import com.g_one_driverapp.api.reponse.HospitalResponse
import com.google.gson.annotations.SerializedName

data class UserEntity (
    @field:SerializedName("account_type")
    val account_type: String? = null,

    @field:SerializedName("group_name")
    val group_name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("telp")
    val telp: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("hospital")
    val hospital: HospitalResponse? = null,

    @field:SerializedName("fcm_token")
    val fcm_token: String? = ""
)