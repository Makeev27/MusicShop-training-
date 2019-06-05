package com.android.example.musicshop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class salePageActivity extends AppCompatActivity {
    String[] addresses = {"alyai225@gmail.com"};
    String subject = "Information about sales in our Music Shop";
    String emailText = "If your order is more than 1000$, you'll get discount 30%. Press the button below to get this info on your email.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_page);
        setTitle("Sale information");
        TextView salePageTextView = findViewById(R.id.salePageTextView);
        salePageTextView.setText(emailText);
    }

    public void submitInformation(View view) {
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