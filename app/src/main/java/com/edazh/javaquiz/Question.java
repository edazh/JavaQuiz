package com.edazh.javaquiz;

import android.support.annotation.StringRes;

/**
 * Created by 12392 on 2017/7/5 0005.
 * 问题
 */

public class Question {
    private int mTextResId;     //问题文本资源ID
    private boolean mAnswerTrue;    //问题的正确答案

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(@StringRes int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
