package com.android.demo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.demo1.Models.Shoe;
import com.android.demo1.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShoeAdapter2 extends RecyclerView.Adapter<ShoeAdapter2.ViewHolder> {
    List<Shoe> shoes;
    Context context;

    IOnClickShoe iOnClickShoe;

    public IOnClickShoe getiOnClickShoe() {
        return iOnClickShoe;
    }

    public void setiOnClickShoe(IOnClickShoe iOnClickShoe) {
        this.iOnClickShoe = iOnClickShoe;
    }

    public ShoeAdapter2(List<Shoe> shoes, Context context) {
        this.shoes = shoes;
        this.context = context;
    }

    public void setShoes(List<Shoe> shoes) {
        this.shoes = shoes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShoeAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_shoe, parent, false);
        ShoeAdapter2.ViewHolder viewHolder = new ShoeAdapter2.ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeAdapter2.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Shoe shoe = shoes.get(position);
        holder.tvName.setText(shoe.getName());
        holder.tvPrice.setText(shoe.getPrice()+" VND");

        holder.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickShoe.clickMore(shoe, position, holder.imgMore);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoes == null ? 0 : shoes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgShoe, imgMore;
        TextView tvName, tvPrice, tvAddToCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShoe = itemView.findViewById(R.id.imgShoe);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvAddToCart = itemView.findViewById(R.id.tvAddToCart);
            imgMore = itemView.findViewById(R.id.imgMore);
        }
    }
}

