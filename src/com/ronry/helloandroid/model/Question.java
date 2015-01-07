package com.ronry.helloandroid.model;

public class Question {

    private int     questionNo;

    private boolean answer;

    public Question(int questionNo, boolean answer){
        super();
        this.questionNo = questionNo;
        this.answer = answer;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

}
