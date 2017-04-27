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
/* 4/26/17 Brandon Hollier      Overhauled class to use an Adapter
/*
/*
/****************************************************************************************/


package indiepantry.firstindiepantry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    public static final String EXTRA_ORDER_TOTAL = "CART_TOTAL";
    public static final String TAG = CartActivity.class.getSimpleName();

    ArrayList<item_display> alCartItems;
    double taxRate = 0.1;
    double subtotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        if (savedInstanceState != null)
            return;


        TextView placard = (TextView) findViewById(R.id.cartPagePlacard);
        placard.setText(SideData.getUsername() + "'s Cart");

        alCartItems = SideData.getCart();
        ListView lvCartItemList = (ListView) findViewById(R.id.lv_cartItemList);
        Log.i(TAG,"lvCartItemList assigned");
        CartItemListAdapter listAdapter = new CartItemListAdapter(this,alCartItems);
        Log.i(TAG,"listAdapter assigned"+listAdapter);
        listAdapter.cart = this;
        lvCartItemList.setAdapter(listAdapter);

        recalculate();

    }

    public void recalculate() {
        subtotal = 0;
        for (item_display i : alCartItems) {
            subtotal += i.getCost();
        }
        ((TextView) findViewById(R.id.tv_subtotal)).setText(String.format("$%.2f",subtotal));
        ((TextView) findViewById(R.id.tv_tax)).setText(String.format("$%.2f",subtotal*taxRate));
        ((TextView) findViewById(R.id.tv_total)).setText(String.format("$%.2f",subtotal+(subtotal*taxRate)));
        Log.i(TAG,"subtotal recalculated: "+subtotal);
    }

    /** Called when the user taps the Checkout button */
    public void proceedToCheckout(View view) {
        Intent intent = new Intent(this, PayPalActivity.class);
        Double orderTotal = subtotal;
        intent.putExtra(EXTRA_ORDER_TOTAL, orderTotal);
        startActivity(intent);
    }
}
