package jihadi.example.windows7.food.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jihadi.example.windows7.food.R;


public class Breakfast extends android.support.v4.app.Fragment {
    public static TextView title,item1,item2,item3;

    public Breakfast(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_breakfast, container, false);
        Bundle b = getArguments();
        title = (TextView)view.findViewById(R.id.title);
        item1 = (TextView)view.findViewById(R.id.item1);
        item2 = (TextView)view.findViewById(R.id.item2);
        item3 = (TextView)view.findViewById(R.id.item3);
        String [] myString = b.getStringArray("brr");
        item1.setText(myString[0]);
        item2.setText(myString[1]);
        item3.setText(myString[2]   );
        return view;
    }

    public void sentText(){

    }
}
