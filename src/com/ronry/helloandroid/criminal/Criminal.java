package com.ronry.helloandroid.criminal;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Criminal implements Serializable {

    private static final long serialVersionUID = 7586165882440861762L;
    private String id;
    private String title;
    private Date date;
    private boolean solved;
    private String            person;

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

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

}
