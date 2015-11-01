package com.example.windows7.menu;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

public class dinner extends Activity {
    TextView d12,d13,d14,d15,d16,d17,d18,d19,d20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
        Bundle b = getIntent().getExtras();

        d12=(TextView)findViewById(R.id.d12);
        d13=(TextView)findViewById(R.id.d13);
        d14=(TextView)findViewById(R.id.d14);
        d15=(TextView)findViewById(R.id.d15);
        d16=(TextView)findViewById(R.id.d16);
        d17=(TextView)findViewById(R.id.d17);
        d18=(TextView)findViewById(R.id.d18);
        d19=(TextView)findViewById(R.id.d19);
        d20=(TextView)findViewById(R.id.d20);
        String[] array=b.getString("array").split(",");
        d12.setText(array[21].replaceAll("[^a-zA-Z]",""));
        d13.setText(array[13].replaceAll("[^a-zA-Z]",""));
        d14.setText(array[14].replaceAll("[^a-zA-Z]",""));
        d15.setText(array[15].replaceAll("[^a-zA-Z]",""));
        d16.setText(array[16].replaceAll("[^a-zA-Z]",""));
        d17.setText(array[17].replaceAll("[^a-zA-Z]",""));
        d18.setText(array[18].replaceAll("[^a-zA-Z]",""));
        d19.setText(array[19].replaceAll("[^a-zA-Z]",""));
        d20.setText(array[20].replaceAll("[^a-zA-Z]",""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dinner, menu);
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
