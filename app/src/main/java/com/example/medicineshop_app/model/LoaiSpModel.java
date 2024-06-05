package com.example.medicineshop_app.model;

import java.util.List;

public class LoaiSpModel {
    boolean sucess;
    String message;
    List<LoaiSP> result;

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

    public List<LoaiSP> getResult() {
        return result;
    }

    public void setResult(List<LoaiSP> result) {
        this.result = result;
    }
}
