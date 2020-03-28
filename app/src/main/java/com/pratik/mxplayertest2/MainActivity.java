package com.pratik.mxplayertest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MostFreeTime(new String[] {"12:15PM-02:00PM","09:00AM-10:00AM","10:30AM-12:00PM"});
    }

    public void  MostFreeTime(String[] array) {
        ArrayList<Date> startTimeArrayList = new ArrayList<>();
        ArrayList<Date> endTimeArrayList = new ArrayList<>();

        for (int i =0; i<array.length;i++){
            String dateString = array[i];
            String timeString[] = dateString.split("-");
            String startTime = timeString[0];
            String endTime = timeString[1];

            String timeSt;
            if( startTime.substring(startTime.length()-2) == "AM"){
                timeSt = startTime.substring(0,5);
            }else{
                timeSt = (Integer.parseInt(startTime.substring(0, 2)) < 12 ? Integer.parseInt(startTime.substring(0, 2)) + 12 + ":" + startTime.substring(3, 2) : startTime.substring(0, 5));
            }
            String timeEnd;
            if( endTime.substring(endTime.length()-2) == "AM"){
                timeEnd = endTime.substring(0,5);
            }else{
                timeEnd = (Integer.parseInt(endTime.substring(0, 2)) < 12 ? Integer.parseInt(endTime.substring(0, 2)) + 12 + ":" + endTime.substring(3) : endTime.substring(0, 5));
            }

            DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
            try {
                Date startTimeInDateObject = dateFormat.parse(timeSt);
                Date endTimeInDateObject = dateFormat.parse(timeEnd);
                startTimeArrayList.add(startTimeInDateObject);
                endTimeArrayList.add(endTimeInDateObject);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            startTimeArrayList.sort(new Comparator<Date>() {
                @Override
                public int compare(Date date, Date t1) {
                    return t1.compareTo(date);
                }
            });
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            endTimeArrayList.sort(new Comparator<Date>() {
                @Override
                public int compare(Date date, Date t1) {
                    return t1.compareTo(date);
                }
            });
        }

        long max = 0;
        for (int i = startTimeArrayList.size() - 1; i > 0; i--) {
           long diff = startTimeArrayList.get(i).getTime() - endTimeArrayList.get(i-1).getTime();
            if (diff > max)
                max = diff;
        }

        Date d = new Date(max);
        DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmXXX");
        Log.d("Pratik", "MostFreeTime 1 : "+dateFormat.format(d));
        Log.d("Pratik", "MostFreeTime 2: "+df.format(d));


    }
}
