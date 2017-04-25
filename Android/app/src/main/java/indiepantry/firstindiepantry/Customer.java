package indiepantry.firstindiepantry;

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
        return "{" +
                "        \"username\":\"" + username + "\"," +
                "        \"passwordC\": \"" + password + "\"," +
                "        \"nameC\": \"" + name + "\"," +
                "        \"customerAddress\": \"" + address +"\"," +
                "        \"emailC\": \"" + email+"\"" +
                "    }";
    }

    public String getName(){ return name; }


}
