package com.nadeem.loanapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    /**
     * Start by creating variables for all of the XML elements inside of
     * the Layout
     */

    EditText loanAmountEditText;
    TextView percentTextView;
    SeekBar percentSeekBar;
    TextView yearsTextView;
    SeekBar yearsSeekBar;
    TextView monthlyPayment;
    TextView totalCost;

    /**
     * CurrencyFormat will be used to format numbers
     * to look like percentage
     */

    public static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();


    /**
     * percentFormat will be used to format numbers
     * to look like currency
     */

    public static final NumberFormat percentFormat = NumberFormat.getPercentInstance();


    private double interest = 0.03;
    private double loanAmount = 0.0;
    private int numberOfYears = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Link all of the XML elements to the code
         */

        loanAmountEditText = findViewById(R.id.amount);
        percentTextView = findViewById(R.id.percentTextView);
        percentSeekBar = findViewById(R.id.percentSeekBar);
        yearsTextView = findViewById(R.id.yearTextView);
        yearsSeekBar = findViewById(R.id.yearsSeekBar);
       monthlyPayment = findViewById(R.id.monthlyPayment);
        totalCost = findViewById(R.id.totalCost);
        monthlyPayment.setText(currencyFormat.format(0));
        totalCost.setText(currencyFormat.format(0));


        yearsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numberOfYears = progress;
                calculateLoan();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        loanAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    loanAmount = Double.parseDouble(s.toString());

                }
                  catch (NumberFormatException e)  {
                    loanAmount = 0.0;
                  }
                   calculateLoan();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                interest = (progress/100.0);
                calculateLoan();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        }


    /**
     * Calculate will be used to caculate the monthly payment and
     * the total cost of your loan after a period of time
     */


    protected void calculateLoan(){
        /**
         * First step: Grab the current interest rate and assign it to
         * the percent/textView
         */

        percentTextView.setText(percentFormat.format(interest));

        /**
         * Second step : Grab the current number of years and assign it to
         * the yearsTextView
         */

        yearsTextView.setText(numberOfYears + " Years");


        /**
         * Third Step:
         * Calculate the amount of interest paid for the year
         * Calcukate the total payment
         */

        double interestPerYear = loanAmount * interest;
        double totalPayment = loanAmount + (interestPerYear * numberOfYears);

        /**
         * Display the monthly payment  and the Total cost in
         * the monthPaymentTextView and total paymentTextView
         */

        monthlyPayment.setText(
                currencyFormat.format(totalPayment/numberOfYears/12));
        totalCost.setText(currencyFormat.format(totalPayment));







    }






}
