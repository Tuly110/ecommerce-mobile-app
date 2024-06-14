package com.example.medicineshop_app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.medicineshop_app.R;
import com.example.medicineshop_app.adapter.ThuockkdAdapter;
import com.example.medicineshop_app.model.SanPhamMoi;
import com.example.medicineshop_app.retrofit.ApiBanhang;
import com.example.medicineshop_app.retrofit.RetrofitClient;
import com.example.medicineshop_app.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThuockkdActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiBanhang apiBanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page =1;
    int loai;
    ThuockkdAdapter adapterThuockkd;
    List<SanPhamMoi> sanPhamMoiList;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuockkd);
        apiBanhang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        loai = getIntent().getIntExtra("loai", 3);

        Anhxa();
        ActionToolBar();
        getData(page);
        addEventLoad();
    }

    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoading==false)
                {
                    if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == sanPhamMoiList.size()-1){
                        isLoading =true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
//                add null
                sanPhamMoiList.add(null);
                adapterThuockkd.notifyItemInserted(sanPhamMoiList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                remove null
                sanPhamMoiList.remove(sanPhamMoiList.size()-1);
                adapterThuockkd.notifyItemRemoved(sanPhamMoiList.size());
                page = page +1;
                getData(page);
                adapterThuockkd.notifyDataSetChanged();
                isLoading= false;
            }
        },2000);
    }

    private void getData(int page) {// gaán string ren quên rồi

        compositeDisposable.add(apiBanhang.getSanPham(page, loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if(sanPhamMoiModel.isSucess()){
                                if(adapterThuockkd == null)
                                {
                                    sanPhamMoiList = sanPhamMoiModel.getResult();
                                    adapterThuockkd = new ThuockkdAdapter(getApplicationContext(), sanPhamMoiList );
                                    recyclerView.setAdapter(adapterThuockkd);
                                }else{
                                    int vitri = sanPhamMoiList.size()-1;
                                    int soluongadd = sanPhamMoiModel.getResult().size();
                                    for(int i=0; i<soluongadd; i++){
                                        sanPhamMoiList.add(sanPhamMoiModel.getResult().get(i));
                                    }
                                    adapterThuockkd.notifyItemRangeInserted(vitri, soluongadd);
                                }

                            }else{
                                Toast.makeText(getApplicationContext(), "Hết dữ liệu rồi", Toast.LENGTH_LONG).show();
                                isLoading = true;
                            }
                        },
                        throwable -> {
                            Log.d("loggg", throwable.getMessage());
                            Toast.makeText(getApplicationContext(), "No connect to server", Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void ActionToolBar() {

        switch (loai){
            case 1:
               toolbar.setTitle("Thuốc Không Kê Đơn");
               break;
            case 2:
                toolbar.setTitle("Thực Phẩm Chức Năng");
                break;
            case 3:
                toolbar.setTitle("Thiết Bị Y Tế");
                break;

        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleView_thuockkd);
       linearLayoutManager = new LinearLayoutManager(this, linearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        sanPhamMoiList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}