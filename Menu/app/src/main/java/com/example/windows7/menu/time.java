package com.example.windows7.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Calendar;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class time extends Activity{

    Object week[] =new Object[7];
    Button b1,b2,b3,refresh1,refresh2;
    enum days{Mon,Tue,Wed,Thu,Fri,Sat,Sun}
    ArrayList al=new ArrayList();
    Bundle bundle = new Bundle();
    File cache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_design);

        b1=(Button)findViewById(R.id.breakfast);
        b2=(Button)findViewById(R.id.lunch);
        b3=(Button)findViewById(R.id.dinner);
        refresh1=(Button)findViewById(R.id.ref1);
        refresh2=(Button)findViewById(R.id.ref2);
        refresh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()){

                    new HttpAsyncTask().execute("https://raw.githubusercontent.com/mr-karan/SNUMessApp/master/MessJSON/DH1.json");

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You are not connected to internet .Please try again !!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        refresh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()){

                    new HttpAsyncTask().execute("https://raw.githubusercontent.com/mr-karan/SNUMessApp/master/MessJSON/DH2.json");

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You are not connected to internet .Please try again !!",
                            Toast.LENGTH_LONG).show();
                }
            }

        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(time.this, breakfast.class);
                Calendar rightNow = Calendar.getInstance();
                if (rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                    myIntent.putExtra("array",  week[4].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    myIntent.putExtra("array",  week[5].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                    myIntent.putExtra("array",  week[6].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

                    myIntent.putExtra("array",week[0].toString());
                    startActivity(myIntent);

                    //do some stuff here
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
                    //do some stuff here
                    myIntent.putExtra("array", week[1].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
                    myIntent.putExtra("array", week[2].toString());
                    startActivity(myIntent);

                    //do some stuff here
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
                {
                    myIntent.putExtra("array", week[3].toString());
                    //do some stuff here
                    startActivity(myIntent);
                }


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(time.this, lunch.class);
                Calendar rightNow = Calendar.getInstance();
                if (rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                    myIntent.putExtra("array",  week[4].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    myIntent.putExtra("array",  week[5].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                    myIntent.putExtra("array",  week[6].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

                    myIntent.putExtra("array",week[0].toString());
                    startActivity(myIntent);

                    //do some stuff here
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
                    //do some stuff here
                    myIntent.putExtra("array", week[1].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
                    myIntent.putExtra("array", week[2].toString());
                    startActivity(myIntent);

                    //do some stuff here
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
                {
                    myIntent.putExtra("array", week[3].toString());
                    startActivity(myIntent);
                    //do some stuff here
                }



            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(time.this, dinner.class);
                Calendar rightNow = Calendar.getInstance();

                if (rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                    myIntent.putExtra("array",  week[4].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    myIntent.putExtra("array",  week[5].toString());

                    startActivity(myIntent);
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                    myIntent.putExtra("array",  week[6].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

                    myIntent.putExtra("array",week[0].toString());
                    startActivity(myIntent);

                    //do some stuff here
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
                    //do some stuff here
                    myIntent.putExtra("array", week[1].toString());
                    startActivity(myIntent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
                    myIntent.putExtra("array", week[2].toString());
                    startActivity(myIntent);

                    //do some stuff here
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
                {
                    myIntent.putExtra("array", week[3].toString());
                    startActivity(myIntent);
                    //do some stuff here
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            try {
                JSONObject json = new JSONObject(result);
                int ct=0;
               // System.out.println((json.getJSONArray("Mon")).toString());
                for(days day:days.values()){
                    JSONObject j=json.getJSONObject(String.valueOf(day));
                    Iterator x = j.keys();
                    System.out.println(j);
                    JSONArray jsonArray = new JSONArray();

                    while (x.hasNext()){
                        String key = (String) x.next();
                        jsonArray.put(j.get(key));
                    }
                    week[ct++]=jsonArray;
                    System.out.print(jsonArray);

                }



            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
