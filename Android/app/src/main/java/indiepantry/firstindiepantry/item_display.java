/****************************************************************************************/
/*
/* FILE NAME: item_display.java
/*
/* DESCRIPTION: Class for managing data about items
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/13/17 Maxwell Reeser       Created the class
/*
/*
/*
/****************************************************************************************/

package indiepantry.firstindiepantry;

/**
 * Created by Maxwell on 4/13/2017.
 */


//This class is used to display an item in Item_Activity
public class item_display {
    private String name;
    private double cost;
    private String description;
    private Item_Category category;
    private Vendor vendor;

    public item_display(String n, double c, String d, Item_Category cat){
        name = n;
        cost = c;
        description = d;
        category = cat;
        vendor = cat.getVendor();
    }


    public String getName(){
        return name;
    }

    public double getCost(){
        return cost;
    }

    public String getDescription(){ return description;}

    public String getVendor_name(){ return vendor.getName();}

    public Vendor getVendor(){ return vendor;}


}
