package com.g_one_driverapp.api

import com.g_one_driverapp.api.reponse.LoginResponse
import com.g_one_driverapp.api.reponse.SignUpResponse
import com.g_one_driverapp.model.UserEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ServiceAPI {
    @POST("users")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun createUser(
        @Body user: UserEntity
    ): Call<SignUpResponse>

    @POST("auth/sign-in")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun signInUser(
        @Body user: UserEntity
    ): Call<LoginResponse>
}