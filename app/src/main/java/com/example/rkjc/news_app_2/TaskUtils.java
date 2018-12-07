package com.example.rkjc.news_app_2;

import android.content.Context;

public class TaskUtils {
    public static final String ACTION_CANCEL_NOTIFICATION = "cancel_notification";
    public static final String ACTION_REFRESH_SYNC = "refresh_sync";

    public static void taskExecution(Context context, String action) {
        if ( action.equals(ACTION_CANCEL_NOTIFICATION) ) {
            cancelNewsNotification(context);
        } else if ( action.equals(ACTION_REFRESH_SYNC) ) {
            syncNewsRefresh(context);
        }
    }

    private static void cancelNewsNotification(Context context) {
        NotificationUtils.clearAllNotifications(context);
    }

    private static void syncNewsRefresh(Context context) {
        NotificationUtils.showNewsNotification(context);
    }
}
