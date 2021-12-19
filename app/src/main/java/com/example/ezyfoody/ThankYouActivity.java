package com.example.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ezyfoody.adapter.OrderAdapter;
import com.example.ezyfoody.adapter.ThankAdapter;
import com.example.ezyfoody.data.OrderData;
import com.example.ezyfoody.data.UserData;
import com.example.ezyfoody.model.Order;
import com.example.ezyfoody.model.User;

import java.util.ArrayList;

public class ThankYouActivity extends AppCompatActivity {

    private Button btnBack;
    private RecyclerView rvThank;
    private TextView tvTotal;
    private ThankAdapter thankAdapter;
    private OrderActivity orderActivity;
    public ArrayList<Order> listOrder = OrderData.getInstance().orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        rvThank = findViewById(R.id.rv_orderThank);
        rvThank.setHasFixedSize(true);
        rvThank.setLayoutManager(new LinearLayoutManager(this));
        tvTotal = findViewById(R.id.tv_totalPrice);

        thankAdapter = new ThankAdapter(listOrder,ThankYouActivity.this);
        rvThank.setAdapter(thankAdapter);
        thankAdapter.notifyDataSetChanged();
//        OrderAdapter orderAdapter = new OrderAdapter(listOrder,orderActivity);
//        orderAdapter.removeAllItem();
//        orderAdapter.notifyDataSetChanged();

        if(listOrder.isEmpty()){
            tvTotal.setText("Rp. 0");
        }else{
            int temp=0;
            for (Order or: listOrder) {
                temp+=(or.getFoodPrice()*or.getQuantity());
            }
            tvTotal.setText("Rp. "+temp);
        }



        btnBack=findViewById(R.id.btn_mainMenu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> listUser = UserData.getInstance().users;
                String username = "";
                for (User u: listUser) {
                    username = u.getUsername();
                    break;
                }
                Intent intent = new Intent(ThankYouActivity.this, HomeActivity.class)
                        .putExtra("username",username);
                startActivity(intent);
                listOrder.clear();
                thankAdapter.removeAllItem();
                thankAdapter.notifyDataSetChanged();
            }
        });
    }
}