package com.g_one_driverapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.g_one_driverapp.api.ConfigAPI
import com.g_one_driverapp.api.reponse.LoginData
import com.g_one_driverapp.api.reponse.LoginResponse
import com.g_one_driverapp.api.reponse.UserResponse
import com.g_one_driverapp.databinding.ActivityLoginBinding
import com.g_one_driverapp.model.UserEntity
import com.g_one_driverapp.preferences.UserPreference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btnToSignIn
import kotlinx.android.synthetic.main.activity_login.et_password
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var preference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = UserPreference(applicationContext)

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
                    if (response.isSuccessful) {
                        // If user successfully logged in
                        // Save user data and access token to SharedPreferences
                        val loginData = LoginData(user = response.body()?.data?.user,
                            response.body()?.data?.access_token
                        )
                        preference.setLoginData(loginData)

                        // Redirect to main activity
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)

                        // Notify success message
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    } else {
                        // Otherwise
                        // Notify failed message
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