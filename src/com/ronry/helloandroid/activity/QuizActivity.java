package com.ronry.helloandroid.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ronry.helloandroid.model.Question;

public class QuizActivity extends Activity {
    
    private final static String   tag       = "QuizActivity";
    private final static String   BUNDLE_KEY_INDEX          = "index";
    private final static String   BUNDLE_KEY_CHEATED_STATUS = "cheated_status";

    private static List<Question> questions = new ArrayList<Question>();

    static {
        questions.add(new Question(R.string.quiz_question, true));
        questions.add(new Question(R.string.quiz_question1, true));
        questions.add(new Question(R.string.quiz_question2, false));
    }

    private TextView              mQuestionView;
    private TextView              mCheatedView;
    private Button                mTrueButton;
    private Button                mFalseButton;
    private Button                mNextButton;
    private Button                mPreButton;
    private Button                mCheatButton;
    private Question              currentQuestion;

    private int                   index;
    private boolean[]             cheatdStatus              = new boolean[questions.size()]; ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        Log.d(tag, "onCreate was called");

        mQuestionView = (TextView) this.findViewById(R.id.quiz_question_view);
        mCheatedView = (TextView) this.findViewById(R.id.quiz_textview_cheated);

        mPreButton = (Button) this.findViewById(R.id.quiz_answer_previous_button);
        mPreButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                if (--index == -1) {
                    Toast.makeText(QuizActivity.this, R.string.quiz_first_question, Toast.LENGTH_SHORT).show();
                    index++;
                    return;
                }
                showQuestion();
            }
        });

        mNextButton = (Button) this.findViewById(R.id.quiz_answer_next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (++index == questions.size()) {
                    Toast.makeText(QuizActivity.this, R.string.quiz_last_question, Toast.LENGTH_SHORT).show();
                    index--;
                    return;
                }
                showQuestion();
            }

        });

        mTrueButton = (Button) this.findViewById(R.id.quiz_answer_true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                judueAnswer(Boolean.TRUE);
            }
        });

        mFalseButton = (Button) this.findViewById(R.id.quiz_answer_false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View view) {
                judueAnswer(Boolean.FALSE);
            }
        });

        mCheatButton = (Button) this.findViewById(R.id.quiz_cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                intent.putExtra(CheatActivity.RIGHT_ANSWER_KEY, QuizActivity.this.currentQuestion.isAnswer());
                QuizActivity.this.startActivityForResult(intent, 0);

            }
        });

        if (savedInstanceState != null) {
            this.index = savedInstanceState.getInt(BUNDLE_KEY_INDEX, -1);
            this.cheatdStatus = savedInstanceState.getBooleanArray(BUNDLE_KEY_CHEATED_STATUS);
        }
        showQuestion();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(tag, "onActivityResult called");

        if (data.getBooleanExtra(CheatActivity.CHEADED_ANSWER_KEY, Boolean.FALSE)) {
            cheatdStatus[index] = Boolean.TRUE;
        }
        checkCheatStatus();
    }

    private void showQuestion() {
        this.currentQuestion = questions.get(index);
        mQuestionView.setText(currentQuestion.getQuestionNo());
        checkCheatStatus();
    }

    private void checkCheatStatus() {
        if (cheatdStatus[index]) {
            mCheatedView.setText(R.string.quiz_show_cheatd);
        } else {
            mCheatedView.setText("");
        }
    }

    private void judueAnswer(boolean choosedAnswer) {
        if (currentQuestion.isAnswer() == choosedAnswer) {
            Toast.makeText(QuizActivity.this, R.string.quiz_true_answer, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(QuizActivity.this, R.string.quiz_fasle_answer, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(tag, "onSaveInstanceState was called");
        
        outState.putInt(BUNDLE_KEY_INDEX, this.index);
        outState.putBooleanArray(BUNDLE_KEY_CHEATED_STATUS, cheatdStatus);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "onPause was called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "onResume was called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "onStop was called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "onRestart was called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "onStart was called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy was called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
