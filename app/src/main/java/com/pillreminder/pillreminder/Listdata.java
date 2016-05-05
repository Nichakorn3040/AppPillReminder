package com.pillreminder.pillreminder;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.pillreminder.pillreminder.DbConstants.PILL_ID;
import static com.pillreminder.pillreminder.DbConstants.PILL_NAME;

/**
 * Created by poliveira on 11/03/2015.
 */
public class Listdata extends Fragment {
    public static final String TAG = "stats";
    DbHelpers dbHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.startfragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    @Override
    public void onStart(){
        super.onStart();
        // Apply any required UI change now that the Fragment is visible.
        dbHelper = new DbHelpers(getActivity());
        ListView listView = (ListView) getActivity().findViewById(R.id.listView);
        ArrayList<HashMap<String, String>> contactList =  dbHelper.DatabaseGetPillName();
        if(contactList.size()!=0) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    TextView rowid = (TextView) view.findViewById(R.id.rowid);
                    String contactIdValue = rowid.getText().toString();
                    Intent  theIndent = new Intent(getActivity(),Pill_add.class);
                    theIndent.putExtra(PILL_ID, contactIdValue);
                    startActivity(theIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(),
                    contactList,
                    R.layout.textview,
                    new String[] { PILL_ID,PILL_NAME },
                    new int[] {R.id.rowid, R.id.name}
            );
            listView.setAdapter(adapter);
        }




        ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent  intent = new Intent(getActivity(), Pill_add.class);
                startActivity(intent);

            }
        });
    }
}
