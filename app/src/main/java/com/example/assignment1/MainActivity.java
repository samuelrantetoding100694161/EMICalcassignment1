package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    Button CalculateEMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //variable declaration for EMI calculator
        final EditText Principal = (EditText) findViewById(R.id.principalamount);
        final EditText interest = (EditText) findViewById(R.id.interestrate);
        final EditText TotalInterest = (EditText) findViewById(R.id.totalinterest);
        final EditText res = (EditText) findViewById(R.id.emi);

        spinner = (Spinner) findViewById(R.id.spinner1);
        //to pass value from string into the dropdown spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        CalculateEMI = (Button) findViewById(R.id.button);
        CalculateEMI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {//onclick() method to get the text value and parse it into string
                String st1 = Principal.getText().toString();
                String st2 = interest.getText().toString();
                if (spinner.getSelectedItem() == null) {
                    return;
                }
                String st3 = spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(st1)) {
                    Principal.setError("Enter Principal Amount");
                    Principal.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(st2)) {
                    interest.setError("Enter Interest Rate");
                    interest.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(st3)) {
                    interest.setError("Enter Amortization Period");
                    interest.requestFocus();
                    return;
                }
                //Assign the parse float value for the calculation
                float p = Float.parseFloat(st1);
                float i = Float.parseFloat(st2);
                float y = Float.parseFloat(st3);
                float Principal = PrincipalCal(p);
                float Rate = InterestCal(i);
                float Months = MonthlyCal(y);
                float Divident = DividentCal(Rate, Months);
                float FinalDiv = FinalDivCal(Principal, Rate, Divident);
                float D = DividerCalc(Divident);
                float emi = EmiCalc(FinalDiv, D);
                float ta = TaCalc(emi, Months);

                float ti = calTotalInt(ta, Principal);
                //show the value of EMI and total interest in textview using setText()
                res.setText(String.valueOf(emi));
                TotalInterest.setText(String.valueOf(ti));
                //passing value to the second page to show the EMI and total interest in second page
                String str1 = res.getText().toString();
                String str2 = TotalInterest.getText().toString();


                Intent intent = new Intent(getApplicationContext(), Act2.class);//intent to access second page


                intent.putExtra("message_key", str1);//EMI value
                intent.putExtra("message_key2", str2);//Total interest value


                startActivity(intent);

            }
        });

    }
    //The formula needed for EMI calculation
    public float PrincipalCal(float p) {
        return (float)(p);//Principal value
    }
    public float InterestCal(float i) {
        return (float)(i / 12 / 100);//Monthly interest rate value in percentage form
    }
    public float MonthlyCal(float y) {
        return (float)(y * 12);//value for amortization period
    }
    public float DividerCalc(float Divident) {
        return (float)(Divident - 1);//denominator value, which equal to (1+rate power to month)
    }
    public float DividentCal(float Rate, float Months) {
        return (float)(Math.pow(1 + Rate, Months));//value inside bracket to make easier calculation for denominator and numerator
    }
    public float FinalDivCal(float Principal, float Rate, float Dvdnt) {
        return (float)(Principal * Rate * Dvdnt);//the EMI formula
    }
    public float TaCalc(float emi, Float Months) {
        return (float)(emi * Months);
    }
    public float EmiCalc(float FinalDiv, Float D) {
        return (float)(FinalDiv / D);
    }
    public float calTotalInt(float ta, float Principal) {
        return (float)(ta - Principal);
    }

}



