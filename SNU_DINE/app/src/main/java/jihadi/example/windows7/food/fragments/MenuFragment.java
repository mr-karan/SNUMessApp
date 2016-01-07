package jihadi.example.windows7.food.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jihadi.example.windows7.food.R;


public class MenuFragment extends android.support.v4.app.Fragment {
    public static TextView title, tvResult;

    public MenuFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        Bundle b = getArguments();
        title = (TextView)view.findViewById(R.id.title);
        tvResult = (TextView)view.findViewById(R.id.tvResult);

        ArrayList<String> items = b.getStringArrayList("items");

        String result = "";

        for(String item: items)   result += (item + "\n\n");

        tvResult.setText(result);
        return view;
    }

    public void sentText(){

    }
}
