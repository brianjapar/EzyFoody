package com.example.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezyfoody.adapter.OrderAdapter;
import com.example.ezyfoody.data.OrderData;
import com.example.ezyfoody.data.UserData;
import com.example.ezyfoody.model.Order;
import com.example.ezyfoody.model.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private ArrayList<Order> orders;
    private RecyclerView rvOrder;
    private OrderAdapter orderAdapter;
    private FloatingActionButton floatingActionButton;
    private TextView tvTotal,tvEmpty;
    private ImageButton ibHome;
    private Button btnPay;
    public ArrayList<Order> listOrder = OrderData.getInstance().orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        rvOrder = findViewById(R.id.rv_order);
        rvOrder.setHasFixedSize(true);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));


        tvTotal = findViewById(R.id.tv_totalPrice);
        Intent intent = getIntent();
        String foodName = intent.getStringExtra("foodName");
        Integer foodPrice = Integer.parseInt(intent.getStringExtra("foodPrice"));
        Integer foodQuantity = Integer.parseInt(intent.getStringExtra("foodQty"));
        Integer foodImage = intent.getIntExtra("foodImage",0);

        tvEmpty = findViewById(R.id.tv_empty);
        int flag=0;
        if(foodName.isEmpty() || foodName.equals("")){
            flag=1;
        }else{
            for (Order or2: listOrder) {
                if(or2.getFoodName().equals(foodName)){
                    or2.setQuantity(or2.getQuantity()+foodQuantity);
                    flag=1;
                    break;
                }
            }
        }

        if(flag==0){
            OrderData.getInstance().orders.add(new Order(foodName,foodPrice,foodQuantity,foodImage));
        }
        //OrderData.getInstance().orders.add(new Order(foodName,foodPrice,foodQuantity,foodImage));
        orderAdapter = new OrderAdapter(listOrder,OrderActivity.this);

        rvOrder.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();

        int temp=setTextTotal();

        floatingActionButton = findViewById(R.id.btn_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<User> listUser = UserData.getInstance().users;
                String username = "";
                for (User u: listUser) {
                     username = u.getUsername();
                     break;
                }
                Intent intent2 = new Intent(OrderActivity.this, HomeActivity.class)
                        .putExtra("username",username);
                startActivity(intent2);
            }
        });

        btnPay = findViewById(R.id.btn_pay);
        int finalTemp = temp;
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> listUser = UserData.getInstance().users;
                String username = "";
                int balanche=0;
                for (User u: listUser) {
                    username = u.getUsername();
                    balanche = u.getBalanche();
                    break;
                }
                if(finalTemp == 0){
                    showBottomSheet("You must order item first!");
                }else if(balanche < finalTemp){
                    showBottomSheet("Your current money is not enough. Please go to the TOP UP menu to top up money.");
                }else{

                    for (User u: listUser) {
                        u.setBalanche(u.getBalanche()-finalTemp);
                        break;
                    }
                    Intent intent2 = new Intent(OrderActivity.this, ThankYouActivity.class)
                            .putExtra("username",username);
                    startActivity(intent2);
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
                Intent intent3 = new Intent(OrderActivity.this, HomeActivity.class)
                        .putExtra("username",username);
                startActivity(intent3);
            }
        });

    }

    public int setTextTotal(){
        int temp=0;
        if(listOrder.isEmpty()){
            tvTotal.setText("Rp. 0");
            tvEmpty.setVisibility(View.VISIBLE);
        }else{
            for (Order or: listOrder) {
                temp+=(or.getFoodPrice()*or.getQuantity());
            }
            tvTotal.setText("Rp. "+temp);
        }
        return temp;
    }
    public void showBottomSheet(String text){
        TextView tvBsText;
        BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(OrderActivity.this);
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