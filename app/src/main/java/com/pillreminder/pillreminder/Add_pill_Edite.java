package com.pillreminder.pillreminder;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Add_pill_Edite extends AppCompatActivity {
    Database_pill database_pill;
    String[] SelectDataCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_doctor);
        database_pill=new Database_pill(this);

          SelectDataCode= database_pill.SelectDataCode(Database_File_Confix_Center.code_updete);

        final EditText time = (EditText) findViewById(R.id.time);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myTimePickerCallback = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String minuteS="";
                        if(minute<10){
                            minuteS="0"+minute;
                        }else{
                            minuteS=""+minute;
                        }

                        String hourOfDayS="";
                        if(hourOfDay<10){
                            hourOfDayS="0"+hourOfDay;
                        }else{
                            hourOfDayS=""+hourOfDay;
                        }


                        time.setText(hourOfDayS + ":" + minuteS);
                    }
                };


                new TimePickerDialog(Add_pill_Edite.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        myTimePickerCallback,
                        Calendar.getInstance().get(Calendar.HOUR),
                        Calendar.getInstance().get(Calendar.MINUTE),
                        false
                ).show();
            }
        });


        final EditText input1 = (EditText) findViewById(R.id.titel1);
        final EditText input2 = (EditText) findViewById(R.id.titel2);
        final EditText input3 = (EditText) findViewById(R.id.titel3);
        final EditText input4 = (EditText) findViewById(R.id.titel4);
        final EditText input5 = (EditText) findViewById(R.id.titel5);

        time.setText(SelectDataCode[1]);
        input1.setText(SelectDataCode[2]);
        input2.setText(SelectDataCode[3]);
        input3.setText(SelectDataCode[4]);
        input4.setText(SelectDataCode[5]);
        input5.setText(SelectDataCode[6]);



        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setText("แก้ไข");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(time.getText().toString().equals(" ")||input1.getText().toString().equals(" ")
                        ||input1.getText().toString().equals(" ")
                        ||input2.getText().toString().equals(" ")
                        ||input3.getText().toString().equals(" ")
                        ||input4.getText().toString().equals(" ")
                        ||input5.getText().toString().equals(" ")){
                    Toast.makeText(Add_pill_Edite.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Add_pill_Edite.this, "แก้ไขเรียบร้อย", Toast.LENGTH_SHORT).show();
                     database_pill.UpdateData(null,time.getText().toString(),input1.getText().toString() ,
                     input2.getText().toString() ,input3.getText().toString() ,input4.getText().toString()
                    ,input5.getText().toString() ,"","","" );
                    Add_pill_Edite.this.finish();
                }

            }
        });
        final Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setText("ลบ");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database_pill.DeleteData(Database_File_Confix_Center.code_updete);
                Toast.makeText(Add_pill_Edite.this, "ลบเรียบร้อย", Toast.LENGTH_SHORT).show();
                Add_pill_Edite.this.finish();
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
