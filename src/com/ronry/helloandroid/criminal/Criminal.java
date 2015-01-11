package com.ronry.helloandroid.criminal;

import java.util.Date;
import java.util.UUID;

public class Criminal {

    private String id;
    private String title;
    private Date date;
    private boolean solved;

    public Criminal(){
        super();
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
    }

    public Criminal(String title){
        this();
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public Date getDate() {
        return date;
    }

}
