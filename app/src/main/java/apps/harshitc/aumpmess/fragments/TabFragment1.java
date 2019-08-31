package apps.harshitc.aumpmess.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import apps.harshitc.aumpmess.R;
import apps.harshitc.aumpmess.adapters.MyListAdapter2;
import apps.harshitc.aumpmess.models.MyListData2;
import apps.harshitc.aumpmess.utils.Tools2;

public class TabFragment1 extends Fragment {
    private ArrayList<MyListData2> list2;
    public RecyclerView recyclerView;
    public MyListAdapter2 myListAdapter2;
    Tools2 tools2;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.fragment_one, container, false);

         tools2=new Tools2();
        createTestList2();

        buildRecyclerView2();
        vollyGet(getContext());

        return view;
    }

    public void insertItem2(String title, String date, String day, int color)
    {

        list2.add( new MyListData2(title,date,day, color));
        recyclerView.scrollToPosition(list2.size()-1);
        myListAdapter2.notifyDataSetChanged();

    }

    public void createTestList2()
    {
        list2=new ArrayList<>();
           }

    public void buildRecyclerView2()
    { recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //adapter = new MyListAdapter(list);
        myListAdapter2=new MyListAdapter2(list2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myListAdapter2);}







    public void vollyGet(final Context context)
    {

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

                            String title,date,day;
                            JSONObject json = new JSONObject(response); // convert String to JSONObject
                            JSONArray articles = json.getJSONArray("list"); // get articles array
                            articles.length(); // --> 2
                            System.out.println( articles.getJSONObject(0)); // get first article in the array
                            System.out.println( articles.getJSONObject(0).names()) ;// get first article keys [title,url,categories,tags]
                            System.out.println("Length is "+articles.length());
                            for(int i=0;i<articles.length();i++) {
                                title=articles.getJSONObject(i).getString("breakfast");
                                date=articles.getJSONObject(i).getString("date");
                                day=articles.getJSONObject(i).getString("day");

                                insertItem2(title,date,day, tools2.getDayColor(day));
                            }



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