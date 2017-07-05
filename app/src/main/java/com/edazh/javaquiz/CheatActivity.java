package com.edazh.javaquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.edazh.javaquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.edazh.javaquiz.answer_shown";

    private boolean mAnswerIsTrue;

    private TextView mTxtAnswer;
    private Button mBtnShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mTxtAnswer = (TextView) findViewById(R.id.txt_answer);
        mBtnShowAnswer = (Button) findViewById(R.id.btn_show_answer);
        mBtnShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mTxtAnswer.setText(R.string.btn_true);
                } else {
                    mTxtAnswer.setText(R.string.btn_false);
                }
                setAnswerShownResult(true);
            }
        });
    }

    /**
     * 设置返回是否查看过答案
     *
     * @param isAnswerShown 是否已查看过答案
     */
    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, intent);
    }

    /**
     * 获取Intent
     *
     * @param packageContext
     * @param answerIsTrue
     * @return
     */
    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    /**
     * 解析是否查看过答案
     * @param intent 返回的结果
     * @return 是否查看过答案
     */
    public static boolean wasAnswerShown(Intent intent) {
        return intent.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
