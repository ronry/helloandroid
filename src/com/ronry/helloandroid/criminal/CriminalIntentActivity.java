package com.ronry.helloandroid.criminal;

import android.app.Fragment;

import com.ronry.helloandroid.SingleFragmentActivity;

public class CriminalIntentActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CriminalIntentFragment();
    }

}
