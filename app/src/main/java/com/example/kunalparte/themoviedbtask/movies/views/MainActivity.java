package com.example.kunalparte.themoviedbtask.movies.views;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.kunalparte.themoviedbtask.R;
import com.example.kunalparte.themoviedbtask.movies.adapters.GenericFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPger)
    ViewPager viewPager;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    private GenericFragmentPagerAdapter genericFragmentPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        toolbar.setTitle(getString(R.string.dash_board_title));
        genericFragmentPagerAdapter = new GenericFragmentPagerAdapter(this,getSupportFragmentManager());
        genericFragmentPagerAdapter.addFragment(AllMoviesFragment.newInstance(),"All Movies");
        genericFragmentPagerAdapter.addFragment(BookmarkedMoviesFragment.newInstance(),"BookMarked");
        viewPager.setAdapter(genericFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
