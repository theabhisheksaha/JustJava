package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.*;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int Quantity=2;
    public boolean hasWhippedCream;
    public boolean hasChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        String Name;
        EditText nameField=(EditText)findViewById(R.id.name_field);
        Name=nameField.getText().toString();

        CheckBox whippedCreamCheckedBox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream=whippedCreamCheckedBox.isChecked();

        CheckBox chocolateCheckBox=(CheckBox)findViewById(R.id.chocolate_checkbox);
        hasChocolate=chocolateCheckBox.isChecked();
        int price=calculatePrice(hasWhippedCream,hasChocolate);

        String mail="Name: "+Name+"\nAdd Whipped Cream? "+hasWhippedCream+"\n Add Chocolate? "+hasChocolate+"\nQuantity: "+Quantity+"\nTotal: $"+price+"\nThank You!!";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        /* only email apps should handle this */
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT,mail);
        intent.putExtra(Intent.EXTRA_EMAIL,"abhishek22.2323@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order For "+Name);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        }
    public int calculatePrice(boolean hasWhippedCream, boolean hasChocolate)
    {
        int basePrice=5;
        //add 1$ for whipped cream
        if(hasWhippedCream)
            basePrice=basePrice+1;
        //add 2$ for choclate topping
        if(hasChocolate)
            basePrice=basePrice+2;
        //return the total price
        return (basePrice*Quantity);

    }

    public void increment(View view)
    {

        if(Quantity==100)
        {
            Context context=getApplicationContext();
            int duration=Toast.LENGTH_SHORT;
            Toast toast=Toast.makeText(context,"Please Enter Value Below 100",duration);
            toast.show();
            return;
        }
        Quantity++;
        displayQuantity(Quantity);
    }
    public void decrement(View view)
    {

        if(Quantity==1)
        {
            Context context=getApplicationContext();
            int duration=Toast.LENGTH_SHORT;
            Toast toast=Toast.makeText(context,"Please Enter Value Above 0",duration);
            toast.show();
            return;
        }
            Quantity--;
            displayQuantity(Quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



}