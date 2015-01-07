package com.ronry.helloandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ronry.helloandroid.model.Question;

public class QuizActivity extends Activity {
    
    private static List<Question> questions = new ArrayList<Question>();

    static {
        questions.add(new Question(R.string.quiz_question, true));
        questions.add(new Question(R.string.quiz_question1, true));
        questions.add(new Question(R.string.quiz_question2, false));
    }

    private TextView              mQuestionView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;

    private int                   index     = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionView = (TextView) this.findViewById(R.id.quiz_question_view);
        nextQuestion();

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
            Toast.makeText(QuizActivity.this, R.string.quiz_last_question, Toast.LENGTH_LONG).show();
            return;
        }

        Question question = questions.get(index);
        mQuestionView.setText(question.getQuestionNo());
    }

    private void judueAnswer(boolean choosedAnswer) {
        Question question = questions.get(index);
        if (question.isAnswer() == choosedAnswer) {
            Toast.makeText(QuizActivity.this, R.string.quiz_true_answer, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(QuizActivity.this, R.string.quiz_fasle_answer, Toast.LENGTH_SHORT).show();
        }
    }
}
