package com.example.medicineshop_app.model;

import java.util.List;

public class SanPhamMoiModel {
    boolean sucess;
    String message;
    List<SanPhamMoi> result;

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

    public List<SanPhamMoi> getResult() {
        return result;
    }

    public void setResult(List<SanPhamMoi> result) {
        this.result = result;
    }
}
