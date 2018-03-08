package com.exercise.temi.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.exercise.temi.R;
import com.exercise.temi.util.di.component.DaggerPubNubServiceComponent;
import com.exercise.temi.util.di.module.PubNubModule;
import com.exercise.temi.view.activity.MainActivity;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import java.util.Arrays;
import javax.inject.Inject;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class PubNubService extends Service{
    // Binder given to clients
    //@Inject
    Binder binder = new PubNubService.LocalBinder();
    private boolean isSender;
    @Inject
    PubNub pubnub;

    private static final String SUBSCRIBE_KEY = "sub-c-47358ad6-21ad-11e8-a183-761142583d66";
    private static final String PUBLISH_KEY = "pub-c-fdabfaec-33e4-4450-bb17-45c73494671d";
    private static final String CHAT_CHANNEL = "temi-test-channel";
    private static final String TAG = "PubNubService";

    public PubNubService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerPubNubServiceComponent.builder()
                .pubNubModule(new PubNubModule(PUBLISH_KEY, SUBSCRIBE_KEY))
                .build()
                .inject(this);

        addPubNubListener();
        subscribeToChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void subscribeToChannel() {
        pubnub.subscribe().channels(Arrays.asList(CHAT_CHANNEL)).execute();
    }

    private void addPubNubListener() {
        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                Log.v(TAG, "New message: " + message.getMessage().toString());
                if (!isSender)
                    showNotification(message.getMessage().getAsString());
                isSender = false;
            }

            // STATUS EVENTS
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                Log.d(TAG, "Status: " + status.getCategory().toString());
            }

            // PRESENCE EVENTS
            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {
                Log.d(TAG, "begin presence: " + presence.getEvent() + ", " + presence.getUuid());
            }
        });
    }

    public void publishMessageToChannel(String... words){
        isSender = true;
        pubnub.publish()
                .message(Arrays.asList(words))
                .channel(CHAT_CHANNEL)
                .async(new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {
                        if (status.isError()) {
                            Log.e(TAG, "Error: " + status.toString());
                        } else {
                            Log.d(TAG, "Message is Published!");
                        }
                    }
                });
    }

    private void showNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHAT_CHANNEL, "My Notifications", NotificationManager.IMPORTANCE_HIGH);
            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHAT_CHANNEL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New Message Arrived!")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pubnub.unsubscribeAll();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        public PubNubService getService() {
            return PubNubService.this;
        }
    }

}
