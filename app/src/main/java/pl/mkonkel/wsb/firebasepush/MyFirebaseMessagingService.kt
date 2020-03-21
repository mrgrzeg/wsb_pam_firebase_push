package pl.mkonkel.wsb.firebasepush

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


// TODO: Implement your custom Messaging service.
class MyFirebaseMessagingService : FirebaseMessagingService()
{




    private val notificationManager by lazy {
        applicationContext.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val title = remoteMessage.notification?.title ?:"empty title"// uzyj jedynie gdu nei jest null!
        val message = remoteMessage.notification?.body ?:"empty message"
        Log.d("MESSAGE", "title: $title / message: $message ")

        createNotificationChanel()
        bulidNotification()
       val notification:Notification= bulidNotification(title)

    }




    //    TODO: Add a helper method for creating the Notification Channel
//    You will need to provide:
//    - Channel Id
//    - Channel Name
//    - Channel Importance
//
//    You can also add some extra configuration like "lights", "vibration" etc...
//    Remember to register created channel in the notificationManager

    private fun createNotificationChanel()

    {

        val notificationChannel=NotificationChannel(
            CHANEL_ID
        )


  notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        notificationChannel.vibrationPattern= longArrayOf(100,200,300,500,100,200)
        notificationChannel.lightColor=Color.BLUE
        notificationChannel.description="def"

        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun bulidNotification(notificationTitle: String?):Notification{
        return Notification.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_default_notification)
            .setContentTitle("Uwaga")
            .setContentText(notificationTitle)
            .setAutoCancel(true)
            .build()

    }



//    TODO: Add a helper method for building the received notification
//    You need to use "NotificationCompat.Builder", and provide some basic configuration like:
//    - Small Icon
//    - Content Title
//    - Content Text
//    - etc...




//    TODO: Add a helper method for creating the Pending Intent that will allow us to run some activity
//    If you want to pass the notification to the Activity you must use the Extras

 companion object
 {
      const val NOTIFICATION_MESSAGE_TITLE = "message_title"
       const val NOTIFICATION_MESSAGE_BODY = "message_body"
   }
}