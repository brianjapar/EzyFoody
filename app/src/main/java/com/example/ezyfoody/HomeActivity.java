package com.example.ezyfoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private RelativeLayout rlFood,rlDrink,rlSnack,rlTopup;
    private TextView tvWelcome;
    private ImageButton ibOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rlFood = findViewById(R.id.rl_food);
        rlDrink = findViewById(R.id.rl_drinks);
        rlSnack = findViewById(R.id.rl_snack);
        rlTopup = findViewById(R.id.rl_topup);
        tvWelcome = findViewById(R.id.tv_welcome);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if(intent.getExtras() !=null){
            tvWelcome.setText("Welcome, "+username);
        }

        ibOrder = findViewById(R.id.ib_cart);
        ibOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer temp = 10;
                Intent intent = new Intent(HomeActivity.this,OrderActivity.class)
                        .putExtra("foodName","")
                        .putExtra("foodPrice",temp.toString())
                        .putExtra("foodQty",temp.toString())
                        .putExtra("foodImage","");
                startActivity(intent);
            }
        });


        rlFood.setOnClickListener(new View.OnClickListener(){
            String type = "Food";
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,FoodActivity.class)
                        .putExtra("username",username)
                        .putExtra("type",type);
                startActivity(intent);
            }

        });

        rlDrink.setOnClickListener(new View.OnClickListener(){
            String type = "Drink";
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,FoodActivity.class)
                        .putExtra("username",username)
                        .putExtra("type",type);
                startActivity(intent);
            }
        });

        rlSnack.setOnClickListener(new View.OnClickListener(){
            String type = "Snack";
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,FoodActivity.class)
                        .putExtra("username",username)
                        .putExtra("type",type);
                startActivity(intent);
            }
        });

        rlTopup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,TopUpActivity.class)
                        .putExtra("username",username);
                startActivity(intent);
            }
        });
    }


}