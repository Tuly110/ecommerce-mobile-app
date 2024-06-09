package com.example.medicineshop_app.utils;

import com.example.medicineshop_app.model.GioHang;
import com.example.medicineshop_app.model.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final String BASE_URL ="http://192.168.1.14/DoAn3/";
    public static List<GioHang> manggiohang;
    public static List<GioHang> mangmuahang = new ArrayList<>();
    public static User user_current = new User();
}
