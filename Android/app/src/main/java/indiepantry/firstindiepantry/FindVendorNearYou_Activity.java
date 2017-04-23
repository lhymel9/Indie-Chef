package indiepantry.firstindiepantry;

import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

public class FindVendorNearYou_Activity extends AppCompatActivity {
    Vendor[] vendors = new Vendor[]{new Vendor("Bob Smith", "11035 N. Shoreline Ave.", "Balls@gmail.com"),
            new Vendor("Kate Winslett", "3000 July St.", "ihatekate@yahoo.com"),
            new Vendor("George Clooney", "1600 Pennsylvania Ave.", "imgeorgecloone@aol.com"};

    ViewGroup linearLayout = (ViewGroup) findViewById(R.id.vendorList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_vendor_near_you);

        

        vendors = sortVendors(vendors);

        for(int i = 0; i < vendors.length; i++){
            Button newButton = new Button(this);
            newButton.setText(vendors[i].getName() + " Rating: " + vendors[i].getRating());
            linearLayout.addView(newButton);
        }
    }

    public Vendor[] sortVendors(Vendor[] vendors){
        Location myLocation = new Location(LocationManager.GPS_PROVIDER);
        Vendor temp;
        float distance;
        for(int i = 0; i < vendors.length - 1; i++){
            for(int j = i + 1; j < vendors.length; j++){
                distance = myLocation.distanceTo(vendors[i].getLocation());
                if(distance < myLocation.distanceTo(vendors[j].getLocation())){
                    temp = vendors[j];
                    vendors[j] = vendors[i];
                    vendors[i] = temp;
                }
            }
        }
        return vendors;
    }
}
