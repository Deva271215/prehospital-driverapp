package com.g_one_driverapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.g_one_driverapp.preferences.UserPreference

class MainActivity : AppCompatActivity() {
    private lateinit var preference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preference = UserPreference(applicationContext)

        Log.d("Why null?", preference.getLoginData().toString())
    }
}