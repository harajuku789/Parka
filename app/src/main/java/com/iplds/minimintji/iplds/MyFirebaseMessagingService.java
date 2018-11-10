package com.iplds.minimintji.iplds;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
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

                    NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationCompat.Builder b = new NotificationCompat.Builder(this, CH1);
                    Intent notificationIntent = new Intent(getBaseContext(), ShowStatusFragment.class);

                    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent intent = PendingIntent.getActivity(getBaseContext(),0,notificationIntent,0);

                    b.setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentTitle("IndoorApp")
                            .setContentText(s)
                            .setContentIntent(intent);

                    nm.notify(idDataPayload, b.build());
                }
            }
        }

        if (remoteMessage.getNotification() != null){
            Log.d("Notification Payload", "Message Notification Body: " + remoteMessage.getNotification().getBody());

            String sn = remoteMessage.getNotification().getBody();


            NotificationCompat.Builder b = new NotificationCompat.Builder(this, CH1);
            b.setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("IndoorApp")
                    .setContentText(sn);

            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(idNotiPayload, b.build());

        }
    }

}
