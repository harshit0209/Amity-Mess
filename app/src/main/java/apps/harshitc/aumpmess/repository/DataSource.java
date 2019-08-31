package apps.harshitc.aumpmess.repository;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataSource {
   SharedPreferenceHelper sharedPreferenceHelper;
   public void setcollection(String string,Context context)
   { sharedPreferenceHelper=new SharedPreferenceHelper(context,string);}
    public void vollyGet(final Context context)
    {
        setcollection("breakfast",context);
        //this code is not working for Crypto Genrated Codes
        Uri.Builder builder = new Uri.Builder();
         builder.scheme("https")
           .authority("harisamarpan.in")
           .appendPath("mess")
          .appendPath("test.php");
        RequestQueue queue = Volley.newRequestQueue(context);
         String url =builder.build().toString();
        //String url =new String("https://harisamarpan.in/mess/test.php");
        Log.d("Repository",url);
                    // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);// Display the first 500 characters of the response string.
                        //Toast.makeText(MainActivity.this, "Get response", Toast.LENGTH_SHORT).show();
                        try {
                            //Experiment here hashmaps so one

                            //  System.out.println(response);
                            // JSONObject jsonObject = new JSONObject(response);
                            // JSONArray jsonArray=jsonObject.getJSONArray(response);
                            JSONObject json = new JSONObject(response); // convert String to JSONObject
                            JSONArray articles = json.getJSONArray("list"); // get articles array
                            articles.length(); // --> 2
                           System.out.println( articles.getJSONObject(0)); // get first article in the array
                           System.out.println( articles.getJSONObject(0).names()) ;// get first article keys [title,url,categories,tags]
                          System.out.println("Length is "+articles.length());
                            for(int i=0;i<articles.length();i++)
                                sharedPreferenceHelper.addToBreakfast(""+ articles.getJSONObject(i).getString("date"),""+ articles.getJSONObject(i).getString("breakfast"));




                        }
                        catch (Exception e){}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Repository",""+error);
            }
        });
        queue.add(stringRequest);

    }
}
