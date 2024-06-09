package com.example.medicineshop_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicineshop_app.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicineshop_app.model.Comment;
import com.example.medicineshop_app.model.DonHang;


import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    Context context;
    private List<Comment> ds;

    public CommentAdapter(Context context, List<Comment> ds) { // copy chat vao chua roi á chạy chua dc ko
        this.context = context;
        this.ds = ds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_comment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comment comment = ds.get(position);
        holder.UserName.setText(comment.getUsername());
        holder.dateComment.setText(comment.getCreate_time());
        holder.content.setText(comment.getComment());
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView UserName, dateComment, content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            UserName = itemView.findViewById(R.id.UserName);
            dateComment = itemView.findViewById(R.id.dateComment);
            content = itemView.findViewById(R.id.content);
        }
    }
}

