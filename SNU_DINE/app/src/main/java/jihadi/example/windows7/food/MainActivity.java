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
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG = "SNU-MESS-APP";


    Button dh1, dh2;//This dh1 and dh2 acts as a refresh button.
    Button about;
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
}