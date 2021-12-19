package com.example.ezyfoody.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfoody.OrderActivity;
import com.example.ezyfoody.R;
import com.example.ezyfoody.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{


    private ArrayList<Order> listOrder;
    private Context context;
    public OrderAdapter(ArrayList<Order> listOrder, OrderActivity activity){
        this.listOrder = listOrder;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_item_list,parent,false);
        OrderAdapter.ViewHolder viewHolder = new OrderAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = listOrder.get(position);
        holder.tvName.setText(order.getFoodName());
        holder.tvPrice.setText("Price: Rp."+order.getFoodPrice());
        holder.tvQty.setText("Quantity: "+order.getQuantity());
        holder.foodImage.setImageResource(order.getFoodImage());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(order.getFoodName());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,order.getFoodName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public void removeItem(String name){
        for (int i=0 ;i<listOrder.size();i++){
            if(listOrder.get(i).getFoodName().equals(name)){
                listOrder.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
        notifyDataSetChanged();
        Toast.makeText(context,name+" was deleted from order!",Toast.LENGTH_SHORT).show();
    }

    public void removeAllItem(){
        for (int i=0 ;i<listOrder.size();i++){
            listOrder.remove(i);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView tvName,tvQty,tvPrice;
        Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.iv_foodImage);
            tvName = itemView.findViewById(R.id.tv_foodName);
            tvPrice = itemView.findViewById(R.id.tv_foodPrice);
            tvQty = itemView.findViewById(R.id.tv_foodQuantity);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
