package indiepantry.firstindiepantry;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CreateNewCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_customer);
        Customer customer = SideData.getCustomer();
        try{
            CreateNewCustomerActivity.QueryClass queryClass = new CreateNewCustomerActivity.QueryClass();
            queryClass.execute(customer.toJson());
        }finally{

        }


    }


    public class QueryClass extends AsyncTask<String, String, String> {

        private ArrayList<Vendor> queryVendors = new ArrayList<>();
        public StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected String doInBackground(String... params) {
            OutputStream out = null;
            try{//From Layne's code
                out = openConnection("http://env2.zs6znymmyc.us-east-1.elasticbeanstalk.com/api/customers/");
                if(out == null) {
                    out.flush();
                    BufferedOutputStream br = (BufferedOutputStream) out;
                    String thing = "{\"username\": \"JohnDoe1337\",\"passwordC\": \"jdoepassword\",\"nameC\": \"John Doe\",\"customerAddress\": \"555 Place Drive\",\"emailC\": \"example1@yahoo.com\"}";
                    byte[] bytes = thing.getBytes();
                    if (br != null) {
                        br.write(bytes);
                        br.flush();
                        br.close();

                    }
                    out.close();
                }
                return "";
            }catch(IOException e){
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }

        //Borrowed from TutorialsPoint -- Modified from VendorListActivity
        public OutputStream openConnection(String strUrl){
            OutputStream out = null;
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
                httpConn.setRequestMethod("POST");

                httpConn.setRequestProperty("Accept", "application/json");
                httpConn.setRequestProperty("Content-type","application/json");
                httpConn.connect();
                resCode = httpConn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    out = httpConn.getOutputStream();
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
