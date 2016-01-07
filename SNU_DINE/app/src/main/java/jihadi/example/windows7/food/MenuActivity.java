package jihadi.example.windows7.food;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import jihadi.example.windows7.food.fragments.MenuFragment;

import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public ArrayList<String> breakfast = new ArrayList<>();
    public ArrayList<String> lunch = new ArrayList<>();
    public ArrayList<String> dinner = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle b = getIntent().getExtras();

        breakfast = b.getStringArrayList("breakfast");
        lunch     = b.getStringArrayList("lunch");
        dinner    = b.getStringArrayList("dinner");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundleBreakfast = new Bundle();
        Bundle bundleLunch = new Bundle();
        Bundle bundleDinner = new Bundle();

        bundleBreakfast.putStringArrayList("items", breakfast);
        bundleLunch.putStringArrayList("items", lunch);
        bundleDinner.putStringArrayList("items", dinner);

        MenuFragment mfBreakfast = new MenuFragment();
        MenuFragment mfLunch = new MenuFragment();
        MenuFragment mfDinner = new MenuFragment();

        adapter.addFragment(mfBreakfast, "Breakfast");
        mfBreakfast.setArguments(bundleBreakfast);

        adapter.addFragment(mfLunch, "Lunch");
        mfLunch.setArguments(bundleLunch);

        adapter.addFragment(mfDinner, "Dinner");
        mfDinner.setArguments(bundleDinner);

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
