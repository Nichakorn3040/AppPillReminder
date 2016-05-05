package com.pillreminder.pillreminder;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by poliveira on 11/03/2015.
 */
public class Listdata_pill extends Fragment {
    ArrayList<HashMap<String, String>> SelectAllData;
    Database_pill data;
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
       data=new  Database_pill(getActivity());
        SelectAllData= data.SelectAllData();
        ArrayList<ItemDetails> image_details = GetSearchResults();

        ListView listView = (ListView) getActivity().findViewById(R.id.listView);
        if(SelectAllData.size()>0){

            listView.setAdapter(new ItemListBaseAdapter(getActivity(), image_details));
        }


        ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent  intent = new Intent(getActivity(), Doctor_content.class);
                startActivity(intent);

            }
        });
    }
    private ArrayList<ItemDetails> GetSearchResults(){


        ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();
            for (int i=0;i<SelectAllData.size();i++){
                ItemDetails item_details = new ItemDetails();
                item_details.setName(SelectAllData.get(i).get("col2"));
                item_details.setItemDescription(SelectAllData.get(i).get("col4"));
                item_details.setPrice(SelectAllData.get(i).get("col1"));
                results.add(item_details);
            }


        return results;
    }
}