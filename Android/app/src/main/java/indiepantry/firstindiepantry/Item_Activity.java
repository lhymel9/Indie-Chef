/****************************************************************************************/
/*
/* FILE NAME: HomeScreen.java
/*
/* DESCRIPTION: Activity for displaying specific item, and allowing it to be added to cart
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/14/17 Maxwell Reeser       Created the class
/*
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
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import static indiepantry.firstindiepantry.R.id.imageButton;

public class Item_Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_);

        Intent intent = getIntent();
        String item_name = intent.getStringExtra(HomeScreen.EXTRA_ITEM_DISPLAY);

        item_display item = SideData.getTemp_item();

        TextView display_name = (TextView) findViewById(R.id.textView4);
        TextView display_rating = (TextView) findViewById(R.id.textView7);
        TextView display_description = (TextView) findViewById(R.id.textView6);
        final TextView added_to_cart = (TextView) findViewById(R.id.itemAddedToCartText);
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        Button purchase_button = (Button) findViewById(R.id.button2);

        added_to_cart.setVisibility(TextView.INVISIBLE);

        display_name.setText(item.getName());
        display_rating.setText("1" + "/5");
        display_description.setText(item.getDescription());
        purchase_button.setText("Purchase for $" + Double.toString(item.getCost()));
        purchase_button.setTag(item);
        purchase_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SideData.addToCart((item_display)v.getTag());
                added_to_cart.setVisibility(TextView.VISIBLE);
            }
        });


    }

    @Override
    public void onClick(View v) {
        SideData.addToCart((item_display)v.getTag());
    }
}
