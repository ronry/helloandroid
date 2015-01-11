package com.ronry.helloandroid.criminal;

import java.util.UUID;

public class Criminal {

    private String id;
    private String title;

    public Criminal(){
        super();
        this.id = UUID.randomUUID().toString();
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

}
