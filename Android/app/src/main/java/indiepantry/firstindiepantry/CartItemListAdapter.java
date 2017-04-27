/****************************************************************************************/
/*
/* FILE NAME: CartListAdapter.java
/*
/* DESCRIPTION: Activity class for handling looking at the cart
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/26/17 Brandon Hollier      Created the class
/*
/*
/*
/*
/****************************************************************************************/

package indiepantry.firstindiepantry;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartItemListAdapter extends ArrayAdapter<item_display> {

    double subtotal;
    public CartActivity cart;

    public static final String TAG = CartItemListAdapter.class.getSimpleName();
    ArrayList<item_display> list;

    public CartItemListAdapter(Context context, ArrayList<item_display> alCartItems) {
        super(context,R.layout.list_cart_item);
        list = alCartItems;
        Log.i(TAG,"created with arraylist: "+alCartItems.toString());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public item_display getItem(int i) {
        return list.get(i);
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart_item, parent, false);
            Log.i(TAG,"view==null");
        }
        Log.i(TAG,"getView: "+index+", "+view.toString()+", "+parent.toString());

        final item_display item = list.get(index);
        Log.i(TAG,"item: "+item.getName());
        // set cart item details
        TextView tvItemName = (TextView) view.findViewById(R.id.tv_vo_itemName);
        tvItemName.setText(item.getName()); // name
        TextView tvItemVendor = (TextView) view.findViewById(R.id.tv_itemVendor);
        tvItemVendor.setText(item.getVendor_name()); // vendor
        TextView tvItemPrice = (TextView) view.findViewById(R.id.tv_itemPrice);
        tvItemPrice.setText(String.format("$%.2f",item.getCost())); // price
        ImageButton btnDeleteItem = (ImageButton) view.findViewById(R.id.btn_itemDelete);
        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(item);
                notifyDataSetChanged();
                cart.recalculate();
                Toast.makeText(parent.getContext(),"Removed "+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}