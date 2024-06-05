package com.example.medicineshop_app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medicineshop_app.Interface.Image_ClickListener;
import com.example.medicineshop_app.R;
import com.example.medicineshop_app.adapter.EventBus.TinhTongEvent;
import com.example.medicineshop_app.model.GioHang;
import com.example.medicineshop_app.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import okhttp3.internal.Util;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {
    Context context;
    List<GioHang> gioHangList;

    public GioHangAdapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
       holder.item_giohang_tensp.setText(gioHang.getTensp());
        holder.item_giohang_soluong.setText(Integer.toString(gioHang.getSoluong()));
        Glide.with(context).load(gioHang.getHinhsp()).into(holder.item_giohang_image);


        holder.item_giohang_gia.setText(Integer.toString((int) gioHang.getGiasp()));

        long gia = gioHang.getSoluong() * gioHang.getGiasp();
        holder.item_giohang_giasp2.setText(String.valueOf(gia));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Utils.mangmuahang.add(gioHang);
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }else{
                    for(int i =0; i< Utils.mangmuahang.size(); i++){
                        if(Utils.mangmuahang.get(i).getIdsp() == gioHang.getIdsp()){
//                            Toast.makeText(context,Utils.mangmuahang.get(i).getTensp().toString(),Toast.LENGTH_LONG).show();
                            EventBus.getDefault().postSticky(new TinhTongEvent());
                            Utils.mangmuahang.remove(i);

                        }
                    }
                }
            }
        });
        holder.setListener(new Image_ClickListener() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if(giatri ==1){
                    if(gioHangList.get(pos).getSoluong() > 1){
                        int soluongmoi = gioHangList.get(pos).getSoluong()-1;
                        gioHangList.get(pos).setSoluong(soluongmoi);

                        holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong()+ " ");
                        long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getGiasp();
                        holder.item_giohang_giasp2.setText(String.valueOf(gia));
                        EventBus.getDefault().postSticky(new TinhTongEvent());

//                        Toast.makeText(context,String.valueOf(gia) + "số lượng > 1",Toast.LENGTH_LONG).show();
                    }else if(gioHangList.get(pos).getSoluong() == 1){

                        AlertDialog.Builder builder =  new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn có muốn xóa sản phẩm khỏi giỏ hàng không?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

//                                Toast.makeText(context,gioHangList.get(pos).getGiasp() + "bằng 1",Toast.LENGTH_LONG).show();
//                                holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong()+ " ");
//                                long gia = gioHangList.get(pos).getSoluong() * 1;
//
//                                holder.item_giohang_giasp2.setText(String.valueOf(gia));
                                Utils.mangmuahang.remove(gioHangList.get(pos));
                                Utils.manggiohang.remove(pos);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new TinhTongEvent());
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();

                    }
                }else if(giatri ==2)
                {

                    if(gioHangList.get(pos).getSoluong() < 11){
                        int soluongmoi = gioHangList.get(pos).getSoluong()+1;
                        gioHangList.get(pos).setSoluong(soluongmoi);
                    }
                    holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong()+ " ");
                    long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getGiasp();
                    holder.item_giohang_giasp2.setText(String.valueOf(gia));
//                    Toast.makeText(context,String.valueOf(gia) + "Tăng",Toast.LENGTH_LONG).show();
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }

            }
        });
    }
    @Override
    public int getItemCount() {
        return gioHangList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_giohang_image, img_tang, img_giam;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong, item_giohang_giasp2;
        Image_ClickListener listener;
        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            item_giohang_image = itemView.findViewById(R.id.item_giohang_image);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_giohang_giasp2 = itemView.findViewById(R.id.item_giohang_giasp2);
            img_giam = itemView.findViewById(R.id.item_giohang_giam);
            img_tang = itemView.findViewById(R.id.item_giohang_tang);
            checkBox = itemView.findViewById(R.id.item_giohang_checkbox);
//            sự kiện click
            img_tang.setOnClickListener(this);
            img_giam.setOnClickListener(this);
        }

        public void setListener(Image_ClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            if(view == img_giam)
            {
                listener.onImageClick(view, getAdapterPosition(), 1);

//                1 trừ
            }else if(view == img_tang){
//                    2 cộng
                listener.onImageClick(view, getAdapterPosition(), 2);
            }
        }
    }
}
