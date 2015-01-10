package com.ronry.helloandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends Activity {

    public static final String RIGHT_ANSWER_KEY = CheatActivity.class.getName() + ".rightAnswer";
    public static final String CHEADED_ANSWER_KEY = CheatActivity.class.getName() + ".cheated";

    public static final String BUNDLE_KEY_CHEATED = "cheated";

    private Button             showAnswerButton;
    private TextView           showAnswerView;
    private Boolean            currentQuestionAnswer;
    private boolean            cheated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_cheat);

        showAnswerView = (TextView) this.findViewById(R.id.cheat_text_veiw_show_answer);

        showAnswerButton = (Button) this.findViewById(R.id.cheat_button_show_answer);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showAnswerView.setText("该题的答案是" + (currentQuestionAnswer ? "对" : "错"));
                cheated = Boolean.TRUE;
                setCheatedResult();
            }

        });

        currentQuestionAnswer = getIntent().getBooleanExtra(RIGHT_ANSWER_KEY, false);
        if (savedInstanceState != null) {
            cheated = savedInstanceState.getBoolean(BUNDLE_KEY_CHEATED, Boolean.FALSE);
        }
        if (cheated) {
            showAnswerView.setText("该题的答案是" + (currentQuestionAnswer ? "对" : "错"));
        }
        setCheatedResult();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(BUNDLE_KEY_CHEATED, cheated);
    }

    private void setCheatedResult() {
        Intent data = new Intent();
        data.putExtra(CHEADED_ANSWER_KEY, cheated);
        CheatActivity.this.setResult(RESULT_OK, data);
    }

}
