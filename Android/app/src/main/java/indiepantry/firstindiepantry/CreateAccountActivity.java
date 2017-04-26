/****************************************************************************************/
/*
/* FILE NAME: CreateAccountActivity.java
/*
/* DESCRIPTION: Activity to handle creating new user login
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/24/17 Maxwell Reeser       Created the class
/*
/*
/*
/*
/****************************************************************************************/


package indiepantry.firstindiepantry;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity {

    private boolean isVendor = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        final EditText editCustomerName = (EditText) findViewById(R.id.createCustomerAccountNameEditText);
        final EditText editCustomerPassword = (EditText) findViewById(R.id.createCustomerAccountPasswordEditText);
        final EditText editCustomerEmail = (EditText) findViewById(R.id.createCustomerAccountEmailEditText);
        final EditText editCustomerAddress = (EditText) findViewById(R.id.createCustomerAccountAddressEditText);
        final EditText editCustomerUsername = (EditText) findViewById(R.id.createCustomerAccountUsernameEditText);

        final EditText editVendorName = (EditText) findViewById(R.id.createVendorAccountNameEditText);
        final EditText editVendorPassword = (EditText) findViewById(R.id.createVendorAccountPasswordEditText);
        final EditText editVendorEmail = (EditText) findViewById(R.id.createVendorAccountEmailEditText);
        final EditText editVendorAddress = (EditText) findViewById(R.id.createVendorAccountAddressEditText);
        final EditText editVendorPhone = (EditText) findViewById(R.id.createVendorAccountPhoneEditText);

        final TextView vendorPhone = (TextView) findViewById(R.id.createVendorAccountPhoneNumberText);

        Button submit_button = (Button) findViewById(R.id.createAccountSignUpButton);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVendor){
                    String name = editVendorName.getText().toString();
                    String password = editVendorPassword.getText().toString();
                    String email = editVendorEmail.getText().toString();
                    String address = editVendorAddress.getText().toString();
                    String phone = editVendorPhone.getText().toString();

                    Vendor vendor = new Vendor(name, email, "1");
                    SideData.setTemp_vendor(vendor);
                    Intent intent = new Intent(CreateAccountActivity.this, CreateNewVendorActivity.class);
                    startActivity(intent);
                }
                else{
                    String name = editCustomerName.getText().toString();
                    String password = editCustomerPassword.getText().toString();
                    String email = editCustomerEmail.getText().toString();
                    String address = editCustomerAddress.getText().toString();
                    String username = editCustomerUsername.getText().toString();

                    Customer newCustomer = new Customer(username, password, name, address, email);
                    SideData.setCustomer(newCustomer);
                    Intent intent = new Intent(CreateAccountActivity.this, CreateNewCustomerActivity.class);
                    startActivity(intent);
                }
            }
        });

        final CheckBox isVendorApp = (CheckBox) findViewById(R.id.vendorApplicationCheckBox);

        isVendorApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView usernameTextview = (TextView) findViewById(R.id.createAccountUsernameText);
                EditText usernameEdit = (EditText) findViewById(R.id.createCustomerAccountUsernameEditText);
                if(!isVendor){
                    usernameTextview.setVisibility(TextView.INVISIBLE);
                    usernameEdit.setVisibility(EditText.INVISIBLE);

                    editCustomerAddress.setVisibility(EditText.INVISIBLE);
                    editCustomerEmail.setVisibility(EditText.INVISIBLE);
                    editCustomerName.setVisibility(EditText.INVISIBLE);
                    editCustomerPassword.setVisibility(EditText.INVISIBLE);
                    editCustomerUsername.setVisibility(EditText.INVISIBLE);

                    editVendorAddress.setVisibility(TextView.VISIBLE);
                    editVendorEmail.setVisibility(TextView.VISIBLE);
                    editVendorName.setVisibility(TextView.VISIBLE);
                    editVendorPassword.setVisibility(TextView.VISIBLE);
                    editVendorPhone.setVisibility(TextView.VISIBLE);
                    vendorPhone.setVisibility(TextView.VISIBLE);



                    isVendor = true;

                } else{
                    editCustomerAddress.setVisibility(EditText.VISIBLE);
                    editCustomerEmail.setVisibility(EditText.VISIBLE);
                    editCustomerName.setVisibility(EditText.VISIBLE);
                    editCustomerPassword.setVisibility(EditText.VISIBLE);
                    editCustomerUsername.setVisibility(EditText.VISIBLE);

                    editVendorAddress.setVisibility(TextView.INVISIBLE);
                    editVendorEmail.setVisibility(TextView.INVISIBLE);
                    editVendorName.setVisibility(TextView.INVISIBLE);
                    editVendorPassword.setVisibility(TextView.INVISIBLE);
                    editVendorPhone.setVisibility(TextView.INVISIBLE);
                    vendorPhone.setVisibility(TextView.INVISIBLE);

                    usernameTextview.setVisibility(TextView.VISIBLE);
                    usernameEdit.setVisibility(EditText.VISIBLE);
                    isVendor = false;
                }

            }
        });



    }

    public void newCustomer(){

        EditText editName = (EditText) findViewById(R.id.createCustomerAccountNameEditText);
        EditText editPassword = (EditText) findViewById(R.id.createCustomerAccountPasswordEditText);
        EditText editEmail = (EditText) findViewById(R.id.createCustomerAccountEmailEditText);
        EditText editAddress = (EditText) findViewById(R.id.createCustomerAccountAddressEditText);
        EditText editUsername = (EditText) findViewById(R.id.createCustomerAccountUsernameEditText);
        String name = editName.getText().toString();
        String password = editPassword.getText().toString();
        String email = editEmail.getText().toString();
        String address = editAddress.getText().toString();
        String username = editUsername.getText().toString();

    }


    public void newVendor(){



    }







    public class QueryClass extends AsyncTask<String, Void, String> {

        private ArrayList<Vendor> queryVendors = new ArrayList<>();
        public StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected String doInBackground(String... params) {
            OutputStream out = null;
            try{//From Layne's code
                out = openConnection(params[0]);
                BufferedOutputStream br = (BufferedOutputStream) out;

                br.write(params[0].getBytes());
                out.close();
                return stringBuilder.toString();
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
