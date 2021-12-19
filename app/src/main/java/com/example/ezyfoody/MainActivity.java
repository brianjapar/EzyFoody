package com.example.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ezyfoody.data.UserData;
import com.example.ezyfoody.model.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    public ArrayList<User> listUser = UserData.getInstance().users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progress_bar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty() ) {
                    showBottomSheet("Please input username and Password");
                }else{
                    UserData.getInstance().users.add(new User(username,password,100000));

                    progressBar.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class)
                            .putExtra("username",username);
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }


    public void showBottomSheet(String text){
        TextView tvBsText;
        BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.bottom_sheet_layout,(LinearLayout)findViewById(R.id.bs_container));

        bottomSheetView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        tvBsText = bottomSheetView.findViewById(R.id.tv_bsText);
        tvBsText.setText(text);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }
}