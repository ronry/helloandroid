package com.ronry.helloandroid.criminal;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class CriminalManager {

    private static CriminalManager instatnce;
    
    private List<Criminal>         criminals;

    private Context context;

    private CriminalManager(Context context){
        this.context = context;
        criminals = new ArrayList<Criminal>();
        for (int i = 0; i < 100; i++) {
            criminals.add(new Criminal("criminal#" + i));
            if (i % 3 == 0) {
                criminals.get(i).setSolved(true);
            }
        }
    }

    public static CriminalManager get(Context context) {
        if (instatnce == null) {
            instatnce = new CriminalManager(context.getApplicationContext());
        }

        return instatnce;
    }

    public List<Criminal> getCriminals() {
        return criminals;
    }

    public Criminal getCriminal(String id) {
        for (Criminal criminal : criminals) {
            if (criminal.getId().equals(id)) {
                return criminal;
            }
        }

        return null;
    }

}
