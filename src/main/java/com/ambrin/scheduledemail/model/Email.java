package com.ambrin.scheduledemail.model;

public class Email {
    private String to;
    private String body;
    private String subject;

    public Email to(String to) {
        this.to = to;
        return this;
    }

    public Email body(String body) {
        this.body=body;
        return this;
    }

    public Email subject(String subject) {
        this.subject=subject;
        return this;
    }

    public String getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }
}
