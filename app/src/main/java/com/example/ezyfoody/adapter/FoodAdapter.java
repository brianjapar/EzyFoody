package com.example.ezyfoody.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfoody.DetailFoodActivity;
import com.example.ezyfoody.FoodActivity;
import com.example.ezyfoody.R;
import com.example.ezyfoody.model.Food;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    private ArrayList<Food> listFood;
    private Context context;
    public FoodAdapter(ArrayList<Food> listFood, FoodActivity activity){
        this.listFood = listFood;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.food_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = listFood.get(position);
        holder.tvName.setText(food.getFoodName());
        holder.tvPrice.setText("Price: Rp."+food.getFoodPrice());
        holder.foodImage.setImageResource(food.getFoodImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TESTING BRIAN", "onClick: "+food.getFoodImage());
                Toast.makeText(context,"Want order "+food.getFoodName()+"?",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailFoodActivity.class)
                        .putExtra("name",food.getFoodName())
                        .putExtra("price",food.getFoodPrice().toString())
                        .putExtra("image",food.getFoodImage());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView foodImage;
        TextView tvName,tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.iv_foodImage);
            tvName = itemView.findViewById(R.id.tv_foodName);
            tvPrice = itemView.findViewById(R.id.tv_foodPrice);
        }
    }

}
