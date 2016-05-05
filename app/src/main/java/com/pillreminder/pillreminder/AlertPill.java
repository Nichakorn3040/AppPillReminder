package com.pillreminder.pillreminder;

import android.app.AlertDialog;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import static com.pillreminder.pillreminder.DbConstants.PILL_ID;
import static com.pillreminder.pillreminder.DbConstants.PILL_NAME;


public class AlertPill extends IntentService {

    public static final String TAG = "AlertPill";

    public static final String ALERT_PILL_OK = "AlertPill.OK";
    public static final String ALERT_PILL_CANCEL = "AlertPill.Cancel";
    public static int Notify_Id = 1;

    public AlertPill() {
        super(TAG);
    }



    @Override
    protected void onHandleIntent(Intent intent) {

        String action = intent.getAction();
        if (action == null)
            return;

        Log.i("ACTION", action);

        if (action.equals(AlertManager.ALERT_DAY_ACTION)) {

            DbHelpers db = new DbHelpers(this);
            ArrayList<HashMap<String, String>> SelectAllData = db.DatabaseAllPill();
            //ArrayList<ItemDetails> image_details = GetSearchResults();

            SimpleDateFormat date_parser = new SimpleDateFormat("d/M/yyyy");
            SimpleDateFormat time_parser = new SimpleDateFormat("HH:mm");
            Calendar now = Calendar.getInstance();

            Log.i("DATE", "timezone: " + TimeZone.getDefault().getDisplayName());
            Log.i("DATE", "system: " + String.valueOf(System.currentTimeMillis()));
            Log.i("DATE", "current: " + String.valueOf(now.getTime()));
            for (HashMap<String, String> row : SelectAllData) {
                try
                {
                    Log.i("PILL", "name: " + String.valueOf(row.get(DbConstants.PILL_NAME).toString()));

                    Date d = date_parser.parse(row.get(DbConstants.PILL_DATE_START).toString());

                    //if (d.getTime() >= now.getTimeInMillis())
                    {
                        Log.i("DATE", "start: " + d.toString());

                        Calendar t1 = Calendar.getInstance();
                        Calendar t2 = Calendar.getInstance();
                        Calendar t3 = Calendar.getInstance();
                        Calendar t4 = Calendar.getInstance();

                        try {
                            Intent notify_intent = new Intent(this, AlertPill.class);
                            Bundle notify_data = new Bundle();
                            notify_data.putString("id", row.get(PILL_ID).toString());
                            notify_data.putString("name", row.get(PILL_NAME).toString());
                            notify_data.putString("title",  row.get(PILL_NAME).toString());
                            notify_data.putString("text", "เตือนใช้ยา ");

                            t1.setTime(time_parser.parse(row.get(DbConstants.PILL_NOTIFICATION1).toString()));
                            t1.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                            Log.i("TIMECOMP", String.valueOf(t1.getTimeInMillis()) + " " + String.valueOf(now.getTimeInMillis()));
                            if (t1.getTimeInMillis() > now.getTimeInMillis())
                            {
                                Log.i("NOTIFY", t1.getTime().toString());
                                AlertManager.set(this, t1.getTimeInMillis(), notify_intent, notify_data);
                            }
                        } catch (Exception ex) { Log.e("NOTIFY", "set error " + ex.getMessage());
                        }


                        try {
                            Intent notify_intent = new Intent(this, AlertPill.class);
                            Bundle notify_data = new Bundle();
                            notify_data.putString("id", row.get(PILL_ID).toString());
                            notify_data.putString("name", row.get(PILL_NAME).toString());
                            notify_data.putString("title",  row.get(PILL_NAME).toString());
                            notify_data.putString("text", "เตือนใช้ยา ");

                            t2.setTime(time_parser.parse(row.get(DbConstants.PILL_NOTIFICATION2).toString()));
                            t2.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                            if (t2.getTimeInMillis() > now.getTimeInMillis())
                            {
                                Log.i("NOTIFY", t2.getTime().toString());
                                AlertManager.set(this, t2.getTimeInMillis(), notify_intent, notify_data);
                            }
                        } catch (Exception ex) { Log.e("NOTIFY", "set error " + ex.getMessage());
                        }

                        try {
                            Intent notify_intent = new Intent(this, AlertPill.class);
                            Bundle notify_data = new Bundle();
                            notify_data.putString("id", row.get(PILL_ID).toString());
                            notify_data.putString("name", row.get(PILL_NAME).toString());
                            notify_data.putString("title",  row.get(PILL_NAME).toString());
                            notify_data.putString("text", "เตือนใช้ยา ");

                            t3.setTime(time_parser.parse(row.get(DbConstants.PILL_NOTIFICATION3).toString()));
                            t3.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                            if (t3.getTimeInMillis() > now.getTimeInMillis())
                            {
                                Log.i("NOTIFY", t3.getTime().toString());
                                AlertManager.set(this, t3.getTimeInMillis(), notify_intent, notify_data);
                            }
                        } catch (Exception ex) { Log.e("NOTIFY", "set error " + ex.getMessage());
                        }


                        try {
                            Intent notify_intent = new Intent(this, AlertPill.class);
                            Bundle notify_data = new Bundle();
                            notify_data.putString("id", row.get(PILL_ID).toString());
                            notify_data.putString("name", row.get(PILL_NAME).toString());
                            notify_data.putString("title", "Pill 4");
                            notify_data.putString("text", "Eat some");

                            t4.setTime(time_parser.parse(row.get(DbConstants.PILL_NOTIFICATION4).toString()));
                            t4.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                            if (t4.getTimeInMillis() > now.getTimeInMillis())
                            {
                                Log.i("NOTIFY", t4.getTime().toString());
                                AlertManager.set(this, t4.getTimeInMillis(), notify_intent, notify_data);
                            }
                        } catch (Exception ex) { Log.e("NOTIFY", "set error " + ex.getMessage());
                        }

                    }

                }
                catch (Exception ex) {

                    Log.e("DATE", "parse err");
                }
            }

            /*
            {

                for (HashMap<String, String> row : SelectAllData) {

                    for (String key : row.keySet()) {
                        Log.i("DB", key + "=" + row.get(key).toString());
                    }

                }
            } */
        }

        if (action.equals(AlertManager.ALERT_ACTION)) {
            Context context = this;
            NotificationManager notify = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder bldr = new NotificationCompat.Builder(context);

            Bundle data = intent.getExtras();

            Intent intent_open = new Intent(this, AlertPillActivity.class);
            intent.getExtras().putString(PILL_ID, intent.getStringExtra("id"));
            intent_open.putExtras(intent.getExtras());
            PendingIntent pending_open = PendingIntent.getActivity(this, Notify_Id, intent_open, 0);

            bldr.setAutoCancel(true);
            bldr.setSmallIcon(R.mipmap.medicineboxicon);
            bldr.setContentTitle(data.getString("title"));
            bldr.setContentText(data.getString("text"));
            bldr.setContentIntent(pending_open);
            bldr.build();

            notify.notify(Notify_Id++, bldr.build());
        }

    }

}
