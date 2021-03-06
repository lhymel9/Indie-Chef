/****************************************************************************************/
/*
/* FILE NAME: CreateNewVendorActivity.java
/*
/* DESCRIPTION: Activity class for handling creation of new vendor account
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/25/17 Maxwell Reeser       Created the class
/*
/*
/*
/*
/****************************************************************************************/

package indiepantry.firstindiepantry;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CreateNewVendorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_vendor);
        Vendor vendor = SideData.getTemp_vendor();
        try{
            CreateNewVendorActivity.QueryClass queryClass = new CreateNewVendorActivity.QueryClass();
            queryClass.execute(vendor.toJson());
        }finally{

        }


    }


    public class QueryClass extends AsyncTask<String, String, String> {

        public StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected String doInBackground(String... params) {
            OutputStream out = null;
            try{//From Layne's code
                out = openConnection("http://env2.zs6znymmyc.us-east-1.elasticbeanstalk.com/api/vendors/",params[0]);
                if(out != null) {
                    return "non_null";
                }else{
                    return "";
                }


            }finally{
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView textView = (TextView) findViewById(R.id.newVendorText);
            textView.setText(SideData.getReturnVal());

        }

        //Borrowed from TutorialsPoint -- Modified from VendorListActivity
        public OutputStream openConnection(String strUrl,String json){
            OutputStream out = null;
            int resCode = -1;

            try {
                URL bigUrl = new URL(strUrl);
                URLConnection urlConn = bigUrl.openConnection();

                if (!(urlConn instanceof HttpURLConnection)) {
                    throw new IOException("URL is not an Http URL");
                }

                HttpURLConnection httpConn = (HttpURLConnection) urlConn;
                httpConn.setConnectTimeout(15000);
                httpConn.setReadTimeout(15000);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setAllowUserInteraction(false);
                httpConn.setRequestMethod("POST");
                httpConn.setRequestProperty("Accept", "application/json");
                httpConn.setRequestProperty("Content-type","application/json");
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);
                out = httpConn.getOutputStream();
                if(out != null) {
                    BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                    if (br != null) {
                        br.write(json);
                        br.flush();
                        br.close();
                    }
                    out.close();
                }
                httpConn.connect();
                resCode = httpConn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    SideData.setReturnVal(Integer.toString(resCode));
                    out = httpConn.getOutputStream();
                }
                else{
                    SideData.setReturnVal(Integer.toString(httpConn.getResponseCode()) + "\n" + json);
                }
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return out;
        }
    }
}
