package com.example.medicineshop_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.example.medicineshop_app.R;
import com.example.medicineshop_app.adapter.DonHangAdapter;
import com.example.medicineshop_app.adapter.SanPhamMoiAdapter;
import com.example.medicineshop_app.model.DonHang;
import com.example.medicineshop_app.model.DonHangModel;
import com.example.medicineshop_app.model.LoaiSP;
import com.example.medicineshop_app.model.SanPhamMoi;
import com.example.medicineshop_app.retrofit.ApiBanhang;
import com.example.medicineshop_app.retrofit.RetrofitClient;
import com.example.medicineshop_app.utils.Utils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.internal.Util;

public class XemDonActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanhang apiBanhang;
    RecyclerView redonhang;
    Toolbar toolbar;

    DonHangAdapter adapter;

    List<DonHang> xemdonhang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_don);
        initView();
        initToolbar();
        getOder();
    }

    private void getOder() {

        compositeDisposable.add(apiBanhang.xemDonHang(Utils.user_current.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        DonHangModel -> {
                            if(DonHangModel.isSucess()){
//                                Toast.makeText(this, DonHangModel.getResult().get(1).toString(), Toast.LENGTH_SHORT).show();
                                xemdonhang = DonHangModel.getResult();
                                adapter = new DonHangAdapter(getApplicationContext(), xemdonhang);
                                 redonhang.setAdapter(adapter);
                            }else{
                            Toast.makeText(getApplicationContext(), DonHangModel.isSucess()+"Không thành công", Toast.LENGTH_SHORT).show();
                          }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Disconnect to server"+throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                ));

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        apiBanhang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        redonhang = findViewById(R.id.recycleView_donhang);
        toolbar = findViewById(R.id.toobar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        redonhang.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}