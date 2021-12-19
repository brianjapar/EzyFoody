package com.example.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFoodActivity extends AppCompatActivity {


    private ImageView ivFoodImage;
    private TextView tvFoodName, tvFoodPrice;
    private Button btnOrder;
    private EditText etQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra("name");
        Integer foodPrice = Integer.parseInt(intent.getStringExtra("price"));
        Integer foodImage = intent.getIntExtra("image",0);

        btnOrder = findViewById(R.id.btn_order);
        etQuantity = findViewById(R.id.et_foodQty);
        tvFoodName = findViewById(R.id.tv_foodName);
        tvFoodPrice = findViewById(R.id.tv_foodPrice);
        ivFoodImage = (ImageView) findViewById(R.id.iv_foodImage);
        tvFoodName.setText(foodName);
        tvFoodPrice.setText("Rp. "+foodPrice);
        ivFoodImage.setImageResource(foodImage);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etQuantity.getText().toString().isEmpty()){
                    Toast.makeText(DetailFoodActivity.this,"Input Quantity.",Toast.LENGTH_SHORT).show();
                }else{
                    Integer qty = Integer.parseInt(etQuantity.getText().toString());
                    Intent intent = new Intent(DetailFoodActivity.this, OrderActivity.class)
                            .putExtra("foodName",foodName)
                            .putExtra("foodPrice",foodPrice.toString())
                            .putExtra("foodQty",qty.toString())
                            .putExtra("foodImage",foodImage);
                    startActivity(intent);
                    Toast.makeText(DetailFoodActivity.this,"Success add to cart Order.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}