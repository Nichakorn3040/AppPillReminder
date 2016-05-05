package com.pillreminder.pillreminder;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Doctor_content extends AppCompatActivity {
    Database_pill database_pill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_pill);
        database_pill=new Database_pill(this);

        final EditText time = (EditText) findViewById(R.id.time);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener DatePickerCallback = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        time.setText(new StringBuilder()
                                .append(dayOfMonth).append("/")
                                .append(monthOfYear+1).append("/")
                                .append(year)
                        );
                    }
                };
                new DatePickerDialog(Doctor_content.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        DatePickerCallback,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });


        final EditText input1 = (EditText) findViewById(R.id.titel1);
        final EditText input2 = (EditText) findViewById(R.id.titel2);
        final EditText input3 = (EditText) findViewById(R.id.titel3);
        final EditText input4 = (EditText) findViewById(R.id.titel4);
        final EditText input5 = (EditText) findViewById(R.id.titel5);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(time.getText().toString().equals(" ")||input1.getText().toString().equals(" ")
                        ||input1.getText().toString().equals(" ")
                        ||input2.getText().toString().equals(" ")
                        ||input3.getText().toString().equals(" ")
                        ||input4.getText().toString().equals(" ")
                        ||input5.getText().toString().equals(" ")){
                    Toast.makeText(Doctor_content.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Doctor_content.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                    database_pill.InsertData(null,time.getText().toString(),input1.getText().toString() ,
                            input2.getText().toString() ,input3.getText().toString() ,input4.getText().toString()
                    ,input5.getText().toString() ,"","","" );
                    Doctor_content.this.finish();;
                }

            }
        });




        final Button btnCancel = (Button) findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.setText("");
                input1.setText("");
                input2.setText("");
                input3.setText("");
                input4.setText("");
                input5.setText("");

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
