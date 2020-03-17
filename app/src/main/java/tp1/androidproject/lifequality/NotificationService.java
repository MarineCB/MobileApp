package tp1.androidproject.lifequality;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class NotificationService extends JobService {
    private static final String CHANNEL_ID = "NotificationService" ;

    private static final long HOUR_IN_MILLIS = TimeUnit.HOURS.toMillis(1);
    private static final long MIN_IN_MILLIS = TimeUnit.MINUTES.toMillis(1);
    private static final long INTERVAL_MIN = HOUR_IN_MILLIS * 2;
    private static final long INTERVAL_MAX = HOUR_IN_MILLIS * 2 + MIN_IN_MILLIS*30;

    @Override
    public boolean onStartJob(JobParameters params) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                performServiceAction();
            }
        }).run();

        return true;
    }

    private void performServiceAction(){
        Log.i("JobServiceSample", "MainJobService start");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, SearchActivity.class);
        notificationIntent.putExtra("NotiClick",true);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.life_quality_logo)
                .setContentTitle("Favorite cities")
                .setContentText("Remember what cities you've saved ?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(12345, builder.build());
        NotificationService.scheduleJob(getApplicationContext());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Notification Channel test", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("JobServiceSample", "MainJobService stop" );
        return true;
    }


    public static void scheduleJob(Context context) {
        Log.i("JobServiceSample", "schedule job start");
        ComponentName serviceComponent = new ComponentName(context, NotificationService.class);
        JobInfo info = new JobInfo.Builder(0, serviceComponent)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setMinimumLatency(INTERVAL_MIN)
                .setOverrideDeadline(INTERVAL_MAX)
                .build();

        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(info);
    }
}
