package com.example.medicineshop_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.medicineshop_app.R;
import com.example.medicineshop_app.adapter.SanPhamMoiAdapter;
import com.example.medicineshop_app.adapter.loaiSP_adapter;
import com.example.medicineshop_app.model.LoaiSP;
import com.example.medicineshop_app.model.SanPhamMoi;
import com.example.medicineshop_app.model.SanPhamMoiModel;
import com.example.medicineshop_app.model.User;
import com.example.medicineshop_app.retrofit.ApiBanhang;
import com.example.medicineshop_app.retrofit.RetrofitClient;
import com.example.medicineshop_app.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView ListViewManHinhChinh;
    DrawerLayout drawerLayout;
    loaiSP_adapter LoaiSP_adapter;
    List<LoaiSP> mangloaiSP;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanhang apiBanhang;
    List<SanPhamMoi> mangSpMoi;
    SanPhamMoiAdapter spAdapter;
    NotificationBadge badge;
    FrameLayout frameLayout;
    ImageView imagesearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanhang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        Paper.init(this);
        if(Paper.book().read("user") != null){
            User user = Paper.book().read("user");
            Utils.user_current = user;
        }
        Anhxa();
        ActionBar();

        if(isConnected(this))
        {
            ActionViewFlipper();
            getLoaiSanPham();
            getSpMoi();
            getEventClick();
        }else {
            Toast.makeText(getApplicationContext(),"Không có kết nối internet, vui lòng thử lại!", Toast.LENGTH_LONG).show();
        }
    }

//    Swj keienj click item sanr phaamr
    private void getEventClick() {
        ListViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(trangchu);
                        break;

                    case 1:
                        Intent thuockkd = new Intent(getApplicationContext(),ThuockkdActivity.class);
                        thuockkd.putExtra("loai", 1);
                        startActivity(thuockkd);
                        break;
                    case 2:
                        Intent tpcn = new Intent(getApplicationContext(),ThuockkdActivity.class);
                        tpcn.putExtra("loai", 2);
                        startActivity(tpcn);
                        break;
                    case 3:
                        Intent thietbiyt = new Intent(getApplicationContext(),ThuockkdActivity.class);
                        thietbiyt.putExtra("loai", 3);
                        startActivity(thietbiyt);
                        break;

                    case 5:
                        Intent donhang = new Intent(getApplicationContext(),XemDonActivity.class);
                        startActivity(donhang);
                        break;

                    case 6:
//                        xoóa key user
                        Paper.book().delete("user");
                        Intent dangnhap = new Intent(getApplicationContext(),DangNhapActivity.class);
                        startActivity(dangnhap);
                        finish();
                        break;

                }
            }
        });
    }

    private void getSpMoi() {
        compositeDisposable.add(apiBanhang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if(sanPhamMoiModel.isSucess()){
                                mangSpMoi = sanPhamMoiModel.getResult();
                                spAdapter = new SanPhamMoiAdapter(getApplicationContext(), mangSpMoi);
                                recyclerViewManHinhChinh.setAdapter(spAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Disconnect to server"+throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                ));
    }
    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanhang.getLoaiSP()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                   loaiSpModel -> {
                       if(loaiSpModel.isSucess()){
                           mangloaiSP = loaiSpModel.getResult();
                           mangloaiSP.add(new LoaiSP("Đăng xuất", ""));
                           //        Khoi tao adapter
                           LoaiSP_adapter = new loaiSP_adapter(getApplicationContext(),mangloaiSP);
                           ListViewManHinhChinh.setAdapter(LoaiSP_adapter);
//                           Toast.makeText(getApplicationContext(),loaiSpModel.getResult().get(0).getTen_sp(), Toast.LENGTH_LONG).show();
                       }
                   }
                ));


    }

    private void ActionViewFlipper()
    {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://insieutoc.vn/wp-content/uploads/2021/02/banner-quang-cao-thuoc.jpg");
        mangquangcao.add("https://namduoc.vn/wp-content/uploads/2017/12/mm-Banner-WEB-5.png");
        mangquangcao.add("https://toctoc.vn/wp-content/uploads/2022/08/Mau-Landing-Page-Thuoc-Dieu-Tri-Tieu-Duong.png");
        for(int i=0; i<mangquangcao.size(); i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
    private void ActionBar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }
//   Ánh xạ thực hiện việc khởi tạo và thiết lập các thành phần giao diện
    private void Anhxa()
    {
        imagesearch = findViewById(R.id.imgsearch);
        toolbar = findViewById(R.id.toobarmanhinhchinh);
        viewFlipper= findViewById(R.id.viewlipper);
        recyclerViewManHinhChinh = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewManHinhChinh.setLayoutManager(layoutManager);
//        Gọi setHasFixedSize(true) để tối ưu hóa hiệu suất của RecyclerView
        recyclerViewManHinhChinh.setHasFixedSize(true);
        ListViewManHinhChinh = findViewById(R.id.listviewmanhinhchinh);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerLayout);
        badge = findViewById(R.id.menu_sl);
        frameLayout = findViewById(R.id.frameGioHang);

//        Khoi tao list
        mangloaiSP = new ArrayList<>();
        mangSpMoi = new ArrayList<>();
//        Kiểm tra và cập nhật giỏ hàng
        if(Utils.manggiohang == null)
        {
            Utils.manggiohang = new ArrayList<>();
        }else{
//            lưu tổng sản phẩm trong giỏ hàng
            int totalItem = 0;
            for(int i=0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(giohang);
            }
        });
        imagesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem = 0;
        for(int i=0; i<Utils.manggiohang.size(); i++){
            totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to mobile data
            }
        } else {
            // not connected to the internet
            return false;
        }
    return true;
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}