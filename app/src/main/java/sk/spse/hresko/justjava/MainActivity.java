package sk.spse.hresko.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 1;
    private CheckBox whippingCream;
    private CheckBox chocolate;
    private EditText customersName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whippingCream = (CheckBox) findViewById(R.id.checkbox_whipping_cream);
        chocolate = (CheckBox) findViewById(R.id.checkbox_chocolate);
        customersName=(EditText) findViewById(R.id.customersName);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: hresko@spse-po.sk"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.orderSummaryFor)+" "+getCustomersInfo(customersName));
        intent.putExtra(Intent.EXTRA_TEXT, createSummary( calculatePrice(5), checkBoxes(whippingCream), checkBoxes(chocolate)));
        startActivity(Intent.createChooser(intent, getString(R.string.sendEmailVia)));

    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(int pricePerCup) {
       int toppings = 0;
       int pricePerWhippingCream=1;
       int pricePerChocolate=2;
        if(checkBoxes(whippingCream)){
         toppings=toppings+quantity*pricePerWhippingCream;
        }
         if(checkBoxes(chocolate)){
            toppings=toppings+quantity*pricePerChocolate;
        }
        return quantity * pricePerCup + toppings;
    }

    private String createSummary( int price, boolean whippingCream, boolean chocolate) {
        return
                 getString(R.string.quantity)+" " + quantity + "\n"
                + getString(R.string.whipping_cream)+": " + whippingCream + "\n"
                + getString(R.string.chocolate)+": "+ chocolate + "\n"
                + getString(R.string.totalPrice)+" " + price +"€" +"\n"
                + getString(R.string.thanks)
                ;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */


    public void increase(View view) {

        quantity++;
        if(quantity==101){
            Toast.makeText(MainActivity.this,getString(R.string.orderLimit1),Toast.LENGTH_LONG).show();
            quantity=100;
        }

        display(quantity);

    }

    public void decrease(View view) {

            quantity--;

        if(quantity==0){
            Toast.makeText(MainActivity.this,getString(R.string.orderLimit2),Toast.LENGTH_LONG).show();
            quantity=1;
        }
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */

    public boolean checkBoxes(View view) {

        if (((CheckBox) view).isChecked()) {
            return true;

        } else {
            return false;
        }
    }

    public String getCustomersInfo(View view){
       String info = ((EditText)view).getText().toString();

        return info;
    }

}