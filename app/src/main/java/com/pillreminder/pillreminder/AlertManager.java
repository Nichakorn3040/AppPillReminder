package com.pillreminder.pillreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by dml on 26/04/2016.
 */
public class AlertManager {

    public static final String TAG = "AlertManager";
    public static final String ALERT_ACTION = "AlertManager.Action";
    public static final String ALERT_DAY_ACTION = "AlertManager.DayAction";

    public static int LastID = 1;

    public static void set(Context context, long millis, Intent intent, Bundle data) {

        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        intent.putExtras(data);
        intent.setAction(ALERT_ACTION);
        PendingIntent pending = PendingIntent.getService(context, LastID++, intent, 0);
        //PendingIntent.getBroadcast(context, 0, intent, 0);
        alarm.set(AlarmManager.RTC_WAKEUP, millis, pending);

        Log.i(TAG, "set alerm id " + String.valueOf(LastID) + " time " + String.valueOf(millis));

    }

    public static void setEveryday(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        intent.setAction(ALERT_DAY_ACTION);
        PendingIntent pending = PendingIntent.getService(context, LastID++, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending);
        context.startService(intent);
        Log.i(TAG, "set alerm everyday");
    }



}
