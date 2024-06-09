package com.example.medicineshop_app.model;

import java.util.List;

public class CommentModel {
    boolean sucess;
    String message;
    List<Comment> result;

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Comment> getResult() {
        return result;
    }

    public void setResult(List<Comment> result) {
        this.result = result;
    }
}
