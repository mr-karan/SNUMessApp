package jihadi.example.windows7.food;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;

import karan.jihadi.windows7.food.Feedback;

public class MainActivity extends Activity {

    private static final String TAG = "SNU-MESS-APP";
    public  Object week[] = new Object[7];
    private ProgressBar mProgress;
    enum days {Mon, Tue, Wed, Thu, Fri, Sat, Sun}


    Button dh1, dh2;//This dh1 and dh2 acts as a refresh button.
    Button about;
    Button feedback;
    int flg=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            File httpCacheDir = new File(getCacheDir(), "http");
            long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
            HttpResponseCache.install(httpCacheDir, httpCacheSize);
        }
        catch (IOException e) {
            Log.i(TAG, "HTTP response cache installation failed:" + e);
        }


        dh1 = (Button) findViewById(R.id.dh1);
        dh2 = (Button) findViewById(R.id.dh2);
        about=(Button)findViewById(R.id.about);
        feedback=(Button)findViewById(R.id.feedback);
        dh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flg=0;
                if (isConnected()) {

                    new HttpAsyncTask().execute("https://cdn.rawgit.com/mr-karan/SNUMessApp/master/MessJSON/DH1.json");



                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoInternet),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        dh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flg=1;
                if (isConnected()) {

                    new HttpAsyncTask().execute("https://cdn.rawgit.com/mr-karan/SNUMessApp/master/MessJSON/DH2.json");

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoInternet),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(MainActivity.this,info.class);
                startActivity(in);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(MainActivity.this,Feedback.class);
                startActivity(in);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        HttpResponseCache cache = HttpResponseCache.getInstalled();
        if (cache != null) {
            cache.flush();
        }
    }

    public static String getFromURL(String sUrl) {
        URL url = null;
        String result = "";

        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = convertInputStreamToString(in);
            }
            finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;
        protected void onPreExecute(){
            // create dialog here
            dialog= new ProgressDialog (MainActivity.this);
            dialog.setMessage("Receiving");
            dialog.show();
        }
        @Override
        protected String doInBackground(String... urls) {

            return getFromURL(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            dialog.dismiss();
            try {
                JSONObject json = new JSONObject(result);
                int ct = 0;
                // System.out.println((json.getJSONArray("Mon")).toString());
                for (days day : days.values()) {
                    JSONObject j = json.getJSONObject(String.valueOf(day));
                    Iterator x = j.keys();

                    JSONArray jsonArray = new JSONArray();

                    while (x.hasNext()) {
                        String key = (String) x.next();
                        jsonArray.put(j.get(key));
                    }
                    week[ct++] = jsonArray;
                }

                Intent intent = new Intent();
                if(flg==0) {
                    intent = new Intent(MainActivity.this, DiningHallOne.class);
                }
                else if(flg==1) {
                    intent = new Intent(MainActivity.this, DiningHallTwo.class);
                }


                Calendar rightNow = Calendar.getInstance();
                if (rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                    intent.putExtra("array", week[4].toString());
                    startActivity(intent);
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    intent.putExtra("array", week[5].toString());
                    startActivity(intent);
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                    intent.putExtra("array", week[6].toString());
                    startActivity(intent);
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

                    intent.putExtra("array", week[0].toString());
                    startActivity(intent);
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
                    intent.putExtra("array", week[1].toString());
                    startActivity(intent);

                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
                    intent.putExtra("array", week[2].toString());
                    startActivity(intent);
                }
                else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
                {
                    intent.putExtra("array", week[3].toString());
                    startActivity(intent);
                }


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}