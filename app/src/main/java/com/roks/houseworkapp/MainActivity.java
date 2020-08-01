package com.roks.houseworkapp;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.roks.houseworkapp.ui.navigation.HistoryFragment;
import com.roks.houseworkapp.ui.navigation.ScoreFragment;
import com.roks.houseworkapp.ui.navigation.ScoreFragmentMonth;
import com.roks.houseworkapp.ui.navigation.ScoreFragmentTotal;
import com.roks.houseworkapp.ui.navigation.WorkFragment;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    TabLayout tab;
    ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (item) -> {
        switch (item.getItemId()) {
            case R.id.navigation_work:
                frameLayout.setVisibility(View.GONE);
                load_fragment_bottom(new WorkFragment());
                return true;
            case R.id.navigation_score:
                frameLayout.setVisibility(View.VISIBLE);
                load_fragment_bottom(new ScoreFragment());
                return true;
            case R.id.navigation_history:
                frameLayout.setVisibility(View.GONE);
                load_fragment_bottom(new HistoryFragment());
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = (BottomNavigationView) findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        frameLayout = (FrameLayout)findViewById(R.id.layout_frame);
        //load_fragment_bottom(new WorkFragment());

        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Tab_adapter tab_adapter = new Tab_adapter(getSupportFragmentManager(),tab.getTabCount());

        viewPager.setAdapter(tab_adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    Boolean load_fragment_bottom(Fragment fragment) {
        if (fragment != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
            return true;
        }
        return false;
    }
}

class Tab_adapter extends FragmentStatePagerAdapter
{
    int scoreTab;

    public Tab_adapter(@NonNull FragmentManager fm, int scoreTab) {
        super(fm);
        this.scoreTab = scoreTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                ScoreFragment tab1 = new ScoreFragment();
                return tab1;
            case 1:
                ScoreFragmentMonth tab2 = new ScoreFragmentMonth();
                return tab2;
            case 2:
                ScoreFragmentTotal tab3 = new ScoreFragmentTotal();
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return scoreTab;
    }
}