/****************************************************************************************/
/*
/* FILE NAME: Item_Category.java
/*
/* DESCRIPTION: Class for managing an item category and its data
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/15/17 Maxwell Reeser       Created the class
/*
/*
/*
/****************************************************************************************/

package indiepantry.firstindiepantry;

/**
 * Created by Maxwell on 4/15/2017.
 */

public class Item_Category {
    private String categoryName;
    private Vendor vendor;

    public String getCategoryName(){
        return categoryName;
    }

    public Vendor getVendor(){
        return vendor;
    }

    public void setCategoryName(String n){
        categoryName = n;
    }

    public void setVendor(Vendor v){
        vendor = v;
    }

    public Item_Category(String n, Vendor v){
        categoryName = n;
        vendor = v;
    }


}
