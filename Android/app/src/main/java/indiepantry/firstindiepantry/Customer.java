/****************************************************************************************/
/*
/* FILE NAME: Customer.java
/*
/* DESCRIPTION: Class for storing information on a customer, as for login or account creation
/*      purpose
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/24/17 Maxwell Reeser       Created the class
/*
/*
/*
/****************************************************************************************/

package indiepantry.firstindiepantry;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maxwell on 4/24/2017.
 */

public class Customer {
    private String name;
    private String password;
    private String email;
    private String address;
    private String username;


    public Customer(String u, String p, String n, String a, String e){
        username = u;
        password = p;
        name = n;
        address = a;
        email = e;
    }

    public String toJson(){
        try{
            JSONObject json = new JSONObject();
            json.put("username",username);
            json.put("passwordC",password);
            json.put("nameC", name);
            json.put("latC",45.67);
            json.put("lonC", -75.00);
            json.put("emailC",email);
            return json.toString();
        }catch(JSONException e){
            e.getMessage();
            return "-1";
        }

    }

    public String getName(){ return name; }


}
