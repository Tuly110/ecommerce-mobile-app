package com.example.medicineshop_app.Activity;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicineshop_app.R;
import com.example.medicineshop_app.adapter.CommentAdapter;
import com.example.medicineshop_app.adapter.SanPhamMoiAdapter;
import com.example.medicineshop_app.model.Comment;
import com.example.medicineshop_app.model.CommentModel;
import com.example.medicineshop_app.model.GioHang;
import com.example.medicineshop_app.model.LoaiSpModel;
import com.example.medicineshop_app.model.SanPhamMoi;
import com.example.medicineshop_app.model.User;
import com.example.medicineshop_app.retrofit.ApiBanhang;
import com.example.medicineshop_app.retrofit.RetrofitClient;
import com.example.medicineshop_app.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsActivity extends AppCompatActivity {
    TextView tensp, giasp, mota;
    Button btnthem, btnComment, btnCommentNo, btnCommentYes;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    SanPhamMoi sanPhamMoi;
    NotificationBadge badge;
    EditText edit_text;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanhang apiBanhang;
    List<Comment> comment;

    RecyclerView recyclerComment ;

    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        apiBanhang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        Paper.init(this);
        if(Paper.book().read("user") != null){
            User user = Paper.book().read("user");
            Utils.user_current = user;
        }

        initView();
        ActionToolBar();
        initData();
        initControl();
        pushDialogComments();
    }

    private void initControl() {
        btnthem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               themGioHang();
            }
        });
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openCommentDialog(Gravity.CENTER);
            }
        });
    }

    private void themGioHang() {
        if(Utils.manggiohang.size() > 0){
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for(int i=0; i<Utils.manggiohang.size(); i++){
//                trung san pham
                if(Utils.manggiohang.get(i).getIdsp() == sanPhamMoi.getId()){
//                    set lai so luong
                    Utils.manggiohang.get(i).setSoluong(soluong + Utils.manggiohang.get(i).getSoluong());
                    Utils.manggiohang.get(i).setGiasp(Long.parseLong(sanPhamMoi.getGiasp()));

                    flag = true;
                }
            }
//            Khong trung san pham
            if(flag == false){
//                long gia = Long.parseLong(sanPhamMoi.getGiasp()) * soluong;
                GioHang gioHang = new GioHang();
                gioHang.setGiasp(Long.parseLong(sanPhamMoi.getGiasp()));
                gioHang.setSoluong(soluong);
                gioHang.setIdsp(sanPhamMoi.getId());
                gioHang.setTensp(sanPhamMoi.getTensp());
                gioHang.setHinhsp(sanPhamMoi.getHinhanh());
                Utils.manggiohang.add(gioHang);
            }
        }else{

            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
//            long gia = Long.parseLong(sanPhamMoi.getGiasp()) * soluong;
            GioHang gioHang = new GioHang();
            gioHang.setGiasp( Long.parseLong(sanPhamMoi.getGiasp()));
            gioHang.setSoluong(soluong);
            gioHang.setIdsp(sanPhamMoi.getId());
            gioHang.setTensp(sanPhamMoi.getTensp());
            gioHang.setHinhsp(sanPhamMoi.getHinhanh());
            Utils.manggiohang.add(gioHang);
        }
        int totalItem = 0;
        for(int i=0; i<Utils.manggiohang.size(); i++){

            totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private void initData() {
        sanPhamMoi = sanPhamMoi = (SanPhamMoi) getIntent().getSerializableExtra("chitiet");
        tensp.setText(sanPhamMoi.getTensp());
        mota.setText(sanPhamMoi.getMota());
        Glide.with(getApplicationContext()).load(sanPhamMoi.getHinhanh()).into(imghinhanh);
        giasp.setText("Giá: "+sanPhamMoi.getGiasp()+" VND");

        Integer[] so = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(adapterspin);
    }
    private void openCommentDialog(int gravity)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_comment);
        Window window = dialog.getWindow();
        if(window == null)
        {
            return;// ủa đâu rồi ới viết đymà
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = gravity;
        window.setAttributes(windowAttribute);
        dialog.setCancelable(Gravity.BOTTOM == gravity);
        btnCommentNo = dialog.findViewById(R.id.btn_nothanks);
        btnCommentYes = dialog.findViewById(R.id.btn_commentyes);

        btnCommentYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit_text = dialog.findViewById(R.id.edit_text);
                insertComment(edit_text.getText().toString());
                dialog.dismiss();
            }
        });
        btnCommentNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void pushDialogComments() {
        sanPhamMoi = sanPhamMoi = (SanPhamMoi) getIntent().getSerializableExtra("chitiet");
        compositeDisposable.add(apiBanhang.comment(sanPhamMoi.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        commentModel -> {
                            if(commentModel.isSucess()){
                                comment = commentModel.getResult();
                                adapter = new CommentAdapter(getApplicationContext(), comment);
                                recyclerComment.setAdapter(adapter);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(
                                    this,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                );
                                recyclerComment.setLayoutManager(layoutManager);
                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(getApplicationContext(), "Hãy là người đầu tiên bình luận nào", Toast.LENGTH_LONG).show();
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "list comments Failed", Toast.LENGTH_LONG).show();
                        }
                ));
    }
    private void insertComment(String text) {
        sanPhamMoi = sanPhamMoi = (SanPhamMoi) getIntent().getSerializableExtra("chitiet");
        List<Comment> ds = new ArrayList<>();
        compositeDisposable.add(apiBanhang.postComment(Utils.user_current.getId(), sanPhamMoi.getId(),text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        commentModel -> {
                            if (commentModel.isSucess()) {
                                Toast.makeText(getApplicationContext(), commentModel.getMessage(), Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "Bình luận thành công", Toast.LENGTH_LONG).show();
                                pushDialogComments();
                            }
                        },
                        throwable -> {
                            Log.e("loi", "Loi binh luan " + throwable.getMessage());
                            Toast.makeText(getApplicationContext(), "Loi binh luan", Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void initView() {

       recyclerComment = findViewById(R.id.recyclerComment);

        tensp = findViewById(R.id.txttensp );
        giasp = findViewById(R.id.txtgiasp);
        mota = findViewById(R.id.txtmotachitiet);
        btnthem = findViewById(R.id.btnthemvaogiohang);
        spinner = findViewById(R.id.spinner);
        imghinhanh = findViewById(R.id.imgdetails);
        toolbar = findViewById(R.id.toobar);
        badge = findViewById(R.id.menu_sl);
        btnComment = findViewById(R.id.btnComment);
        edit_text = findViewById(R.id.edit_text);
        FrameLayout frameLayoutgiohang = findViewById(R.id.frameGioHang);
        frameLayoutgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(giohang);
            }
        });
        if(Utils.manggiohang != null){
            int totalItem = 0;
            for(int i=0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }

            badge.setText(String.valueOf(totalItem));
        }
    }
    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.manggiohang != null){
            int totalItem = 0;
            for(int i=0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }

            badge.setText(String.valueOf(totalItem));
        }
    }
}