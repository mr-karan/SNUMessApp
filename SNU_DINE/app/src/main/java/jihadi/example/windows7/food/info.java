package jihadi.example.windows7.food;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class info extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView t1 = (TextView) findViewById(R.id.link1);
        //String linkText = "Visit the <a href='http://stackoverflow.com'>StackOverflow</a> web page.";
        String linkText = "Blogs <a href='http://vutsuak95.blogspot.in/'>here</a>";
        t1.setText(Html.fromHtml(linkText));
        t1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView t2 = (TextView) findViewById(R.id.link2);
        String linkText2 = "<a href='http://mr-karan.github.io'>Website</a>";
        t2.setText(Html.fromHtml(linkText2));
        t2.setMovementMethod(LinkMovementMethod.getInstance());
        TextView t3 = (TextView) findViewById(R.id.t3);
        String linkText3 = "<a href='http://www.facebook.com/profile.php?id=100002235304169'>Facebook</a>";
        t3.setText(Html.fromHtml(linkText3));
        t3.setMovementMethod(LinkMovementMethod.getInstance());
        TextView t4 = (TextView) findViewById(R.id.t4);
        String linkText4 = "<a href='http://sheikharaf.me'>Website</a>";
        t4.setText(Html.fromHtml(linkText4));
        t4.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
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
