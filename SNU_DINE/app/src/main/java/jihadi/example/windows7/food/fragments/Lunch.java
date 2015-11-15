package jihadi.example.windows7.food.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jihadi.example.windows7.food.R;

public class Lunch extends android.support.v4.app.Fragment {
    public static TextView title,item1,item2,item3,item4,item5,item6,item7,item8,item9;
    public Lunch() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lunch, container, false);
        Bundle b = getArguments();
        title = (TextView)view.findViewById(R.id.title);
        item1 = (TextView)view.findViewById(R.id.item1);
        item2 = (TextView)view.findViewById(R.id.item2);
        item3 = (TextView)view.findViewById(R.id.item3);
        item4 = (TextView)view.findViewById(R.id.item4);
        item5 = (TextView)view.findViewById(R.id.item5);
        item6 = (TextView)view.findViewById(R.id.item6);
        item7 = (TextView)view.findViewById(R.id.item7);
        item8 = (TextView)view.findViewById(R.id.item8);
        item9 = (TextView)view.findViewById(R.id.item9);
        String [] myString = b.getStringArray("lrr");

        if(myString.length==9){
            item1.setText(myString[0]);
            item2.setText(myString[1]);
            item3.setText(myString[2]);
            item4.setText(myString[3]);
            item5.setText(myString[4]);
            item6.setText(myString[5]);
            item7.setText(myString[6]);
            item8.setText(myString[7]);
            item9.setText(myString[8]);
        }
        else{
            item1.setText(myString[0]);
            item2.setText(myString[1]);
            item3.setText(myString[2]);
            item4.setText(myString[3]);
            item5.setText(myString[4]);
            item6.setText(myString[5]);
            item7.setText(myString[6]);
            item8.setText(myString[7]);
            item9.setText("");
        }
        return view;
    }
}
