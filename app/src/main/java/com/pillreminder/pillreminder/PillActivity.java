package com.pillreminder.pillreminder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static com.pillreminder.pillreminder.DbConstants.PILL_AMOUNT;
import static com.pillreminder.pillreminder.DbConstants.PILL_DATE_START;
import static com.pillreminder.pillreminder.DbConstants.PILL_DOSE;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN_EVENING;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN_MORNING;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN_NIGHT;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN_NOON;
import static com.pillreminder.pillreminder.DbConstants.PILL_NOTIFICATION1;
import static com.pillreminder.pillreminder.DbConstants.PILL_NOTIFICATION2;
import static com.pillreminder.pillreminder.DbConstants.PILL_NOTIFICATION3;
import static com.pillreminder.pillreminder.DbConstants.PILL_NOTIFICATION4;
import static com.pillreminder.pillreminder.DbConstants.PILL_ID;
import static com.pillreminder.pillreminder.DbConstants.PILL_NAME;
import static com.pillreminder.pillreminder.DbConstants.PILL_TABLE;

public class PillActivity extends Activity {
    // An array containing list of Country Names


    EditText pill_name,pill_amount,pill_dose,pill_eaten,pill_date_start,pill_notification1,pill_notification2,pill_notification3,pill_notification4;
    CheckBox pill_eaten_morning,pill_eaten_noon,pill_eaten_evening,pill_eaten_night;
    Button button_pill_save,button_pill_delete;
    DbHelpers dbHelper;
    Database_pill databae_pill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pill);
        databae_pill=new Database_pill(this);
        ArrayList<HashMap<String, String>> SelectAllData=databae_pill.SelectAllData();

        pill_name = (EditText) findViewById(R.id.pill_name);
        pill_amount = (EditText) findViewById(R.id.pill_amount);
        pill_dose = (EditText) findViewById(R.id.pill_dose);
        pill_eaten = (EditText) findViewById(R.id.pill_eaten);
        pill_date_start = (EditText) findViewById(R.id.pill_date_start);
        //pill_eaten_time = (EditText) findViewById(R.id.pill_eaten_time);  pill_eaten_time
        pill_notification1 = (EditText) findViewById(R.id.pill_notification1);
        pill_notification2 = (EditText) findViewById(R.id.pill_notification2);
        pill_notification3 = (EditText) findViewById(R.id.pill_notification3);
        pill_notification4 = (EditText) findViewById(R.id.pill_notification4);
        final List<String> arrList = new ArrayList<String>();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        for(int d=0;d<SelectAllData.size();d++){
            arrList.add(SelectAllData.get(d).get("col1"));
        }


        ArrayAdapter<String> arrAd = new ArrayAdapter<String>(PillActivity.this, android.R.layout.simple_spinner_item, arrList);
        spinner.setAdapter(arrAd);
        spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int i, long l) {
                // TODO Auto-generated method stub


            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });


        pill_eaten_morning = new CheckBox(this);
        pill_eaten_noon = new CheckBox(this);
        pill_eaten_evening = new CheckBox(this);
        pill_eaten_night = new CheckBox(this);
        button_pill_save = (Button) findViewById(R.id.button_pill_save);
        button_pill_delete = (Button) findViewById(R.id.button_pill_delete);
        dbHelper = new DbHelpers(this);

        Intent theIntent = getIntent();
        String pi_id = theIntent.getStringExtra(PILL_ID);
        final HashMap<String, String> pillInfo = this.DatabaseGetPillInfo(pi_id);
        if (pillInfo.size() != 0) {
            pill_name.setText(pillInfo.get(PILL_NAME));
            pill_amount.setText(pillInfo.get(PILL_AMOUNT));
            pill_dose.setText(pillInfo.get(PILL_DOSE));
            pill_eaten.setText(pillInfo.get(PILL_EATEN));
            pill_eaten_morning.setText(pillInfo.get(PILL_EATEN_MORNING));
            pill_eaten_noon.setText(pillInfo.get(PILL_EATEN_NOON));
            pill_eaten_evening.setText(pillInfo.get(PILL_EATEN_EVENING));
            pill_eaten_night.setText(pillInfo.get(PILL_EATEN_NIGHT));
            pill_notification1.setText(pillInfo.get(PILL_NOTIFICATION1));
            pill_notification2.setText(pillInfo.get(PILL_NOTIFICATION2));
            pill_notification3.setText(pillInfo.get(PILL_NOTIFICATION3));
            pill_notification4.setText(pillInfo.get(PILL_NOTIFICATION4));
            pill_date_start.setText(pillInfo.get(PILL_DATE_START));
        } else {
            pill_date_start.setText(new StringBuilder()
                            .append(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).append("/")
                            .append(Calendar.getInstance().get(Calendar.MONTH) + 1).append("/")
                            .append(Calendar.getInstance().get(Calendar.YEAR))
            );
        }

        button_pill_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(PILL_NAME, pill_name.getText().toString());
                values.put(PILL_AMOUNT, pill_amount.getText().toString());
                values.put(PILL_DOSE, pill_dose.getText().toString());
                values.put(PILL_EATEN, pill_eaten.getText().toString());
                values.put(PILL_EATEN_MORNING, pill_eaten_morning.getText().toString());
                values.put(PILL_EATEN_NOON, pill_eaten_noon.getText().toString());
                values.put(PILL_EATEN_EVENING, pill_eaten_evening.getText().toString());
                values.put(PILL_EATEN_NIGHT, pill_eaten_night.getText().toString());
                values.put(PILL_NOTIFICATION1, pill_notification1.getText().toString());
                values.put(PILL_NOTIFICATION2, pill_notification2.getText().toString());
                values.put(PILL_NOTIFICATION3, pill_notification3.getText().toString());
                values.put(PILL_NOTIFICATION4, pill_notification4.getText().toString());
                values.put(PILL_DATE_START, pill_date_start.getText().toString());
                if (pillInfo.size() != 0) {
                    db.update(PILL_TABLE, values, PILL_ID + " = ?", new String[]{pillInfo.get(PILL_ID)});
                    Toast.makeText(getApplicationContext(), "อัพเดทข้อมูล เสร็จสิ้น", Toast.LENGTH_LONG).show();
                } else {
                    db.insert(PILL_TABLE, null, values);
                    Toast.makeText(getApplicationContext(), "บันทึกข้อมูล เสร็จสิ้น", Toast.LENGTH_LONG).show();
                }
                db.close();

                PillActivity.this.finish();;

            }
        });

        button_pill_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent theIntent = getIntent();
                String id = theIntent.getStringExtra(PILL_ID);
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                String deleteQuery = "DELETE FROM  "+PILL_TABLE+" WHERE "+PILL_ID+"='"+ id +"'";
                database.execSQL(deleteQuery);
                database.close();
                Toast.makeText(getApplicationContext(), ",ลบข้อมูล เสร็จสิ้น", Toast.LENGTH_LONG).show();
                PillActivity.this.finish();;

            }
        });

        pill_eaten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_eaten_picker();
            }
        });

        pill_notification1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v,  boolean hasFocus) {
                if (hasFocus) { show_time_picker1(); }
            }
        });
        pill_notification2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v,  boolean hasFocus) {
                if (hasFocus) { show_time_picker2(); }
            }
        });
        pill_notification3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v,  boolean hasFocus) {
                if (hasFocus) { show_time_picker3(); }
            }
        });
        pill_notification4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v,  boolean hasFocus) {
                if (hasFocus) { show_time_picker4(); }
            }
        });

        pill_date_start.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) { show_date_picker(); }
            }
        });
    }

    public void show_date_picker() {
        DatePickerDialog.OnDateSetListener DatePickerCallback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                pill_date_start.setText(new StringBuilder()
                        .append(dayOfMonth).append("/")
                        .append(monthOfYear+1).append("/")
                        .append(year)
                );
            }
        };
        new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog,
                DatePickerCallback,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    public void show_time_picker1() {
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


                pill_notification1.setText(hourOfDayS + ":" + minuteS);
            }
        };


        new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog,
                myTimePickerCallback,
                Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
        ).show();
    }
    public void show_time_picker2() {
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


                pill_notification2.setText(hourOfDayS + ":" + minuteS);
            }
        };


        new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog,
                myTimePickerCallback,
                Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
        ).show();
    }
    public void show_time_picker3() {
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


                pill_notification3.setText(hourOfDayS + ":" + minuteS);
            }
        };


        new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog,
                myTimePickerCallback,
                Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
        ).show();
    }
    public void show_time_picker4() {
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


                pill_notification4.setText(hourOfDayS + ":" + minuteS);
            }
        };


        new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog,
                myTimePickerCallback,
                Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
        ).show();
    }
    public void show_eaten_picker() {
        final String[] eaten = getResources().getStringArray(R.array.array_eaten);
        NumberPicker myNumberPicker = new NumberPicker(this);
        myNumberPicker.setMinValue(0);
        myNumberPicker.setMaxValue(eaten.length - 1);
        myNumberPicker.setDisplayedValues(eaten);
        NumberPicker.OnValueChangeListener myValueChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pill_eaten.setText(eaten[newVal]);
            }
        };
        myNumberPicker.setOnValueChangedListener(myValueChangedListener);
        new AlertDialog.Builder(this).setView(myNumberPicker).show();
    }

    public void show_eaten_time_picker() {
        final ArrayList list_eaten_time = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_eaten_time)
                .setMultiChoiceItems(R.array.array_eaten_time, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (pill_eaten_morning.isChecked()) {
                                    pill_eaten_morning.setChecked(true);
                                } else if (pill_eaten_noon.isChecked()) {
                                    pill_eaten_noon.setChecked(true);
                                } else if (pill_eaten_evening.isChecked()) {
                                    pill_eaten_evening.setChecked(true);
                                } else if (pill_eaten_night.isChecked()){
                                    pill_eaten_night.setChecked(true);
                                }
//                                if (isChecked) {
//                                    list_eaten_time.add(which);
//                                } else if (list_eaten_time.contains(which)) {
//                                    list_eaten_time.remove(Integer.valueOf(which));
//                                }
//                                Log.d("cb", String.valueOf(list_eaten_time));
                            }
                        });
//                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                    }
//                })
//                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                    }
//                });
        builder.show();
    }

    public HashMap<String, String> DatabaseGetPillInfo(String id) {
        HashMap<String, String> pillInfo = new HashMap<String, String>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+PILL_TABLE+" where "+PILL_ID+"='"+id+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                pillInfo.put(PILL_ID, cursor.getString(cursor.getColumnIndex(PILL_ID)));
                pillInfo.put(PILL_NAME, cursor.getString(cursor.getColumnIndex(PILL_NAME)));
                pillInfo.put(PILL_AMOUNT, cursor.getString(cursor.getColumnIndex(PILL_AMOUNT)));
                pillInfo.put(PILL_DOSE, cursor.getString(cursor.getColumnIndex(PILL_DOSE)));
                pillInfo.put(PILL_EATEN, cursor.getString(cursor.getColumnIndex(PILL_EATEN)));
                pillInfo.put(PILL_EATEN_MORNING, cursor.getString(cursor.getColumnIndex(PILL_EATEN_MORNING)));
                pillInfo.put(PILL_EATEN_NOON, cursor.getString(cursor.getColumnIndex(PILL_EATEN_NOON)));
                pillInfo.put(PILL_EATEN_EVENING, cursor.getString(cursor.getColumnIndex(PILL_EATEN_EVENING)));
                pillInfo.put(PILL_EATEN_NIGHT, cursor.getString(cursor.getColumnIndex(PILL_EATEN_NIGHT)));
                pillInfo.put(PILL_NOTIFICATION1, cursor.getString(cursor.getColumnIndex(PILL_NOTIFICATION1)));
                pillInfo.put(PILL_NOTIFICATION2, cursor.getString(cursor.getColumnIndex(PILL_NOTIFICATION2)));
                pillInfo.put(PILL_NOTIFICATION3, cursor.getString(cursor.getColumnIndex(PILL_NOTIFICATION3)));
                pillInfo.put(PILL_NOTIFICATION4, cursor.getString(cursor.getColumnIndex(PILL_NOTIFICATION4)));
                pillInfo.put(PILL_DATE_START, cursor.getString(cursor.getColumnIndex(PILL_DATE_START)));
            } while (cursor.moveToNext());
        }
        return pillInfo;
    }

//    public void removeContact(View view) {
//        Intent theIntent = getIntent();
//        String rowid = theIntent.getStringExtra("row");
//        dbHelper.deleteContact(contactId);
//        startActivity(new Intent(getApplication(), MainActivity.class););
//    }
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
