package com.ronry.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ronry.helloandroid.quiz.QuizActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);

        ((Button) this.findViewById(R.id.activity_main_button_quiz)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, QuizActivity.class));
            }
        });
    }

}
