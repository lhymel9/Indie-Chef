package indiepantry.firstindiepantry;

import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class FindVendorNearYou_Activity extends AppCompatActivity {
    Vendor[] vendors = new Vendor[]{new Vendor("Bob Smith", "11035 N. Shoreline Ave.", "Balls@gmail.com"),
            new Vendor("Kate Winslett", "3000 July St.", "ihatekate@yahoo.com"),
            new Vendor("George Clooney", "1600 Pennsylvania Ave.", "imgeorgeclooney@aol.com")};
    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.vendorList);
    Location myLocation = new Location(LocationManager.GPS_PROVIDER);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_vendor_near_you);

        vendors[0].getLocation().setLatitude(23.4);
        vendors[0].getLocation().setLongitude(33);
        vendors[1].getLocation().setLatitude(30.1);
        vendors[1].getLocation().setLongitude(12.3);
        vendors[2].getLocation().setLatitude(42.3);
        vendors[2].getLocation().setLongitude(53.2);

        vendors = sortVendors(vendors);

        for (Vendor vendor : vendors) {
            Button newButton = new Button(this);
            newButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    //startActivity(new Intent(FindVendorNearYou_Activity.this, <INSERT VENDOR MENU ACTIVITY HERE>));
                }
            });
            newButton.setText(vendor.getName()
                    + " Rating: " + vendor.getRating()
                    + "Distance: " + myLocation.distanceTo(vendor.getLocation()));
            linearLayout.addView(newButton);
        }
    }

    public Vendor[] sortVendors(Vendor[] vendors){
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
