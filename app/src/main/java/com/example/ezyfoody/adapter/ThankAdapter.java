package com.example.ezyfoody.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfoody.R;
import com.example.ezyfoody.ThankYouActivity;
import com.example.ezyfoody.model.Order;

import java.util.ArrayList;

public class ThankAdapter extends RecyclerView.Adapter<ThankAdapter.ViewHolder>{


    private ArrayList<Order> listOrder;
    private Context context;
    public ThankAdapter(ArrayList<Order> listOrder, ThankYouActivity activity){
        this.listOrder = listOrder;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.thank_item_list,parent,false);
        ThankAdapter.ViewHolder viewHolder = new ThankAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = listOrder.get(position);
        holder.tvName.setText(order.getFoodName());
        holder.tvPrice.setText("Price: Rp."+order.getFoodPrice());
        holder.tvQty.setText("Quantity: "+order.getQuantity());
        holder.foodImage.setImageResource(order.getFoodImage());

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

    public void removeAllItem(){
        listOrder.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView tvName,tvQty,tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.iv_foodImage);
            tvName = itemView.findViewById(R.id.tv_foodName);
            tvPrice = itemView.findViewById(R.id.tv_foodPrice);
            tvQty = itemView.findViewById(R.id.tv_foodQuantity);
        }
    }
}
