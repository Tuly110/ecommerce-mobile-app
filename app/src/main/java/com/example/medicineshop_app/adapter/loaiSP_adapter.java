package com.example.medicineshop_app.adapter;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medicineshop_app.R;
import com.example.medicineshop_app.model.LoaiSP;

import java.util.List;

public class loaiSP_adapter extends BaseAdapter {

    List<LoaiSP> array;
    Context context;
    public loaiSP_adapter (Context context,List<LoaiSP> array)
    {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView textten_sp;
        ImageView imghinhanh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sanpham, null);
            viewHolder.textten_sp = view.findViewById(R.id.item_tensp);
            viewHolder.imghinhanh = view.findViewById(R.id.item_img);
            view.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) view.getTag();

        }
        viewHolder.textten_sp.setText(array.get(i).getTensanpham());
        Glide.with(context).load(array.get(i).getHinhanh()).into(viewHolder.imghinhanh);

        return view;

    }
}
