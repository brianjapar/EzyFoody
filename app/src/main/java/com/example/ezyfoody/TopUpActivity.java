package com.example.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezyfoody.data.UserData;
import com.example.ezyfoody.model.User;

import java.util.ArrayList;

public class TopUpActivity extends AppCompatActivity {

    private TextView tvUsername,tvBalanche;
    private EditText etNominal;
    private Button btnTopup;
    private ImageButton ibHome,ibOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        tvUsername = findViewById(R.id.tv_username);
        tvBalanche = findViewById(R.id.tv_balanche);
        etNominal = findViewById(R.id.et_nominal);
        btnTopup = findViewById(R.id.btn_topup);

        tvUsername.setText("Hello, "+username);
        ArrayList<User> listUser = UserData.getInstance().users;
        for (User u: listUser) {
            if(u.getUsername().equals(username)){
                tvBalanche.setText("My Balanche: "+u.getBalanche());
                break;
            }
        }
        btnTopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNominal.getText().toString().isEmpty()){
                    Toast.makeText(TopUpActivity.this,"Input Nominal.",Toast.LENGTH_SHORT).show();
                }else{
                    Integer nominal = Integer.parseInt(etNominal.getText().toString());
                    ArrayList<User> listUser = UserData.getInstance().users;
                    for (User u: listUser) {
                        if(u.getUsername().equals(username)){
                            u.setBalanche(u.getBalanche()+nominal);
                            break;
                        }
                    }
                    ArrayList<User> listUser2 = UserData.getInstance().users;
                    for (User u2: listUser2) {
                        if(u2.getUsername().equals(username)){
                            tvBalanche.setText("My Balanche: "+u2.getBalanche());
                            break;
                        }
                    }
                    Intent intent = new Intent(TopUpActivity.this, HomeActivity.class)
                            .putExtra("username",username);
                    startActivity(intent);
                }
            }
        });

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
                Intent intent3 = new Intent(TopUpActivity.this, HomeActivity.class)
                        .putExtra("username",username);
                startActivity(intent3);
            }
        });

        ibOrder = findViewById(R.id.ib_cart);
        ibOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer temp = 10;
                Intent intent = new Intent(TopUpActivity.this,OrderActivity.class)
                        .putExtra("foodName","")
                        .putExtra("foodPrice",temp.toString())
                        .putExtra("foodQty",temp.toString())
                        .putExtra("foodImage","");
                startActivity(intent);
            }
        });
    }
}