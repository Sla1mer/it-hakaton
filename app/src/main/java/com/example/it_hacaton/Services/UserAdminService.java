package com.example.it_hacaton.Services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
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
        final Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                int size = 0;
                arrayList.clear();//ПЕРЕЗАГРУЖАЕМ МЕТОД RUN В КОТОРОМ ДЕЛАЕТЕ ТО ЧТО ВАМ НАДО
                apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
                Call<List<Event>> call = apiInterface.get_events();

                call.enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        List<Event> object = response.body();
                        for (Event event : object) {
                            arrayList.add(new Item(event.getTo_subject(), event.getDescription()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {

                    }
                });

                if (arrayList.size() != size)
                {
                    String bigText = arrayList.get(arrayList.size() - 1).getDescription();
                    Intent intent2 = new Intent(getApplicationContext(), MainForAdminActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 0);
                    Notification notification = new NotificationCompat.Builder(getApplicationContext(), "ChannelId1")
                            .setContentTitle("IT-HAKATON")
                            .setContentText(arrayList.get(arrayList.size() - 1).getDescription())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                            .setContentIntent(pendingIntent).build();
                    startForeground(1, notification);
                    size = arrayList.size();
                }else {
                    size = arrayList.size();
                }
            }
        }, 0, 4000);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
