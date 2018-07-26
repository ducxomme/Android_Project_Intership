package com.example.huuduc.intership_project.data.model;

public class Comment {
    private String comment_id;
    private String content;
    private String date;
    private String username;

    public Comment() {
    }

    public Comment(String commentId, String content, String date, String username) {
        this.comment_id = commentId;
        this.content = content;
        this.date = date;
        this.username = username;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
