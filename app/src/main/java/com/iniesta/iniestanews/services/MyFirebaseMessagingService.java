package com.iniesta.iniestanews.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.iniesta.iniestanews.MainActivity;
import com.iniesta.iniestanews.NewsDetailActivity;
import com.iniesta.iniestanews.utils.NotificationUtils;
import com.iniesta.iniestanews.vo.NotificationVO;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgingService";
    private static final String TITLE = "heading";
    private static final String CONTENTONE = "contentone";
    private static final String CONTENTTWO = "contenttwo";
    private static final String CONTENTTHREE = "contentthree";
    private static final String CONTENTFOUR = "contentfour";
    private static final String IMAGE = "image";
    private static final String ACTION = "action";
    private static final String DATA = "data";
    private static final String CID = "cid";
    private static final String NID = "nid";
    private static final String DATE = "date";
    private static final String ACTION_DESTINATION = "action_destination";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Map<String, String> data = remoteMessage.getData();
            handleData(data);

        } else if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification());
        }// Check if message contains a notification payload.

    }

    private void handleNotification(RemoteMessage.Notification RemoteMsgNotification) {
        String message = RemoteMsgNotification.getBody();
        String title = RemoteMsgNotification.getTitle();
        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setTitle(title);
        notificationVO.setMessage(message);

        Intent resultIntent = new Intent(getApplicationContext(), NewsDetailActivity.class);
        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.displayNotification(notificationVO, resultIntent);
        notificationUtils.playNotificationSound();
    }

    private void handleData(Map<String, String> data) {
        String title = data.get(TITLE);
        String iconUrl = data.get(IMAGE);
        System.out.println(iconUrl);
        String contentone = data.get(CONTENTONE);
        String contenttwo = data.get(CONTENTTWO);
        String contentthree = data.get(CONTENTTHREE);
        String contentfour = data.get(CONTENTFOUR);
        String action = data.get(ACTION);
        String nid = data.get(NID);
        String cid = data.get(CID);
        String date = data.get(DATE);

        String actionDestination = data.get(ACTION_DESTINATION);
        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setTitle(title);
        notificationVO.setIconUrl(iconUrl);
        notificationVO.setMessage("");
        notificationVO.setAction(action);
        notificationVO.setActionDestination(actionDestination);

        Intent resultIntent = new Intent(getApplicationContext(), NewsDetailActivity.class);
        resultIntent.putExtra("title", title);
        resultIntent.putExtra("img",iconUrl);
        resultIntent.putExtra("content1" ,contentone);
        resultIntent.putExtra("content2",contenttwo);
        resultIntent.putExtra("content3",contentthree);
        resultIntent.putExtra("content4",contentfour);
        resultIntent.putExtra("nid",nid);
        resultIntent.putExtra("cid",cid);
        resultIntent.putExtra("date",date);

        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.displayNotification(notificationVO, resultIntent);
        notificationUtils.playNotificationSound();

    }
}
