package com.example.it_hacaton.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Adapters.UsersForAdminAdapter;
import com.example.it_hacaton.Admin.LoginActivity;
import com.example.it_hacaton.Admin.MainForAdminActivity;
import com.example.it_hacaton.Items.Item;
import com.example.it_hacaton.Items.ItemUsersForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.Users.MainActivity;
import com.example.it_hacaton.model.Event;
import com.example.it_hacaton.model.GetPersonFromDBPersonal;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdminService extends Service {

    public static final String CHANNEL_ID = "hakaton";
    private ApiInterface apiInterface;
    private ArrayList<Item> arrayList = new ArrayList<>();
    private int size = 0;
    private int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intent2 = new Intent(getApplicationContext(), MainForAdminActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("IT-HAKATON");
        builder.setContentText("Отслеживание сообщений");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Отслеживание сообщений"));
        builder.setContentIntent(pi).build();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("notification_id");
        }
// Add additional:
// [adaptive Android8.0] set NotificationChannel to NotificationManager object
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("notification_id", "notification_name", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        startForeground(1, builder.build());
        final Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                arrayList.clear();
                apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
                Call<List<Event>> call = apiInterface.get_events();
                call.enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        List<Event> object = response.body();
                        for (Event event : object) {
                            arrayList.add(new Item(event.getTo_subject(), event.getDescription()));
                        }

                        if (arrayList.size() < size && count != 0)
                        {
                            String bigText = arrayList.get(arrayList.size() - 1).getDescription();
                            Intent intent2 = new Intent(getApplicationContext(), MainForAdminActivity.class);
                            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 0);

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                            builder.setSmallIcon(R.mipmap.ic_launcher);
                            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                            builder.setContentTitle("IT-HAKATON");
                            builder.setContentText(String.valueOf(bigText));
                            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
                            builder.setContentIntent(pi).build();
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                builder.setChannelId("notification_id");
                            }
                            startForeground(1, builder.build());
                            size = arrayList.size();
                        }else {
                            size = arrayList.size();
                            count = 1;
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {

                    }
                });
            }
        }, 0, 4000);
        return START_NOT_STICKY;
    }

    private void createNotificationChannel() {

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notification_id", "notification_name", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
