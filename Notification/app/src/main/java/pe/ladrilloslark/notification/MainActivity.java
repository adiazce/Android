package pe.ladrilloslark.notification;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String CHANNEL = "Canal";


    Button btAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver();

        btAction = findViewById(R.id.btAction);
        btAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("pe.ladrilloslark.ACTION");
                sendBroadcast(intent);
            }
        });
        this.createNotificactionChannel();

         //this.showNotification();


    }

    private void showNotification() {
        Intent intent = new Intent(this, NotificationActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder notification = new NotificationCompat
                .Builder(this, CHANNEL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.text_notification))
                .setContentText(getString(R.string.content_notification))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, notification.build());
    }


    private void registerReceiver() {

        NotificationBroadcasReciver notificationBroadcasReciver = new NotificationBroadcasReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("pe.ladrilloslark.ACTION");
        IntentFilter filterairPlaneaMde = new IntentFilter();
        filterairPlaneaMde.addAction("android.intent.action.AIRPLANE_MODE");
        registerReceiver(notificationBroadcasReciver,intentFilter);
        registerReceiver(notificationBroadcasReciver,filterairPlaneaMde);
    }

    public void createNotificactionChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = getString(R.string.channel_name);
            String description = getString(R.string.chennel_descripcion);
            int importacia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL, name, importacia);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
