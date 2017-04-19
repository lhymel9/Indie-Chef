/****************************************************************************************/
/*
/* FILE NAME: VendorPageActivity.java
/*
/* DESCRIPTION: Activity class for looking at a vendor's profile
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/15/17 Maxwell Reeser       Created the class
/*
/*
/*
/*
/****************************************************************************************/


package indiepantry.firstindiepantry;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VendorPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_page);

        TextView nameBox =  (TextView) findViewById(R.id.vendorPageNameBox);
        TextView descriptionBox = (TextView) findViewById(R.id.vendorPageDescriptionBox);
        Button ratingButton = (Button) findViewById(R.id.vendorPageRatingsButton);
        Button menuButton = (Button) findViewById(R.id.vendorPageMenuButton);
        ImageView profilePic = (ImageView) findViewById(R.id.vendorPageProfilePicture);

        Vendor vendor = SideData.getTemp_vendor();
        nameBox.setText(vendor.getName());
        ratingButton.setText(Double.toString(vendor.getRating()));



    }
}
