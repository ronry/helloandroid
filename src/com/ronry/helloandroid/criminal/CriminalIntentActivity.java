package com.ronry.helloandroid.criminal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.ronry.helloandroid.R;

public class CriminalIntentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_criminal_intent);

        FragmentManager fragmentManager = this.getFragmentManager();

        Fragment criminalIntentFragment = fragmentManager.findFragmentById(R.id.criminal_intent_container);
        if (criminalIntentFragment == null) {
            criminalIntentFragment = new CriminalIntentFragment();
            fragmentManager.beginTransaction().add(R.id.criminal_intent_container, criminalIntentFragment).commit();
        }
    }

}
