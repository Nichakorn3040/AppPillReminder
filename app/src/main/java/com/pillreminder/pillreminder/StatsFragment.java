package com.pillreminder.pillreminder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by poliveira on 11/03/2015.
 */
public class StatsFragment extends Fragment {
    public static final String TAG = "stats";

    private EditText text3;
    private EditText text4;
    private EditText text5;
    private EditText text6;
//    private EditText text7;
//    private EditText text8;

    private Button btnSubmit;
    View v =null;
    Database_Profile dataregister;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          v = inflater.inflate(R.layout.profile, container, false);

        try
        {
            setdata();
        }
        catch (Exception ex) {
            Log.e("StatsFragment", "error", ex);
        }
        return  v;
    }

    private void setdata() {
        dataregister=new Database_Profile(getActivity());


        text3 = (EditText) v.findViewById(R.id.text3);
        text4 = (EditText) v.findViewById(R.id.text4);
        text5 = (EditText)v. findViewById(R.id.text5);
        text6 = (EditText) v.findViewById(R.id.text6);
//        text7= (EditText) v.findViewById(R.id.text7);
//        text8= (EditText) v.findViewById(R.id.text8);





        text6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final CharSequence[] items = { "ชาย", "หญิง"   };

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                //	builder.setTitle("Make your selection");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        // will toast your selection
                        text6.setText(""+items[item]);

                        dialog.dismiss();

                    }
                }).show();

            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View viewS = inflater.inflate(R.layout.date_picker, null, false);
//
//        // the time picker on the alert dialog, this is how to get the value
//        final DatePicker myDatePicker = (DatePicker) viewS.findViewById(R.id.myDatePicker);
//
//        // so that the calendar view won't appear
//        myDatePicker.setCalendarViewShown(false);
//
//        // the alert dialog
//        new AlertDialog.Builder(getActivity()).setView(viewS)
//                .setTitle("Set Date")
//                .setPositiveButton("Go", new DialogInterface.OnClickListener() {
//                    @TargetApi(11)
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    /*
//                     * In the docs of the calendar class, January = 0, so we
//                     * have to add 1 for getting correct month.
//                     * http://goo.gl/9ywsj
//                     */
//                        int month = myDatePicker.getMonth() + 1;
//                        int day = myDatePicker.getDayOfMonth();
//                        int year = myDatePicker.getYear();
//
//
//                        text5.setText(day + "/" + month + "/" + year);
//
//
//                        dialog.cancel();
//
//                    }
//
//                }).show();

                DatePickerDialog.OnDateSetListener DatePickerCallback = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        text5.setText(new StringBuilder()
                                .append(dayOfMonth).append("/")
                                .append(monthOfYear+1).append("/")
                                .append(year)
                        );
                    }
                };
                new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog,
                        DatePickerCallback,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                ).show();


            }
        });


        final ArrayList<HashMap<String, String>> SelectAllData=dataregister.SelectAllData();
        String[] SelectDataCode=null ;
        if(SelectAllData.size()>0){
            SelectDataCode=dataregister.SelectDataCode( "0");

            text3.setText(SelectDataCode[1]);
            text4.setText(SelectDataCode[2]);
            text5.setText(SelectDataCode[3]);
            text6.setText(SelectDataCode[4]);
//            text7.setText(SelectDataCode[5]);
//            text8.setText(SelectDataCode[6]);
        }else{

        }


        btnSubmit= (Button) v.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (text3.getText().toString().equals(null)&&text3.getText().toString()==null&&text3.getText().toString().equals("")
                        &&text4.getText().toString().equals(null)&&text4.getText().toString()==null&&text4.getText().toString().equals("")
                        &&text5.getText().toString().equals(null)&&text5.getText().toString()==null&&text5.getText().toString().equals("")
//                        &&text8.getText().toString().equals(null)&&text8.getText().toString()==null&&text8.getText().toString().equals("")
//                        &&text7.getText().toString().equals(null)&&text7.getText().toString()==null&&text7.getText().toString().equals("")
                        ){

                    com.pillreminder.pillreminder.Dialog myAlertDialog = new com.pillreminder.pillreminder.Dialog();
                    myAlertDialog.myDialog(getActivity(),
                            "มีช่องว่าง",
                            "กรุณากรอก ทุกช่องคะ");


                }else{
                    if(SelectAllData.size()>0){
                        dataregister.UpdateData("0",   text3.getText().toString()
                                , text4.getText().toString(), text5.getText().toString(), text6.getText().toString(), "", "", "", "", "");
                        Toast.makeText(getActivity(), "แก้ไขเรียบร้อย", Toast.LENGTH_SHORT).show() ;
                    }else{
                        dataregister.InsertData("0",  text3.getText().toString()
                                , text4.getText().toString(), text5.getText().toString(), text6.getText().toString(), "","", "", "", "");
                        Toast.makeText(getActivity(), "บันทึกเรียบร้อย", Toast.LENGTH_SHORT).show() ;
                    }



                }

            }
        });


        Button  cancel= (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(SelectAllData.size()>0){
                    dataregister.DeleteData("0");

                    Toast.makeText(getActivity(), "ลบเรียบร้อย", Toast.LENGTH_SHORT).show() ;
                }else{

                    Toast.makeText(getActivity(), "ยังไม่มีข้อมูลครับ", Toast.LENGTH_SHORT).show() ;
                }
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }
}
