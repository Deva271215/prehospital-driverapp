package com.g_one_driverapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.g_one_driverapp.databinding.ActivityMainBinding
import com.g_one_driverapp.preferences.UserPreference
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var preference: UserPreference
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        preference = UserPreference(applicationContext)
        setContentView(binding.root)

        val groupName = preference.getLoginData().user?.group_name
        binding.titleUName.text = "Driver $groupName"

        sendCoordinatetoMaps()
        subscribeToFirebaseTopic()
    }

    private fun sendCoordinatetoMaps() {
        binding.openMaps.setOnClickListener {
            val gmmIntentUri = Uri.parse("google.navigation:q=-8.703472,115.248977")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }

    private fun subscribeToFirebaseTopic() {
        val driverId = preference.getLoginData().user?.id
        FirebaseMessaging.getInstance().subscribeToTopic(driverId.toString()).addOnCompleteListener {
            var message = "Subscribe to $driverId successful!"
            if (!it.isSuccessful) {
                message = "Failed to subscribe to $driverId!"
            }
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }
}