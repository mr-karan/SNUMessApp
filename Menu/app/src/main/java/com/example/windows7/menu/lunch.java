package com.example.windows7.menu;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;


public class lunch extends Activity {

    TextView l3,l4,l5,l6,l7,l8,l9,l10,l11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        l3=(TextView)findViewById(R.id.l3);
        l4=(TextView)findViewById(R.id.l4);
        l5=(TextView)findViewById(R.id.l5);
        l6=(TextView)findViewById(R.id.l6);
        l7=(TextView)findViewById(R.id.l7);
        l8=(TextView)findViewById(R.id.l8);
        l9=(TextView)findViewById(R.id.l9);
        l10=(TextView)findViewById(R.id.l10);
        l11=(TextView)findViewById(R.id.l11);
        Bundle b = getIntent().getExtras();
        String[] array=b.getString("array").split(",");
        l3.setText(array[12].replaceAll("[^a-zA-Z]",""));
        l4.setText(array[4].replaceAll("[^a-zA-Z]",""));
        l5.setText(array[5].replaceAll("[^a-zA-Z]",""));
        l6.setText(array[6].replaceAll("[^a-zA-Z]",""));
        l7.setText(array[7].replaceAll("[^a-zA-Z]",""));
        l8.setText(array[8].replaceAll("[^a-zA-Z]",""));
        l9.setText(array[9].replaceAll("[^a-zA-Z]",""));
        l10.setText(array[10].replaceAll("[^a-zA-Z]",""));
        l11.setText(array[11].replaceAll("[^a-zA-Z]",""));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lunch, menu);
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
}
