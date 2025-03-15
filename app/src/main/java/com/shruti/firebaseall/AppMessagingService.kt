package com.shruti.firebaseall

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class AppMessagingService : FirebaseMessagingService() {
    val TAG = "FireBaseServices"
    var firebase = ""
    var channelId = "FCM channel"
    var channelDescription = "FCM channel used for messaging"
    lateinit var notificationManager: NotificationManager


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notification = message.notification
        System.out.print("notification title ${notification?.title}")
        System.out.print("notification body ${notification?.body}")
        System.out.println("type ${notification}")
        System.out.print("${message.data}")
        firebase = message.data.toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = getString(R.string.app_name)
            val descriptionText = channelDescription
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val ChannelId= channelId

            val channel = NotificationChannel(ChannelId,name,importance).apply{
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
        generateNotification(message)
    }

    private fun generateNotification(notificationData: RemoteMessage){
        val intent = Intent(this,FcmActivity::class.java)
        intent.putExtra("data", firebase)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val pendingIntent = PendingIntent.getActivity(this,1,intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(notificationData?.notification?.title?:"")
            .setContentText(notificationData?.notification?.body?:"")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setContentIntent(pendingIntent)
        notificationManager.notify(System.currentTimeMillis().toInt(),builder.build())
    }
}