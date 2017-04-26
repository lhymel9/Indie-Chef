/****************************************************************************************/
/*
/* FILE NAME: FindVendorNearYou_Activity.java
/*
/* DESCRIPTION: Activity class for handling looking at the cart
/*
/* REFERENCE:
/*
/* DATE BY CHANGE            REF   DESCRIPTION
/* ======= ================ ===== =============
/* 4/23/17 Jacob Guglielmo        Created the class
/* 4/26/17 Maxwell Reeser         Connected to rest of GUI
/*
/*
/*
/****************************************************************************************/

package indiepantry.firstindiepantry;



import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class FindVendorNearYou_Activity extends AppCompatActivity {
    ArrayList<Vendor> vendors = new ArrayList<>();
    ArrayList<Location> locations = new ArrayList<>();
    LinearLayout linearLayout = null;
    Location myLocation = new Location(LocationManager.GPS_PROVIDER);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myLocation.setLatitude(30.45);
        myLocation.setLongitude(91.22);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_vendor_near_you);
        linearLayout = (LinearLayout) findViewById(R.id.vendorList);
        createVendorsArrayList();
        createLocationsArrayList();
        if(vendors.size() == 0){

            //textView.setText("None");
        }
        sortVendors();

        for (int i = 0; i < vendors.size(); i++) {
            Button newButton = new Button(this);
            newButton.setTag(vendors.get(i));
            newButton.setText(vendors.get(i).getName()
                    + " Rating: " + vendors.get(i).getRating()
                    + "Distance: " + myLocation.distanceTo(locations.get(i)));
            newButton.setVisibility(Button.VISIBLE);
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    Vendor vendor = (Vendor) button.getTag();
                    Intent intent = new Intent(FindVendorNearYou_Activity.this, VendorPageActivity.class);
                    SideData.setTemp_vendor(vendor);
                    startActivity(intent);
                }
            });

            linearLayout.addView(newButton);
        }
    }

    public void createLocationsArrayList() {
        double lat, lon;
        Location tempL;
        for (Vendor vendor : vendors) {
            lat = Double.parseDouble(vendor.getLat());
            lon = Double.parseDouble(vendor.getLon());
            tempL = new Location("");
            tempL.setLongitude(lon);
            tempL.setLatitude(lat);
            locations.add(tempL);
        }
    }

    public void sortVendors() {
        Vendor temp;
        Location tempL;
        float distance;
        for (int i = 0; i < vendors.size() - 1; i++) {
            for (int j = i + 1; j < vendors.size(); j++) {
                distance = myLocation.distanceTo(locations.get(i));
                if (distance < myLocation.distanceTo(locations.get(j))) {
                    temp = vendors.get(j);
                    vendors.set(j, vendors.get(i));
                    vendors.set(i, temp);
                    tempL = locations.get(j);
                    locations.set(j, locations.get(i));
                    locations.set(i, tempL);
                }
            }
        }
    }

    public void createVendorsArrayList() {
        try {
            FindVendorNearYou_Activity.QueryClass queryClass = new QueryClass();
            queryClass.execute("http://env2.zs6znymmyc.us-east-1.elasticbeanstalk.com/api/vendors/?format=json");
        } finally {

        }
    }

    private class QueryClass extends AsyncTask<String, Void, String> {
        private StringBuilder stringBuilder = new StringBuilder();

        protected String doInBackground(String... params) {
            InputStream in = null;
            try {//From Layne's code
                in = openConnection(params[0]);

                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String output;
                while ((output = br.readLine()) != null)
                    stringBuilder.append(output);
                in.close();
                return stringBuilder.toString();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //textView.setText(s);
            if (s.substring(0, 4).contains("[")) {
                try {
                    org.json.JSONArray myJson = new org.json.JSONArray(s);
                    for (int i = 0; i < myJson.length(); i++) {
                        JSONObject jObj = (JSONObject) myJson.get(i);
                        Vendor vendor = new Vendor(jObj.getString("nameV"), jObj.getString("emailV"), jObj.getString("paypal"));
                        //vendor.setLat(jObj.getString("latV"));
                        //vendor.setLon(jObj.getString("lonV"));
                        vendor.setRating(jObj.getString("rating"));
                        vendor.setPhone(jObj.getString("phone"));
                        vendor.setPassword(jObj.getString("passwordV"));
                        vendor.setApproved(jObj.getBoolean("approved"));
                        vendors.add(vendor);
                    }
                } catch (JSONException e) {
                    return;
                }
            } else {
                try {
                    JSONObject jObj = new JSONObject(s);
                    Vendor vendor = new Vendor(jObj.getString("nameV"), jObj.getString("vendorAddress"), jObj.getString("emailV"));
                    vendors.add(vendor);
                } catch (JSONException e) {
                    return;
                }
            }
            createLocationsArrayList();

            sortVendors();

            for (int i = 0; i < vendors.size(); i++) {
                Button newButton = new Button(FindVendorNearYou_Activity.this);
                newButton.setTag(vendors.get(i));
                newButton.setText(vendors.get(i).getName()
                        + " Rating: " + vendors.get(i).getRating()
                        + "\nDistance (km): " + myLocation.distanceTo(locations.get(i))/1000);
                newButton.setVisibility(Button.VISIBLE);
                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button button = (Button) v;
                        Vendor vendor = (Vendor) button.getTag();
                        Intent intent = new Intent(FindVendorNearYou_Activity.this, VendorPageActivity.class);
                        SideData.setTemp_vendor(vendor);
                        startActivity(intent);
                    }
                });

                linearLayout.addView(newButton);
            }
        }

        //Borrowed from TutorialsPoint
        private InputStream openConnection(String strUrl) {
            InputStream in = null;
            int resCode = -1;

            try {
                URL bigUrl = new URL(strUrl);
                URLConnection urlConn = bigUrl.openConnection();

                if (!(urlConn instanceof HttpURLConnection)) {
                    throw new IOException("URL is not an Http URL");
                }

                HttpURLConnection httpConn = (HttpURLConnection) urlConn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.setRequestProperty("Accept", "application/json");
                httpConn.connect();
                resCode = httpConn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    in = httpConn.getInputStream();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return in;
        }
    }
}
