package com.android.example.musicshop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"olushkagromova@yandex.ru"};
    String subject = "Order from Music Shop";
    String emailText;
    String discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Your Order");
        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity= receivedOrderIntent.getIntExtra("quantity" , 0);
        double  orderPrice  = receivedOrderIntent.getDoubleExtra("orderPrice" , 0);
        double  price  = receivedOrderIntent.getDoubleExtra("price" , 0);
        if (orderPrice >= 1000) {
            orderPrice = orderPrice * 0.7;
            price = price * 0.7;
            discount = "30%";
        }
        else{
            discount = "0%";
        }
            if (quantity != 0 && userName != null) {
                emailText = ("Customer name: " + userName + "\n" +
                        "Goods name: " + goodsName + "\n" +
                        "Quantity: " + quantity + "\n" +  "Price: " +
                        "" + price + "\n" + "Discount: " + discount + " \n" + "Order price: " + orderPrice);
                TextView orderTextView = findViewById(R.id.orderTextView);
                orderTextView.setText(emailText);
            }
            else{
                emailText = ("Sorry you didn't select any goods or didn't enter your name");
                TextView orderTextView = findViewById(R.id.orderTextView);
                orderTextView.setText(emailText);
                Button buttonSubmitOrder = findViewById(R.id.submitOrderButton);
                buttonSubmitOrder.setVisibility(View.INVISIBLE);
            }
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
        }
    }
}
