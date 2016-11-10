package jihadi.example.windows7.food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by manjul singh sachan on 10-Nov-16.
 */

public class ClickActionHelper {
    public static void startActivity(String className, Bundle extras, Context context) throws ClassNotFoundException {
        Class cls;
        cls = Class.forName(className);
        Intent i = new Intent(context, cls);
        i.putExtras(extras);
        context.startActivity(i);
    }
}
