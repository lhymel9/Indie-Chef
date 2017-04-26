/****************************************************************************************/
/*
/* FILE NAME: MainLogin.java
/*
/* DESCRIPTION: Covers authentication of username and password, and allows account creation
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/12/17 Maxwell Reeser       Created the class
/*
/*
/*
/*
/****************************************************************************************/


package indiepantry.firstindiepantry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainLogin extends AppCompatActivity {


    public static final String EXTRA_USER = "com.indiepantry.firstindiepantry.USER";
    public static final String EXTRA_PASS = "com.indiepantry.firstindiepantry.PASS";

    private ProgressDialog progressDialog;
    private Bitmap bitmap = null;
    Button b1;

    public static StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        final TextView textView = (TextView) findViewById(R.id.mainLoginInvalidUsernameAndOrPasswordText);
        textView.setVisibility(View.INVISIBLE);
        // Ignore this bit, it is for testing queries only

        Button loginButton = (Button) findViewById(R.id.mainLoginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View textView) {
                login_attempt(textView);
            }

        });

        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(TextView.INVISIBLE);
                Intent intent = new Intent(MainLogin.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });


    }



    public void login_attempt(View view){


        if(validate()){
            Intent intent = new Intent(this, HomeScreen.class);
            EditText editUser = (EditText) findViewById(R.id.mainLoginUsernameEditText);
            String username = editUser.getText().toString();
            SideData.setUsername(username);
            startActivity(intent);
        }
        else{
            TextView textView = (TextView) findViewById(R.id.mainLoginInvalidUsernameAndOrPasswordText);
            textView.setVisibility(View.VISIBLE);
        }

    }

    protected boolean validate(){
        EditText editUser = (EditText) findViewById(R.id.mainLoginUsernameEditText);
        EditText editPass = (EditText) findViewById(R.id.mainLoginPasswordEditText);
        String username = editUser.getText().toString();
        String password = editPass.getText().toString();
        if(username.equals("folshost") && password.equals("1234")){
            return true;
        }
        else{
            return false;
        }

    }




}
