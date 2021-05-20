package com.g_one_driverapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.g_one_driverapp.api.ConfigAPI
import com.g_one_driverapp.api.reponse.LoginResponse
import com.g_one_driverapp.databinding.ActivityLoginBinding
import com.g_one_driverapp.model.UserEntity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btnToSignIn
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onLoginButtonClicked()
        onSignUpTextClicked()
    }

    private fun onLoginButtonClicked() {
        login.setOnClickListener {
            // Get all values from input
            val email = et_username.text.toString().trim()
            val password = et_password.text.toString().trim()

            // Validate input values
            if (email.isEmpty()) {
                et_username.error = "Email yang Anda inputkan tidak terdaftar"
                et_username.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                et_password.error = "Password yang Anda inputkan tidak benar"
                et_password.requestFocus()
                return@setOnClickListener
            }

            // Call API
            val signInUser = UserEntity(email = email, password = password)
            ConfigAPI.instance.signInUser(signInUser).enqueue(object: Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d("Sign up response", response.body().toString())

                    if (response.isSuccessful) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(applicationContext, "Email atau password salah.", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    private fun onSignUpTextClicked() {
        btnToSignIn.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}