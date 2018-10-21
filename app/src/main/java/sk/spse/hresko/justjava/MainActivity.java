package sk.spse.hresko.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
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

        displayMessage(createSummary(getCustomersInfo(customersName), calculatePrice(5), checkBoxes(whippingCream), checkBoxes(chocolate)));

    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(int pricePerCup) {
        return quantity * pricePerCup;
    }

    private String createSummary(String name, int price, boolean whippingCream, boolean chocolate) {
        return "Name: " + name + "\n"
                + "Quantity: " + quantity + "\n"
                + "Whipping Cream: " + whippingCream + "\n"
                + "Chocolate: " + chocolate + "\n"
                + "Total price: " + price + "\n"
                + "Thank you !"
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
        display(quantity);
    }

    public void decrease(View view) {
        if (quantity > 0) {
            quantity--;
        }
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

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