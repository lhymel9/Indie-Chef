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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    public static final String EXTRA_ORDER_TOTAL = "CART_TOTAL";
    public static final String TAG = CartActivity.class.getSimpleName();

    ArrayList<item_display> alCartItems;

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
        lvCartItemList.setAdapter(listAdapter);
    }

    /** Called when the user taps the Checkout button */
    public void proceedToCheckout(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        Double orderTotal = 4.95;
        intent.putExtra(EXTRA_ORDER_TOTAL, orderTotal);
        startActivity(intent);
    }
}
