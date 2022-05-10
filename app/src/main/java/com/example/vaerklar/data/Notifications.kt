package com.example.vaerklar.data

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.vaerklar.MainActivity

class Notifications(context:Context) {
    private val context = context
    private val intent = Intent(context, MainActivity::class.java)
    private val pendingIntent = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(intent)
        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }
    private val CHANNEL_ID = "channelID"
    private val CHANNEL_NAME = "channelName"

    private fun createNotificationChannel(context:Context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColors(Color.Green)
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    //Sender bare en gang hver fjerde time fordi den ikke skal spamme, men minne deg på at Værklar finnes
    @Composable
    fun setNotification (){
        createNotificationChannel(context)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Værvarsel!")
            .setContentText("Sjekk hva du burde gå med i dag!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setTimeoutAfter(5)
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(0, notification)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP,3600000*4, pendingIntent)
    }
}