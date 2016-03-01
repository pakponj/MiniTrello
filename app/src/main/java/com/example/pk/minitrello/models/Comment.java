package com.example.pk.minitrello.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pipatpol on 2559-02-29.
 */
public class Comment {
    private String subject;
    private String body;
    private long createdTime;

    public Comment( String subject , String body){
        this.subject = subject;
        this.body = body;
        createdTime = System.currentTimeMillis();

    }

    public String getSubject() {
        return this.subject;
    }

    public String getBody() {
        return this.body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (createdTime != comment.createdTime) return false;
        if (!subject.equals(comment.subject)) return false;
        return body.equals(comment.body);

    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + (int) (createdTime ^ (createdTime >>> 32));
        return result;
    }

    public String getReadableCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy HH:mm");
        Date date = new Date(this.createdTime);
        return sdf.format(date);
    }

}
