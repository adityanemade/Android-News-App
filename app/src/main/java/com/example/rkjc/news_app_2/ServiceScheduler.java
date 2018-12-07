package com.example.rkjc.news_app_2;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class ServiceScheduler {
    private static final int SCHEDULE_INTERVAL_SECONDS = (int) (TimeUnit.SECONDS.toSeconds(10));;
    private static final int SYNC_FLEXTIME_SECONDS = SCHEDULE_INTERVAL_SECONDS;
    private static final String REFRESH_JOB_TAG = "refresh_job_tag";
    private static boolean initialized = false;

    synchronized public static void refreshJob(@NonNull final Context context) {
        if(initialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job refreshNewsJob = dispatcher.newJobBuilder()
                .setService(NewsFireBaseJobScheduler.class)
                .setTag(REFRESH_JOB_TAG)
                .setRecurring(true)
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(true)
                .setTrigger(Trigger.executionWindow(SCHEDULE_INTERVAL_SECONDS,SCHEDULE_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .build();

        dispatcher.schedule(refreshNewsJob);
        initialized = true;
    }
}
