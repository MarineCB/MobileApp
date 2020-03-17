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

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import tp1.androidproject.lifequality.Utils.Constants;

/**
 * Class handling the service
 * Service is started from the boot of the device
 * Send a notification every 2/2h30 to the user
 */
public class NotificationService extends JobService {
    private static final String CHANNEL_ID = "NotificationService" ;

    /**
     * Starts the actions
     */
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

    /**
     * Instantiate and initialize the notification
     * and launch the "repetitive" service by calling scheduleJob
     */
    private void performServiceAction(){
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, SearchActivity.class);
        notificationIntent.putExtra("NotiClick",true);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.life_quality_logo)
                .setContentTitle("Favorite cities")
                .setContentText("Remember what cities you have saved ?")
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
        return true;
    }


    /**
     * Make the task repetitive by calling itself again
     * set the intervals
     */
    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, NotificationService.class);
        JobInfo info = new JobInfo.Builder(0, serviceComponent)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setMinimumLatency(Constants.INTERVAL_MIN)
                .setOverrideDeadline(Constants.INTERVAL_MAX)
                .build();

        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(info);
    }
}
