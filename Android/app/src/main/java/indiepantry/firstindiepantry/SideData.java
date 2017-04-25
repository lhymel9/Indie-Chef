/****************************************************************************************/
/*
/* FILE NAME: SideData.java
/*
/* DESCRIPTION: Referenceable exterior data store, as views can only pass references via
/*      tags. Used for Item_Activity.java, CartActivity.java, and HomeScreen.java
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

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;



public class SideData {

    private static item_display temp_item;

    private static ArrayList<item_display> cart;

    private static ArrayList<Vendor> vendors;

    private static Vendor temp_vendor;

    private static String username;

    public static String returnVal;  //For use in DjangoQueryClass?

    public static Button textView;

    public static Customer customer;



    public static void setCustomer(Customer c){ customer =c;}

    public static void setReturnVal(String r){ returnVal = r;}

    public static void setUsername(String u){ username = u;}

    public static void setVendors(ArrayList<Vendor> vens){ vendors = vens;}

    public static void setTemp_vendor(Vendor vendor){ temp_vendor = vendor;}

    public static Vendor getTemp_vendor(){ return temp_vendor;}

    public static void setTemp_item(item_display item){
        temp_item = item;
    }

    public static void addToCart(item_display item){
        cart.add(item);
    }

    public static void addToVendors(Vendor ven){ vendors.add(ven);}

    public static item_display getTemp_item(){
        return temp_item;
    }

    public static int getCartSize(){
        return cart.size();
    }

    public static void initCart(){
        cart = new ArrayList<>();
    }

    public static ArrayList<item_display> getCart(){
        return cart;
    }

    public static String getUsername(){ return username;}

    public static ArrayList<Vendor> getVendors(){ return vendors;}

    public static String getReturnVal(){ return returnVal;}

    public static Customer getCustomer(){ return customer; }

}
