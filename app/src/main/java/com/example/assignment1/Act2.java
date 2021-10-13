package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Act2 extends AppCompatActivity {
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //variable declaration
        button2 = (Button) findViewById(R.id.button2);
        //display the EMI and total interest value in textview of the second page
        TextView  receiver_msg = (TextView)findViewById(R.id.emi);
        TextView  secondreceiver_msg = (TextView)findViewById(R.id.totalinterest);
        Intent intent = getIntent();//get intent from the previous activity
        String str = intent.getStringExtra("message_key");
        String str2 = intent.getStringExtra("message_key2");
        //setText
        receiver_msg.setText(str);
        secondreceiver_msg.setText(str2);

        //onClickListener() to return to main page when button is clicked
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactmain();
            }
        });

    }
    //intent for main activity
    public void openactmain (){
        Intent intent = new Intent(this
                , MainActivity.class);
        startActivity(intent);

    }
}
