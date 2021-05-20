package com.g_one_driverapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.g_one_driverapp.api.ConfigAPI
import com.g_one_driverapp.api.reponse.SignUpResponse
import com.g_one_driverapp.databinding.ActivitySignupBinding
import com.g_one_driverapp.model.UserEntity
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onSignUpButtonClicked()
        onSignInTextClicked()
    }

    private fun onSignUpButtonClicked() {
        signUpButton.setOnClickListener {
            // Get value from all input fields
            val group_name = et_groupName.text.toString().trim()
            val email = et_email.text.toString().trim()
            val telp = et_noHp.text.toString().trim()
            val password = et_password.text.toString().trim()

            // Validate all input fields
            if (group_name.isEmpty()) {
                et_groupName.error = "Masukkan nama group Anda"
                et_groupName.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                et_email.error = "Masukkan nama group Anda"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if (telp.isEmpty()) {
                et_noHp.error = "Masukkan nama group Anda"
                et_noHp.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                et_password.error = "Masukkan nama group Anda"
                et_password.requestFocus()
                return@setOnClickListener
            }

            // Call API
            val signUpUser = UserEntity(group_name, email, telp, password)
            ConfigAPI.instance.createUser(signUpUser).enqueue(object: Callback<SignUpResponse> {
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    if (response.isSuccessful) {
                        // If user successfully signup, then redirect to login screen
                        val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    } else {
                        // Otherwise, notify the error
                        Toast.makeText(applicationContext, "Akun dengan email ${email} sudah terdaftar.", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    private fun onSignInTextClicked() {
        btnToSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}