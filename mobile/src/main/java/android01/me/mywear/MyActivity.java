package android01.me.mywear;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity {
    private Button mButton;
    public static int notificationId = 001;
    private static final String EXTRA_VOICE_REPLY = "voice_reply";

    public static NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
                bigStyle.bigText("eventDescription");

                String replyLabel = getResources().getString(R.string.reply_label);
                String[] replyChoices = getResources().getStringArray(R.array.reply_choices);

               RemoteInput mRemoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
                        .setLabel(replyLabel)
                        .setChoices(replyChoices)
                        .build();
                Intent replyIntent = new Intent(getApplicationContext(), MyActivity2.class);

                PendingIntent replyPendingIntent =
                        PendingIntent.getActivity(getApplicationContext(), 0, replyIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Action mAction =
                        new NotificationCompat.Action.Builder(R.drawable.ic_sms_failed_white_36dp,
                                getString(R.string.reply_label), replyPendingIntent)
                                .addRemoteInput(mRemoteInput)
                                .build();


                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.ic_error_white_48dp)
                                .setLargeIcon(BitmapFactory.decodeResource(
                                        getResources(), R.drawable.moto360))
                                .setContentTitle("Android01")
                                .setContentText("Our First Android Waer")
                                .setStyle(bigStyle)
                                .addAction(mAction);

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(notificationId, notificationBuilder.build());
            }
        });
    }

    public static void cancelNotification() {
        notificationManager.cancel(notificationId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
