package com.iplds.minimintji.iplds;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.iplds.minimintji.iplds.activity.CheckIsDriveOutActivity;
import com.iplds.minimintji.iplds.activity.WelcomeActivity;
import com.iplds.minimintji.iplds.fragment.ShowStatusFragment;

import java.util.Date;
import java.util.Locale;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    int idDataPayload=createId(), idNotiPayload=createId();
    private final String CH1 = "CH1";

    public MyFirebaseMessagingService(){

    }

    public int createId(){
        Date now = new Date();
        int id = Integer.parseInt(new java.text.SimpleDateFormat("ddHHmmss", Locale.US).format(now));

        return id;
    }

    @Override
    public void onNewToken(String token) {
        Log.d("TAG", "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(token);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Chanel Test 1";
            String description = "Chenel for test 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CH1, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("getFrom", "From: "+remoteMessage.getFrom());

        if (remoteMessage.getData() != null){
            if (remoteMessage.getData().size() > 0){
                for (String k : remoteMessage.getData().keySet()){
                    String s = remoteMessage.getData().get(k);
                    Log.d("Data Payload: ", "Message data payload: "+remoteMessage.getData().get(k));

                    Intent resultIntent = new Intent(getApplicationContext(),WelcomeActivity.class);
                    resultIntent.putExtra("test","test");
                    resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                    stackBuilder.addNextIntentWithParentStack(resultIntent);

                    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CH1);
                    builder.setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentIntent(resultPendingIntent)
                            .setContentTitle("Parka Test")
                            .setContentText(s)
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_SOUND);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                    notificationManager.notify(idDataPayload, builder.build());
                }
            }
        }

        if (remoteMessage.getNotification() != null){
            Log.d("Notification Payload", "Message Notification Body: " + remoteMessage.getNotification().getBody());

            String sn = remoteMessage.getNotification().getBody();

            Intent resultIntent = new Intent(getApplicationContext(),WelcomeActivity.class);
            resultIntent.putExtra("test","test");
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntentWithParentStack(resultIntent);

            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CH1);
            builder.setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentIntent(resultPendingIntent)
                    .setContentTitle("Parka Test")
                    .setContentText(sn)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(idNotiPayload, builder.build());
        }
    }

}
