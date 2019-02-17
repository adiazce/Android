package pe.ladrilloslark.notification;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class NotificationBroadcasReciver    extends BroadcastReceiver {


        private final String CHANNEL = "Canal";
        @Override
        public void onReceive(Context context, Intent intent) {
                String accion = intent.getAction().toString();
                if(accion == "android.intent.action.AIRPLANE_MODE" ){
                        //Toast.makeText(context,"Modo de avion activado",Toast.LENGTH_LONG).show();

                        Intent newIntent = new Intent(context,NotificationActivity.class);


                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        NotificationCompat.Builder notification = new NotificationCompat
                                .Builder(context, CHANNEL)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle(context.getString(R.string.text_notification))
                                .setContentText("Modo de avion activado")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setContentIntent(pendingIntent)

                                .setAutoCancel(true);

                        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                        notificationManagerCompat.notify(1, notification.build());

                }
                if(accion == "pe.ladrilloslark.ACTION" ){
                        Toast.makeText(context,"Accion por button",Toast.LENGTH_LONG).show();
                }

        }
}
