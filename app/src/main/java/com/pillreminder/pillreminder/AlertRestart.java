package com.pillreminder.pillreminder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AlertRestart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_alert_restart);

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setCancelable(false);
        dlg.setIcon(R.mipmap.medicineboxicon);
        dlg.setMessage("ต้องเริ่มโปรแกรมใหม่เพื่อการตั้งเวลาเตือนทำงาน ?");
        dlg.setTitle("Restart application");
        dlg.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        dlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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

    private void dismiss() {
        this.finish();
    }
}
