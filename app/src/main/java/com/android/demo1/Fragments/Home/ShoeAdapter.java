package com.android.demo1.Fragments.Home;

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

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ViewHolder> {
    List<Shoe> shoes;
    Context context;

    IOnClickShoe iOnClickShoe;

    public IOnClickShoe getiOnClickShoe() {
        return iOnClickShoe;
    }

    public void setiOnClickShoe(IOnClickShoe iOnClickShoe) {
        this.iOnClickShoe = iOnClickShoe;
    }

    public ShoeAdapter(List<Shoe> shoes, Context context) {
        this.shoes = shoes;
        this.context = context;
    }

    public void setShoes(List<Shoe> shoes) {
        this.shoes = shoes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShoeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_shoe, parent, false);
        ShoeAdapter.ViewHolder viewHolder = new ShoeAdapter.ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Shoe shoe = shoes.get(position);
        holder.tvName.setText(shoe.getName());
        holder.tvPrice.setText(shoe.getPrice()+" VND");
        Glide.with(context).load(shoe.getImageUrl()).into(holder.imgShoe);

        holder.tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickShoe.iOnClickAddToCart(shoe, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoes == null ? 0 : shoes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgShoe;
        TextView tvName, tvPrice, tvAddToCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShoe = itemView.findViewById(R.id.imgShoe);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvAddToCart = itemView.findViewById(R.id.tvAddToCart);
        }
    }
}
