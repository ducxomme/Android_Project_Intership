package com.example.huuduc.intership_project.data.model;

import android.support.annotation.NonNull;

import java.util.Date;

public class CommentDate implements Comparable<CommentDate>{
    private String commentId;
    private String content;
    private Date date;
    private String username;

    public CommentDate() {
    }

    public CommentDate(String commentId, String content, Date date, String username) {
        this.commentId = commentId;
        this.content = content;
        this.date = date;
        this.username = username;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int compareTo(@NonNull CommentDate commentDate) {
        return getDate().compareTo(commentDate.getDate());
    }
}
