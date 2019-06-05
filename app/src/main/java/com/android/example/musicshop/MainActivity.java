package com.android.example.musicshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    int quantity = 0;
    Spinner spinner; // создания спиннера
    ArrayList spinnerArrayList; // создание списка под спиннер
    ArrayAdapter spinnerAdapter; // создание адаптера под спиннер
    HashMap goodsMap; // создание хэша для товаров
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditText);

        createSpinner();

        createMap();
    }

    public void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Guitar");
        spinnerArrayList.add("Drums");
        spinnerArrayList.add("Keyboard");
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerAdapter);
    }

    public void createMap(){
        goodsMap = new HashMap();
        goodsMap.put("Guitar", 500.0);
        goodsMap.put("Drums", 1500.0);
        goodsMap.put("Keyboard", 1000.0);
    }


    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView plusTextView = findViewById(R.id.quantityTextView);
        plusTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.orderPrice);
        priceTextView.setText("" + quantity * price + " $");
    }

    public void decreaseQuantity (View view){
        if (quantity > 0) quantity--;
        TextView minusTextView = findViewById(R.id.quantityTextView);
        minusTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.orderPrice);
        priceTextView.setText("" + quantity * price + " $");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.orderPrice);
        priceTextView.setText("" + quantity * price + " $");

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        switch (goodsName){
            case "Guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "Drums":7
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "Keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCard(View view) {
        Order order = new Order();

        order.userName = userNameEditText.getText().toString();

        order.goodsName = goodsName;

        order.quantity = quantity;

        order.orderPrice = quantity * price;

        order.price = price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent" , order.userName);
        orderIntent.putExtra("goodsName" , order.goodsName);
        orderIntent.putExtra("quantity" , order.quantity);
        orderIntent.putExtra("orderPrice" , order.orderPrice);
        orderIntent.putExtra("price" , order.price);

        startActivity(orderIntent);

    }

    public void toSaleActivity(View view) {
        Intent saleIntent = new Intent(MainActivity.this, salePageActivity.class);
        startActivity(saleIntent);
    }

}


