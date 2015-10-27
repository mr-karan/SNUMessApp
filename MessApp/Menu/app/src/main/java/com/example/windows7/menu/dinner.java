package com.example.windows7.menu;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Calendar;

public class dinner extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
        Calendar rightNow = Calendar.getInstance();
        if (rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
            //do some stuff here
        }
        else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            //do some stuff here
        }
        else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            //do some stuff here
        }
        else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            //do some stuff here
        }
        else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
            //do some stuff here
        }
        else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
            //do some stuff here
        }
        else if(rightNow.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
        {
            //do some stuff here
        }
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
