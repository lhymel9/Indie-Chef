package indiepantry.firstindiepantry;

/**
 * Created by Maxwell on 4/15/2017.
 */

public class Vendor {
    private String name;
    private int id;
    private double rating;

    public void setName(String n){ name = n;}

    public void setId(int i){ id = i;}

    public void setRating(double r){ rating = r;}

    public String getName(){ return name;}

    public int getId(){ return id;}

    public double getRating(){ return rating;}

    public Vendor(String n, int i, double r){
        name = n;
        id = i;
        rating = r;
    }

}
