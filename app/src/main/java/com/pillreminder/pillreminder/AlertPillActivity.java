package com.pillreminder.pillreminder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlertPillActivity extends AppCompatActivity {

    private Bundle data;
    private AlertDialog.Builder dlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_pill);

        data = getIntent().getExtras();
        if (data == null)
            finish();

        dlg = new AlertDialog.Builder(this);
        dlg.setTitle(data.getString("title"));
        dlg.setMessage(data.getString("text"));
        dlg.setCancelable(false);
        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveStatus("Y");
            }
        });

        dlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveStatus("N");
            }
        });

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dismiss();
            }
        });

        dlg.show();
    }

    private void saveStatus(String status) {

        DbPillStats Db = new DbPillStats(this);
        Bundle data = this.getIntent().getExtras();

        SimpleDateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();


        Db.InsertData(data.getString("id"), data.getString("name"), status, sqlformat.format(now));
    }

    private void dismiss() {
        this.finish();
    }
}
