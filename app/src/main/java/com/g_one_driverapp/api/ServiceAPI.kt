package com.g_one_driverapp.api

import com.g_one_driverapp.api.reponse.SignUpResponse
import com.g_one_driverapp.api.request.SignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAPI {
    @POST("users")
    fun createUser(
        @Body response: SignUpRequest
    ): Call<SignUpResponse>
}