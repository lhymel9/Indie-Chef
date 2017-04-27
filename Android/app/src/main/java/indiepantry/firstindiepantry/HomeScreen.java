/****************************************************************************************/
/*
/* FILE NAME: HomeScreen.java
/*
/* DESCRIPTION: Home screen for customer user, shows nearby offerings, links to cart
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/12/17 Maxwell Reeser       Created the class
/*
/*
/*
/*
/****************************************************************************************/


package indiepantry.firstindiepantry;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements  View.OnClickListener{


    public static final String EXTRA_ITEM_DISPLAY = "com.indiepantry.firstindiepantry";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Intent intent = getIntent();

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("Hello " + SideData.getUsername() + "!");

        SideData.initCart();
        Button vendorListButton = (Button) findViewById(R.id.VendorListButton);
        vendorListButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, FindVendorNearYou_Activity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onClick(View v_button) {
        Button button = (Button) v_button;
        item_display bob = (item_display) button.getTag();
        Intent intent = new Intent(this, Item_Activity.class);
        SideData.setTemp_item(bob);
        startActivity(intent);
    }


    @Override
    protected void onStart(){
        super.onStart();

        Button cart_button = (Button) findViewById(R.id.cartButton);
        cart_button.setText("Cart(" + SideData.getCartSize() + " items)");
        cart_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, CartActivity.class);
                startActivity(intent);
            }
        });

        Vendor vendor1 = new Vendor("Bob Smith", "House Place", "bob@smith.com");
        Vendor vendor2 = new Vendor("Kate Winslett", "Non-Home place", "kate@winslett.com");
        Vendor vendor3 = new Vendor("George Clooney", "Place","george@clooney.com");

        Item_Category cat1 = new Item_Category("Jellies", vendor1);
        Item_Category cat2 = new Item_Category("Cookies", vendor2);
        Item_Category cat3 = new Item_Category("Breads", vendor3);


        //Get list of menu items from user's location
        ArrayList<item_display> items = new ArrayList<>();
        items.add(new item_display("Red Pepper Jelly",12.45, "A jelly made from red pepper", cat1));
        items.add(new item_display("Cookie Platter", 8.95, "A platter of assorted cookies",cat2));
        items.add(new item_display("Sourdough Loaf", 4.99,"A loaf of plain sourdough bread, baked fresh", cat3));
        items.add(new item_display("Red Pepper Jelly",12.45, "A jelly made from red pepper", cat1));
        items.add(new item_display("Cookie Platter", 8.95, "A platter of assorted cookies",cat2));
        items.add(new item_display("Sourdough Loaf", 4.99,"A loaf of plain sourdough bread, baked fresh", cat3));
        items.add(new item_display("Red Pepper Jelly",12.45, "A jelly made from red pepper", cat1));
        items.add(new item_display("Cookie Platter", 8.95, "A platter of assorted cookies",cat2));
        items.add(new item_display("Sourdough Loaf", 4.99,"A loaf of plain sourdough bread, baked fresh", cat3));
        items.add(new item_display("Red Pepper Jelly",12.45, "A jelly made from red pepper", cat1));
        items.add(new item_display("Cookie Platter", 8.95, "A platter of assorted cookies",cat2));
        items.add(new item_display("Sourdough Loaf", 4.99,"A loaf of plain sourdough bread, baked fresh", cat3));
        items.add(new item_display("Red Pepper Jelly",12.45, "A jelly made from red pepper", cat1));
        items.add(new item_display("Cookie Platter", 8.95, "A platter of assorted cookies",cat2));
        items.add(new item_display("Sourdough Loaf", 4.99,"A loaf of plain sourdough bread, baked fresh", cat3));
        items.add(new item_display("Red Pepper Jelly",12.45, "A jelly made from red pepper", cat1));
        items.add(new item_display("Cookie Platter", 8.95, "A platter of assorted cookies",cat2));
        items.add(new item_display("Sourdough Loaf", 4.99,"A loaf of plain sourdough bread, baked fresh", cat3));
        items.add(new item_display("Red Pepper Jelly",12.45, "A jelly made from red pepper", cat1));
        items.add(new item_display("Cookie Platter", 8.95, "A platter of assorted cookies",cat2));
        items.add(new item_display("Sourdough Loaf", 4.99,"A loaf of plain sourdough bread, baked fresh", cat3));



        ScrollView available_items = (ScrollView) findViewById(R.id.menu_items_nearby);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout1);
        for(item_display bob: items){
            LinearLayout horizontalLayout = new LinearLayout(this);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout verticalLayout = new LinearLayout(this);
            verticalLayout.setOrientation(LinearLayout.VERTICAL);
            int scale = Math.round(getApplicationContext().getResources().getDisplayMetrics().density);

            Button item_button = new Button(this);
            item_button.setTag(bob);
            item_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    item_display bob = (item_display) button.getTag();
                    Intent intent = new Intent(HomeScreen.this, Item_Activity.class);
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
                    Intent intent = new Intent(HomeScreen.this,VendorPageActivity.class);
                    SideData.setTemp_vendor(vendor);
                    startActivity(intent);
                }
            });

            ImageView thumb = new ImageView(this);
            thumb.setImageResource(R.drawable.ic_rbmq4ipuhk);

            verticalLayout.addView(item_button);
            verticalLayout.addView(vendor_button);
            horizontalLayout.addView(thumb);
            horizontalLayout.addView(verticalLayout);

            linearLayout.addView(horizontalLayout);

            thumb.getLayoutParams().width = 80*scale;
            thumb.getLayoutParams().height = 80*scale;

        }
    }

}
