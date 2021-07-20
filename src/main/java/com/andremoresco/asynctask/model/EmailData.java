package com.andremoresco.asynctask.model;

import java.util.Date;

public class EmailData {

    private String from;
    private String to;
    private Date received;
    private Date sent;
    private Date created;
    private String labels;
    private String subject;
    private String content;

    public EmailData() {
    }

    public EmailData(String from, String to, Date received, Date sent, Date created, String labels, String subject, String content) {
        this.from = from;
        this.to = to;
        this.received = received;
        this.sent = sent;
        this.created = created;
        this.labels = labels;
        this.subject = subject;
        this.content = content;
    }

    public String getIdentifier() {
        return this.getSubject();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "EmailData{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", received=" + received +
                ", sent=" + sent +
                ", created=" + created +
                ", labels='" + labels + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
