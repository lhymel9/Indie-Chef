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

        Button submit_button = (Button) findViewById(R.id.createAccountSignUpButton);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVendor){
                    Intent intent = new Intent(CreateAccountActivity.this, CreateAccountActivity.class);
                    startActivity(intent);
                }
                else{
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
                EditText usernameEdit = (EditText) findViewById(R.id.createAccountUsernameEditText);
                if(!isVendor){
                    usernameTextview.setVisibility(TextView.INVISIBLE);
                    usernameEdit.setVisibility(EditText.INVISIBLE);
                    isVendor = true;
                } else{
                    usernameTextview.setVisibility(TextView.VISIBLE);
                    usernameEdit.setVisibility(EditText.VISIBLE);
                    isVendor = false;
                }

            }
        });



    }

    public void newCustomer(){

        EditText editName = (EditText) findViewById(R.id.createAccountNameEditText);
        EditText editPassword = (EditText) findViewById(R.id.createAccountPasswordEditText);
        EditText editEmail = (EditText) findViewById(R.id.createAccountEmailEditText);
        EditText editAddress = (EditText) findViewById(R.id.createAccountAddressEditText);
        EditText editUsername = (EditText) findViewById(R.id.createAccountUsernameEditText);
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
