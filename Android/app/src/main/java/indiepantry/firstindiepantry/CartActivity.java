/****************************************************************************************/
/*
/* FILE NAME: CartActivity.java
/*
/* DESCRIPTION: Activity class for handling looking at the cart
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/15/17 Maxwell Reeser       Created the class
/* 4/25/17 Brandon Hollier      Added proceedToCheckout
/*
/*
/*
/****************************************************************************************/


package indiepantry.firstindiepantry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    public static final String EXTRA_ORDER_TOTAL = "indiepantry.firstindiepantry.CART_TOTAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        if (savedInstanceState != null)
            return;

        ScrollView available_items = (ScrollView) findViewById(R.id.cartItemList);
        LinearLayout verticalLayout = (LinearLayout) findViewById(R.id.cartItemLinearLayout);

        TextView placard = (TextView) findViewById(R.id.cartPagePlacard);
        placard.setText(SideData.getUsername() + "'s Cart");

        ArrayList<item_display> items = SideData.getCart();
        for(item_display bob: items){
            LinearLayout horizontalLayout = new LinearLayout(this);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            Button item_button = new Button(this);
            item_button.setTag(bob);
            item_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    item_display bob = (item_display) button.getTag();
                    Intent intent = new Intent(CartActivity.this, Item_Activity.class);
                    SideData.setTemp_item(bob);
                    startActivity(intent);
                }
            });
            item_button.setText(bob.getName() + " " + bob.getCost());

            Button vendor_button = new Button(this);
            vendor_button.setTag(bob.getVendor());
            vendor_button.setText(bob.getVendor_name());
            vendor_button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    Vendor vendor = (Vendor) button.getTag();
                    Intent intent = new Intent(CartActivity.this,VendorPageActivity.class);
                    SideData.setTemp_vendor(vendor);
                    startActivity(intent);
                }
            });

            horizontalLayout.addView(item_button);
            horizontalLayout.addView(vendor_button);

            verticalLayout.addView(horizontalLayout);
        }


    }

    /** Called when the user taps the Checkout button */
    public void proceedToCheckout(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        Double orderTotal = 4.95;
        intent.putExtra(EXTRA_ORDER_TOTAL, orderTotal);
        startActivity(intent);
    }
}
