package com.ronry.helloandroid.criminal;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.ronry.helloandroid.R;

public class CriminialViewPagerActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int position = this.getIntent().getIntExtra("position", 0);

        final List<Criminal> criminals=CriminalManager.get(this).getCriminals();
        
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(this.getSupportFragmentManager()) {
            
            @Override
            public int getCount() {
                return criminals.size();
            }
            
            @Override
            public Fragment getItem(int position) {
                return CriminalIntentFragment.newInstance(criminals.get(position).getId());
            }
        };

        ViewPager view=new ViewPager(this);
        view.setId(R.id.viewPager);
        view.setAdapter(adapter);
        view.setCurrentItem(position);
        this.setContentView(view);
    }

}
