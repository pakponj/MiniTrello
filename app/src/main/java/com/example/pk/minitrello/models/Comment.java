package com.example.pk.minitrello.models;

/**
 * Created by Pipatpol on 2559-02-29.
 */
public class Comment {
    private String subject;
    private String body;
    private long createdTime;

    public String getSubject() {
        return this.subject;
    }

    public String getBody() {
        return this.body;
    }

    public Comment( String subject , String body){
        this.subject = subject;
        this.body = body;
        createdTime = System.currentTimeMillis();

    }
}
