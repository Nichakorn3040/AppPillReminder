package com.pillreminder.pillreminder;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Profile extends Activity  {
 
	private  Bitmap  bitmapSave=null; 
	public static final int REQUEST_GALLERY = 1;
	static final int DATE_DIALOG_ID = 999;


	private EditText text1;
	private EditText text2;
	private EditText text3;
	private EditText text4;
	private EditText text5;
	private EditText text7;
	private EditText text8;
	private String nameType;
	private Button btnCancel,btnSubmit;
	private Uri part;
	 String fname="" ;
	 Database_Profile dataregister;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main_profile);
	    dataregister=new Database_Profile(this);

	    text1 = (EditText) findViewById(R.id.text1);
	    text2 = (EditText) findViewById(R.id.text2);
	    text3 = (EditText) findViewById(R.id.text3);
	    text4 = (EditText) findViewById(R.id.text4);
	    text5 = (EditText) findViewById(R.id.text5);
	    text7= (EditText) findViewById(R.id.text7);
	    text8= (EditText) findViewById(R.id.text8);

 
		//��
		EditText spinner1 = (EditText) findViewById(R.id.spinner1);


		spinner1.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				final CharSequence[] items = { "ชาย", "หญิง"   };

				AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
			//	builder.setTitle("Make your selection");
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

						// will toast your selection

						nameType=""+items[item];
						dialog.dismiss();

					}
				}).show();

			}
		});

		btnSubmit= (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if(text1.getText().toString().equals(null)&&text1.getText().toString()==null&&text1.getText().toString().equals("")
						&&text2.getText().toString().equals(null)&&text2.getText().toString()==null&&text2.getText().toString().equals("")
						&&text3.getText().toString().equals(null)&&text3.getText().toString()==null&&text3.getText().toString().equals("")
						&&text4.getText().toString().equals(null)&&text4.getText().toString()==null&&text4.getText().toString().equals("")
						&&text5.getText().toString().equals(null)&&text5.getText().toString()==null&&text5.getText().toString().equals("")
						&&text8.getText().toString().equals(null)&&text8.getText().toString()==null&&text8.getText().toString().equals("")
						&&text7.getText().toString().equals(null)&&text7.getText().toString()==null&&text7.getText().toString().equals("")){
					com.pillreminder.pillreminder.Dialog myAlertDialog = new com.pillreminder.pillreminder.Dialog();
					myAlertDialog.myDialog(Profile.this,
							"มีช่องว่าง",
							"กรุณากรอก ทุกช่องคะ");

				}else{
	        dataregister.InsertData(null, text1.getText().toString(), text2.getText().toString(), text3.getText().toString()
			, text4.getText().toString(), text5.getText().toString(), nameType, text7.getText().toString(), text8.getText().toString(), fname);
	        text1.setText(" ");
			text2.setText(" ");
			text3.setText(" ");
			text4.setText(" ");
			text5.setText(" ");
			text7.setText(" ");
			text8.setText(" ");

		    Intent i = new Intent(Profile.this, Profile.class);
            startActivity(i);
				}
 
			}
		});
		
		btnCancel= (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				text1.setText(" ");
				text2.setText(" ");
				text3.setText(" ");
				text4.setText(" ");
				text5.setText(" ");
				text7.setText(" ");
				text8.setText(" ");

			}
		});
	}
 

 
	@Override
	public void onBackPressed() {
		Profile.this.finish();


    }



	 
}
