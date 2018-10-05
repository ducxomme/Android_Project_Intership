package com.example.huuduc.intership_project.data.listener;

import com.example.huuduc.intership_project.data.model.Comment;

import java.util.List;

public interface CommentListener {

    void success(List<Comment> listComment);
    void failed(String error);
}
