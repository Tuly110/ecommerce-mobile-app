package com.example.medicineshop_app.utils;

import com.example.medicineshop_app.model.GioHang;
import com.example.medicineshop_app.model.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    //public static final String BASE_URL ="http://10.50.17.129/DoAn3/";
    public static final String BASE_URL ="http://192.168.1.22/DoAn3/";
    //public static final String BASE_URL ="http://192.168.1.11/DoAn3/";
    public static List<GioHang> manggiohang;
    public static List<GioHang> mangmuahang = new ArrayList<>();
    public static User user_current = new User();
}
