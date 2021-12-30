package com.xcellity.yournotification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressLint("OverrideAbstract")
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationService extends NotificationListenerService {

    SQLiteDataBaseListner db;
    List<NotiModel> imageOffline = new ArrayList<>();

    private static final class ApplicationPackageNames {
        public static final String FACEBOOK_PACK_NAME = "com.facebook.katana";
        public static final String FACEBOOK_MESSENGER_PACK_NAME = "com.facebook.orca";
        public static final String WHATSAPP_PACK_NAME = "com.whatsapp";
        public static final String INSTAGRAM_PACK_NAME = "com.instagram.android";
    }

    public static final class InterceptedNotificationCode {
        public static final int FACEBOOK_CODE = 1;
        public static final int WHATSAPP_CODE = 2;
        public static final int INSTAGRAM_CODE = 3;
        public static final int OTHER_NOTIFICATIONS_CODE = 4; // We ignore all notification with code == 4
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        int notificationCode = matchNotificationCode(sbn);
        db = new SQLiteDataBaseListner(this);

        String pack = sbn.getPackageName();
        long dateLong = (sbn.getPostTime());
        Date date = new Date(dateLong);

        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = extras.getString("android.text");

        if (!TextUtils.isEmpty(text)) {
            db.insertData(title, pack, text, String.valueOf(date));
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        if (notificationCode != InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE) {
            Intent intent = new Intent("com.xcellity.yournotification");
            intent.putExtra("Notification Code", notificationCode);
            sendBroadcast(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private int matchNotificationCode(StatusBarNotification sbn) {
        String packageName = sbn.getPackageName();

        if (packageName.equals(ApplicationPackageNames.FACEBOOK_PACK_NAME)
                || packageName.equals(ApplicationPackageNames.FACEBOOK_MESSENGER_PACK_NAME)) {
            return (InterceptedNotificationCode.FACEBOOK_CODE);
        } else if (packageName.equals(ApplicationPackageNames.INSTAGRAM_PACK_NAME)) {
            return (InterceptedNotificationCode.INSTAGRAM_CODE);
        } else if (packageName.equals(ApplicationPackageNames.WHATSAPP_PACK_NAME)) {
            return (InterceptedNotificationCode.WHATSAPP_CODE);
        } else {
            return (InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE);
        }
    }
}
