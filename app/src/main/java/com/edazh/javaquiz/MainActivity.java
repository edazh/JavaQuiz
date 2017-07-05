package com.edazh.javaquiz;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mBtnTrue;
    private Button mBtnFalse;
    private Button mBtnCheat;
    private Button mBtnNext;
    private TextView mTxtQuestion;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.q1, true),
            new Question(R.string.q2, true),
            new Question(R.string.q3, false),
            new Question(R.string.q4, false),
            new Question(R.string.q5, false),
            new Question(R.string.q6, true),
            new Question(R.string.q7, false),
            new Question(R.string.q8, true),
            new Question(R.string.q9, true),
            new Question(R.string.q10, true),
    };
    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        //设置文本
        mTxtQuestion = (TextView) findViewById(R.id.view_txt_question);
        updateQuestion();

        //TRUE按钮
        mBtnTrue = (Button) findViewById(R.id.btn_true);
        mBtnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        //FALSE按钮
        mBtnFalse = (Button) findViewById(R.id.btn_false);
        mBtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        //NEXT按钮
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        //作弊按钮
        mBtnCheat = (Button) findViewById(R.id.btn_cheat);
        mBtnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
    }

    /**
     * 更新问题文本
     */
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mTxtQuestion.setText(question);
    }

    /**
     * 判断是否正确
     *
     * @param userPressedTrue 用户按下的答案
     */
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId;
        if (mIsCheater) {
            messageResId = R.string.toast_judgment;
        } else if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.toast_correct;
        } else {
            messageResId = R.string.toast_incorrect;
        }
        showToast(messageResId);
    }

    private void showToast(@StringRes int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }
}
