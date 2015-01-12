package com.ronry.helloandroid.criminal;

import android.app.Fragment;

import com.ronry.helloandroid.SingleFragmentActivity;


public class CriminalListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CriminalListFragment();
    }

}
