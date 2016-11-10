package jihadi.example.windows7.food;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import static android.R.id.message;
import static jihadi.example.windows7.food.R.attr.icon;
public class MainActivity extends Activity {

    private static final String TAG = "SNU-MESS-APP";
    public  Object week[] = new Object[7];
    private ProgressBar mProgress;
    enum days {Mon, Tue, Wed, Thu, Fri, Sat, Sun}


    Button dh1, dh2;//This dh1 and dh2 acts as a refresh button.
    Button about;
    Button feedback;
    Button special;
    Button contact;
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
        special=(Button)findViewById(R.id.special);
        contact=(Button)findViewById(R.id.contact);
        dh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flg=0;
                if (isConnected()) {

                    new fetchMenu().execute("dh1");



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

                    new fetchMenu().execute("dh2");

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

        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(MainActivity.this,SpecialMenu.class);
                startActivity(in);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(MainActivity.this,ContactUs.class);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra("click_action")) {
            try {
                ClickActionHelper.startActivity(intent.getStringExtra("click_action"), intent.getExtras(), this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey("click_action")) {
            try {
                ClickActionHelper.startActivity(data.get("click_action"), null, this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        HttpResponseCache cache = HttpResponseCache.getInstalled();
        if (cache != null) {
            cache.flush();
        }
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class fetchMenu extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;
        ArrayList<String> breakfast = new ArrayList<>();
        ArrayList<String> lunch = new ArrayList<>();
        ArrayList<String> dinner = new ArrayList<>();
        protected void onPreExecute(){
            // create dialog here
            dialog= new ProgressDialog (MainActivity.this);
            dialog.setMessage("Fetching");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
        @Override
        protected Boolean doInBackground(String... mess) {

            try {
                Document page = Jsoup.connect("http://messmenu.snu.in/messMenu.php")
                        .get();

                Element menu;
                if(mess[0].equals("dh1"))   menu = page.getElementsByTag("tbody").get(0);
                else                        menu = page.getElementsByTag("tbody").get(1);

                Elements breakfast_items = menu.getElementsByTag("td").get(1).children();
                Elements lunch_items = menu.getElementsByTag("td").get(2).children();
                Elements dinner_items = menu.getElementsByTag("td").get(3).children();

                for(Element item: breakfast_items)  breakfast.add(item.text());
                for(Element item: lunch_items)      lunch.add(item.text());
                for(Element item: dinner_items)     dinner.add(item.text());

                return true;

            } catch (IOException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
                return false;
            }
        // onPostExecute send result to the respective activity.
        @Override
        protected void onPostExecute(Boolean result) {
            dialog.dismiss();

            if(result) {
                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                i.putExtra("breakfast", breakfast);
                i.putExtra("lunch", lunch);
                i.putExtra("dinner", dinner);
                startActivity(i);
            }
            else {
                Toast.makeText(MainActivity.this, "Can't fetch menu. Try again later.", Toast.LENGTH_SHORT).show();


            }
        }
    }
    //Just to confirm exitting the app.
    @Override
    public void onBackPressed()
    {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        moveTaskToBack(true);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

}


