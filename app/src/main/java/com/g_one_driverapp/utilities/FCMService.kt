package com.g_one_driverapp.utilities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.g_one_driverapp.R
import com.g_one_driverapp.preferences.UserPreference
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random.Default.nextInt

class FCMService: FirebaseMessagingService() {
    private lateinit var preference: UserPreference

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        preference = UserPreference(applicationContext)

        showNotification(
            p0.data["title"].toString(),
            p0.data["body"].toString(),
            p0.data["click_action"].toString()
        )
    }

    private fun showNotification(title: String, body: String, clickAction: String) {
        val channelId = "default"
        var intent = Intent(clickAction)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationId = nextInt()
        val channel = NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            createNotificationChannel(channel)
            notify(notificationId, builder.build())
        }
    }
}