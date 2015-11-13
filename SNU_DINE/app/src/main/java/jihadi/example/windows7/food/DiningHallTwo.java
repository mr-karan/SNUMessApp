package jihadi.example.windows7.food;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import jihadi.example.windows7.food.fragments.Breakfast;
import jihadi.example.windows7.food.fragments.Dinner;
import jihadi.example.windows7.food.fragments.Lunch;

import java.util.ArrayList;
import java.util.List;


public class DiningHallTwo extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public String [] brr, lrr, drr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_hall_two);
        Bundle b = getIntent().getExtras();
        String[] array=b.getString("array").split(",");
        brr=new String[3];
        lrr=new String[9];
        drr=new String[9];
        brr[0]=array[0].replaceAll("[^a-zA-Z]","")+"  "+array[1].replaceAll("[^a-zA-Z]"," ");
        brr[1]=array[2].replaceAll("[^a-zA-Z]"," ");
        brr[2]=array[3].replaceAll("[^a-zA-Z]"," ");
        lrr[0]=array[4].replaceAll("[^a-zA-Z]"," ");
        lrr[1]=array[5].replaceAll("[^a-zA-Z]"," ");
        lrr[2]=array[6].replaceAll("[^a-zA-Z]"," ");
        lrr[3]=array[7].replaceAll("[^a-zA-Z]"," ");
        lrr[4]=array[8].replaceAll("[^a-zA-Z]"," ");
        lrr[5]=array[9].replaceAll("[^a-zA-Z]"," ");
        lrr[6]=array[10].replaceAll("[^a-zA-Z]"," ");
        lrr[7]=array[11].replaceAll("[^a-zA-Z]"," ");
        lrr[8]=array[12].replaceAll("[^a-zA-Z]"," ");
        drr[0]=array[21].replaceAll("[^a-zA-Z]"," ");
        drr[1]=array[13].replaceAll("[^a-zA-Z]"," ");
        drr[2]=array[14].replaceAll("[^a-zA-Z]"," ");
        drr[3]=array[15].replaceAll("[^a-zA-Z]"," ");
        drr[4]=array[16].replaceAll("[^a-zA-Z]"," ");
        drr[5]=array[17].replaceAll("[^a-zA-Z]"," ");
        drr[6]=array[18].replaceAll("[^a-zA-Z]"," ");
        drr[7]=array[19].replaceAll("[^a-zA-Z]"," ");
        drr[8]=array[20].replaceAll("[^a-zA-Z]"," ");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putStringArray("brr",brr);
        bundle.putStringArray("lrr",lrr);
        bundle.putStringArray("drr",drr);
        Breakfast bf = new Breakfast();
        Lunch lu=new Lunch();
        Dinner di=new Dinner();
        adapter.addFragment(bf, "Breakfast");
        bf.setArguments(bundle);
        adapter.addFragment(lu, "Lunch");
        lu.setArguments(bundle);
        adapter.addFragment(di, "Dinner");
        di.setArguments(bundle);
        viewPager.setAdapter(adapter);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



}
