package com.ronry.helloandroid.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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
    private final static String   INDEX     = "INDEX";

    private static List<Question> questions = new ArrayList<Question>();

    static {
        questions.add(new Question(R.string.quiz_question, true));
        questions.add(new Question(R.string.quiz_question1, true));
        questions.add(new Question(R.string.quiz_question2, false));
    }

    private TextView              mQuestionView;
    private Button                mTrueButton;
    private Button                mFalseButton;
    private Button                mNextButton;
    private Button                mPreButton;
    private Question              currentQuestion;

    private int                   index     = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        Log.d(tag, "onCreate was called");

        if (savedInstanceState != null) {
            this.index = savedInstanceState.getInt(INDEX, -1);
        }

        mQuestionView = (TextView) this.findViewById(R.id.quiz_question_view);
        nextQuestion();

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
                nextQuestion();
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

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(tag, "onSaveInstanceState was called");
        
        outState.putInt(INDEX, --this.index);
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

    private void nextQuestion() {
        if (++index == questions.size()) {
            Toast.makeText(QuizActivity.this, R.string.quiz_last_question, Toast.LENGTH_SHORT).show();
            index--;
            return;
        }
        showQuestion();
    }

    private void showQuestion() {
        this.currentQuestion = questions.get(index);
        mQuestionView.setText(currentQuestion.getQuestionNo());
    }

    private void judueAnswer(boolean choosedAnswer) {
        if (currentQuestion.isAnswer() == choosedAnswer) {
            Toast.makeText(QuizActivity.this, R.string.quiz_true_answer, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(QuizActivity.this, R.string.quiz_fasle_answer, Toast.LENGTH_SHORT).show();
        }
    }
}
