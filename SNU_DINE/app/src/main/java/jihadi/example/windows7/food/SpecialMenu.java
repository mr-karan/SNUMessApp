package jihadi.example.windows7.food;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class SpecialMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_menu);

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
}
