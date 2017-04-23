package indiepantry.firstindiepantry;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
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

public class VendorListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_list);
        TextView textView = (TextView) findViewById(R.id.textView5);
        textView.setText("Before query!");
        try{
            QueryClass queryClass = new QueryClass();
            queryClass.execute("http://env2.zs6znymmyc.us-east-1.elasticbeanstalk.com/api/vendors/1/?format=json");
        }finally{

        }
    }


    public class QueryClass extends AsyncTask<String, Void, String> {

        private ArrayList<Vendor> queryVendors;
        public StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected String doInBackground(String... params) {
            InputStream in = null;
            try{//From Layne's code
                in = openConnection(params[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String output;
                while ((output = br.readLine()) != null)
                    stringBuilder.append(output);
                in.close();
                return stringBuilder.toString();
            }catch(IOException e){
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView textView = (TextView) findViewById(R.id.textView5);
            textView.setText("PostExecute!");
            String new_string = "{\"vendorAddress\":\"555 Place Drive\",\"emailV\":\"jDoe@yahoo.com\",\"passwordV\":\"jDoePassword1\",\"phone\":\"555-555-5555\",\"rating\":\"3.5\",\"nameV\":\"John Doe\",\"approved\":true}";
            try{
                JSONArray myJson = new JSONArray(s);
                /*JSONObject nObj = new JSONObject(s);
                Vendor vendor = new Vendor(nObj.getString("nameV"), 0, Double.parseDouble(nObj.getString("rating")));
                queryVendors.add(vendor);
                */
                for(int i=0; i < myJson.length(); i++) {
                    JSONObject jObj = (JSONObject) myJson.get(i);
                    Vendor vendor = new Vendor(jObj.getString("nameV"), 0, Double.parseDouble(jObj.getString("rating")));
                    queryVendors.add(vendor);
                }
            }catch(JSONException e){
                textView.setText("Error" + s+ e.getMessage());
                return;
            }

            textView.setText(s);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.VendorListActivityLinearLayout);
            for(Vendor bob: queryVendors){
                LinearLayout horizontalLayout = new LinearLayout(VendorListActivity.this);
                horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
                Button vendor_button = new Button(VendorListActivity.this);
                vendor_button.setTag(bob);
                vendor_button.setText(bob.getName());
                vendor_button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Button button = (Button) v;
                        Vendor vendor = (Vendor) button.getTag();
                        Intent intent = new Intent(VendorListActivity.this,VendorPageActivity.class);
                        SideData.setTemp_vendor(vendor);
                        startActivity(intent);
                    }
                });
                horizontalLayout.addView(vendor_button);
                linearLayout.addView(horizontalLayout);
            }
        }

        //Borrowed from TutorialsPoint
        public InputStream openConnection(String strUrl){
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
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return in;
        }
    }



}
