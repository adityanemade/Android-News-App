package com.example.rkjc.news_app_2;

import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class NewsFireBaseJobScheduler extends JobService {
    private AsyncTask mAysncTask;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        mAysncTask = new AsyncTask() {
            private NewsRepository mNewsRepository;

            @Override
            protected Object doInBackground(Object[] objects) {
                TaskUtils.taskExecution(NewsFireBaseJobScheduler.this, TaskUtils.ACTION_REFRESH_SYNC);
                mNewsRepository = new NewsRepository(getApplication());
                mNewsRepository.syncNewsDatabase();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(jobParameters, false);
            }
        };

        mAysncTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (mAysncTask != null) mAysncTask.cancel(true);
        return true;
    }

}
