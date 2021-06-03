package com.g_one_driverapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.g_one_driverapp.preferences.UserPreference
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var preference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preference = UserPreference(applicationContext)

        subscribeToFirebaseTopic()
    }

    private fun subscribeToFirebaseTopic() {
        val driverId = preference.getLoginData().user?.id
        FirebaseMessaging.getInstance().subscribeToTopic(driverId.toString()).addOnCompleteListener {
            var message = "Subscribe to $driverId successful!"
            if (!it.isSuccessful) {
                message = "Failed to subscribe to $driverId!"
            }
            Log.i("hospital_code", driverId!!)
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }
}