package com.example.windows7.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class breakfast extends Activity {

    TextView br1,br2,br3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        br1=(TextView)findViewById(R.id.br1);
        br2=(TextView)findViewById(R.id.br2);
        br3=(TextView)findViewById(R.id.br3);
        Bundle b = getIntent().getExtras();
        String[] array=b.getString("array").split(",");
        br1.setText(array[0].replaceAll("[^a-zA-Z]","")+"  "+array[1].replaceAll("[^a-zA-Z]",""));
        br2.setText(array[2].replaceAll("[^a-zA-Z]",""));
        br3.setText(array[3].replaceAll("[^a-zA-Z]",""));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_breakfast, menu);
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
