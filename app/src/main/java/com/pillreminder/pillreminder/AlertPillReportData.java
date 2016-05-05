package com.pillreminder.pillreminder;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.pillreminder.pillreminder.DbConstants.PILL_DATE_START;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN;
import static com.pillreminder.pillreminder.DbConstants.PILL_ID;
import static com.pillreminder.pillreminder.DbConstants.PILL_NAME;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class AlertPillReportData extends Fragment {

    public static final String TAG = "stats";
    DbPillStats dbHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alert_pill_report, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    @Override
    public void onStart(){
        super.onStart();
        // Apply any required UI change now that the Fragment is visible.
        dbHelper = new DbPillStats(getActivity());
        ArrayList<HashMap<String, String>> data =  dbHelper.SelectAllData();

        {

            for (HashMap<String,String> row : data) {

                for (String key : row.keySet()) {
                    Log.i("DB", key + "=" + row.get(key).toString());
                }

            }
        }



        if(data.size()!=0) try {

            GraphView graph = (GraphView) getView().findViewById(R.id.graph);

            ArrayList<DataPoint> data_yes = new ArrayList<>();
            ArrayList<DataPoint> data_no = new ArrayList<>();

            SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
            Date lastdate = new Date();
            int index = 0;
            int x = 0;
            int yes = 0;
            int no = 0;
            for (HashMap<String,String> row : data) {
                String name = row.get(PILL_NAME).toString();
                String status = row.get(PILL_EATEN).toString();
                Date date = new Date();
                try {
                    date = parser.parse(row.get(PILL_DATE_START).toString());
                } catch (Exception exd) { }

                if ("Y".equals(status))
                    yes += 1;
                else
                    no += 1;
                index++;
                if (index == data.size())
                    date = new Date();
                if (!lastdate.equals(date)) {
                    Log.i("DATE:A", lastdate.toString());
                    Log.i("DATE:B", date.toString());
                    data_yes.add(new DataPoint(x, yes));
                    data_no.add(new DataPoint(x, no));
                    lastdate = date;
                    x++;
                    yes = 0;
                    no = 0;
                }
            }

            LineGraphSeries<DataPoint> plot1 = new LineGraphSeries<>(data_yes.toArray(new DataPoint[data_yes.size()]));
            LineGraphSeries<DataPoint> plot2 = new LineGraphSeries<>(data_no.toArray(new DataPoint[data_yes.size()]));

            plot1.setColor(0xff0000ff);
            plot2.setColor(0xffff0000);

            graph.addSeries(plot1);
            graph.addSeries(plot2);


        }
        catch (Exception ex ){
            Log.e("AlertPillReportData", "error", ex);
        }

    }
}
