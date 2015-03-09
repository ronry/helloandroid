package com.ronry.helloandroid.criminal;

import android.app.Fragment;

import com.ronry.helloandroid.SingleFragmentActivity;


public class SingleCriminalViewActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        int position = this.getIntent().getIntExtra("position", 0);
        return SingleCriminalViewFragment.newInstance(CriminalManager.get(this).getCriminals().get(position));
    }

}
