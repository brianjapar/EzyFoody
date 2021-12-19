package com.example.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ezyfoody.adapter.FoodAdapter;
import com.example.ezyfoody.data.UserData;
import com.example.ezyfoody.model.Food;
import com.example.ezyfoody.model.User;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {


    private RecyclerView rvFood;
    private ArrayList<Food> foods;
    private FoodAdapter foodAdapter;
    private TextView tvTitle,tvDesc;
    private ImageButton ibHome,ibOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        rvFood = findViewById(R.id.rv_food);
        rvFood.setHasFixedSize(true);
        rvFood.setLayoutManager(new LinearLayoutManager(this));


        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String type = intent.getStringExtra("type");

        tvTitle = findViewById(R.id.tv_title);
        tvDesc = findViewById(R.id.tv_desc);
        foods = new ArrayList<>();
        if(type.equals("Food")){
            tvTitle.setText("Food Menu");
            tvDesc.setText("Have a look, and i hope you can find the food you want.");
            foods.add(new Food("Mie Goreng","Food",20000,R.drawable.noodle));
            foods.add(new Food("Bakso","Food",15000,R.drawable.bakso));
            foods.add(new Food("Kwetiaw Goreng","Food",25000,R.drawable.kwetiaw));
            foods.add(new Food("Bihun Goreng","Food",23000,R.drawable.bihun));
            foods.add(new Food("Kentucky Fried Chicken","Food",12000,R.drawable.kentucky));
        }else if(type.equals("Drink")){
            tvTitle.setText("Drink Menu");
            tvDesc.setText("Have a look, and i hope you can find the drink you want.");
            foods.add(new Food("Air Mineral","Drink",3000,R.drawable.airmineral));
            foods.add(new Food("Es Teh Manis","Drink",5000,R.drawable.tehmanis));
            foods.add(new Food("Es Jeruk Kunci","Drink",8000,R.drawable.esjeruk));
            foods.add(new Food("Es Cincau","Drink",12000,R.drawable.cincau));
            foods.add(new Food("Es Campur","Drink",15000,R.drawable.escampur));
        }else if(type.equals("Snack")){
            tvTitle.setText("Snack Menu");
            tvDesc.setText("Have a look, and i hope you can find the snack you want.");
            foods.add(new Food("Kentang Goreng","Snack",14000,R.drawable.kentang));
            foods.add(new Food("Risoles","Snack",4000,R.drawable.risol));
            foods.add(new Food("Oreo","Snack",6000,R.drawable.oreo));
            foods.add(new Food("Pocky","Snack",10000,R.drawable.pocky));
        }

        foodAdapter = new FoodAdapter(foods,FoodActivity.this);
        rvFood.setAdapter(foodAdapter);
        foodAdapter.notifyDataSetChanged();

        ibHome = findViewById(R.id.ib_menu);
        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> listUser = UserData.getInstance().users;
                String username = "";
                for (User u: listUser) {
                    username = u.getUsername();
                    break;
                }
                Intent intent3 = new Intent(FoodActivity.this, HomeActivity.class)
                        .putExtra("username",username);
                startActivity(intent3);
            }
        });

        ibOrder = findViewById(R.id.ib_cart);
        ibOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer temp = 10;
                Intent intent = new Intent(FoodActivity.this,OrderActivity.class)
                        .putExtra("foodName","")
                        .putExtra("foodPrice",temp.toString())
                        .putExtra("foodQty",temp.toString())
                        .putExtra("foodImage","");
                startActivity(intent);
            }
        });

    }
}