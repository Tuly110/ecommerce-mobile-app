package com.example.medicineshop_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicineshop_app.R;
import com.example.medicineshop_app.model.DonHang;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<DonHang> listdonhang;

    public DonHangAdapter(Context context, List<DonHang> listdonhang) {
        this.context = context;
        this.listdonhang = listdonhang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Toast.makeText(context,"Chào", Toast.LENGTH_LONG).show();
        DonHang donHang = listdonhang.get(position);
        Toast.makeText(context,donHang.getId(), Toast.LENGTH_LONG).show();
        holder.txtdonhang.setText("Đơn hàng"+donHang.getId());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.reChiTiet.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(donHang.getItem().size());
//        adapter chi tiet
        ChiTietAdapter chiTietAdapter = new ChiTietAdapter(context,donHang.getItem());
        holder.reChiTiet.setLayoutManager(layoutManager);
        holder.reChiTiet.setAdapter(chiTietAdapter);
        holder.reChiTiet.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return listdonhang.size(); // Trả về số lượng phần tử trong danh sách
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtdonhang;
        RecyclerView reChiTiet;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdonhang = itemView.findViewById(R.id.iddonhang);
            reChiTiet = itemView.findViewById(R.id.recycleview_chitiet);
        }
    }
}
