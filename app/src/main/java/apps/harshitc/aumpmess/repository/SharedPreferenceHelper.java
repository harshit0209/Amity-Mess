package apps.harshitc.aumpmess.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferenceHelper {
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
public SharedPreferenceHelper(Context context, String collectionName)
{
    pref=context.getSharedPreferences(collectionName, 0);
    editor = pref.edit();
}
public void adddata()
{
    editor.putString("key_name", "string value"); // Storing string


    editor.commit(); // commit changes
     }
public String getData()
    {
        Log.d("getdata",pref.getString("key_name","not fount"));
        return  pref.getString("key_name", null); // getting String

    }

    public void addToBreakfast(String date,String food)
    {
        editor.putString(date, food);
        editor.commit(); // commit changes
    }
    public void getBreakfast()
    {

    }









}