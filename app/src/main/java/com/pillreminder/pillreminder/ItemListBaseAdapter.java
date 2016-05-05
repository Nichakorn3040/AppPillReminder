package com.pillreminder.pillreminder;

import java.util.ArrayList;
import java.util.HashMap;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsrrayList;
	ArrayList<HashMap<String, String>> SelectAllData;
	Database_pill data;
	
	private LayoutInflater l_Inflater;
	Context context;
	public ItemListBaseAdapter(Context context, ArrayList<ItemDetails> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
		data=new  Database_pill(context);
		SelectAllData= data.SelectAllData();
		 this.context=context;
	}

	public int getCount() {
		return itemDetailsrrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final  int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.list_row, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.tvCity);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.tvCondition);
			holder.txt_itemPrice = (TextView) convertView.findViewById(R.id.tvTemp);
			holder.setonclick = (RelativeLayout) convertView.findViewById(R.id.thumbnailS);

			holder.setonclick.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {

					Database_File_Confix_Center.code_updete=SelectAllData.get(position).get("Code");
					Intent theIndent = new Intent(context,Add_pill_Edite.class);
					context.startActivity(theIndent);
				}
			});
			convertView.setTag(holder);



		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getItemDescription());
		holder.txt_itemPrice.setText(itemDetailsrrayList.get(position).getPrice());

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDescription;
		TextView txt_itemPrice;
		RelativeLayout setonclick;

	}
}
