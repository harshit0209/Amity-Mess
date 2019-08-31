package apps.harshitc.aumpmess.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.provider.Settings;
import android.util.Log;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import apps.harshitc.aumpmess.R;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Tools2 {

    public void setReminder(Context context, String subject, String date) {
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("beginTime", ms(date));
        intent.putExtra("allDay", false);
        intent.putExtra("rrule", "FREQ=DAILY");
        intent.putExtra("endTime", ms(date)+1000*84000);
        intent.putExtra("title", subject);
        context.startActivity(intent);
    }

    public long getCurrentTimeInMs() {
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis();
    }

    public long ms(String date) {


    long miliSecsDate = milliseconds(date);
    Log.d("miliSecsDate"," = "+miliSecsDate);
    return  miliSecsDate;
    }
    public long milliseconds(String date)
    {
        //String date_ = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milli :: " + timeInMilliseconds);
            return timeInMilliseconds;
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }
    public int getCurrentDaysCount()
    {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int numDays = c.getActualMaximum(Calendar.DATE);
        Log.d("Days",""+numDays);
        return numDays;
    }

    public List<String> getChipAdapter(String s)
    {
        s+=',';
        int a=0;
        List<String> st = new ArrayList<>();
        for(int i=0;i<s.length();i++) {
            int j=0;
            if(s.charAt(i)==','){
                j=i+1;
                st.add(s.substring(a,j-1));
                a=j;
            }

        }

        return st;
    }
    public List<String> getChipAdapterDelimeter(String s)
    {

        String ss;
        // create a new scanner
        // with the specified String Object
        Scanner scanner = new Scanner(s);
        scanner.useDelimiter(",");
        // Set the delimiter to "."
        List<String> st = new ArrayList<>();


        while (scanner.hasNext())
            st.add(scanner.next());
       return st;
    }
    public int getDayColor(String day)
    {
        if(day.equals("Monday"))
        {
            return Color.parseColor("#BA68C8");
        }
       else if(day.equals("Tuesday"))
        {
            return Color.parseColor("#4FC3F7");
        }
       else if(day.equals("Wednesday"))
        {
            return Color.parseColor("#4DB6AC");
        }
       else if(day.equals("Thursday"))
        {
            return Color.parseColor("#FFB74D");
        }
       else if(day.equals("Friday"))
        {
            return Color.parseColor("#7986CB");
        }
        else
           return Color.parseColor("#AED581");

    }
}
