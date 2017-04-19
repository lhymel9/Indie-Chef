/****************************************************************************************/
/*
/* FILE NAME: MainLogin.java
/*
/* DESCRIPTION:
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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainLogin extends AppCompatActivity {


    public static final String EXTRA_USER = "com.indiepantry.firstindiepantry.USER";
    public static final String EXTRA_PASS = "com.indiepantry.firstindiepantry.PASS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setVisibility(View.INVISIBLE);
        /* Ignore this bit, it is for testing queries only
        try{
            DjangoQueryClass.get_user();
        }catch(IOException e){
            e.getMessage();
        }
        */

    }



    public void login_attempt(View view){
        /*
         * Implement validation
         */
        int k = 0;
        TextView textView = (TextView) findViewById(R.id.textView3);
        if(textView.getVisibility() == View.VISIBLE){
            k = 1;
        }

        if(validate()){
            Intent intent = new Intent(this, HomeScreen.class);
            EditText editUser = (EditText) findViewById(R.id.editText);
            String username = editUser.getText().toString();
            SideData.setUsername(username);
            startActivity(intent);
        }
        else{
            if(k==1){
                //textView.setVisibility(View.INVISIBLE);
            }
            else{
                textView.setVisibility(View.VISIBLE);
            }
        }

    }

    protected boolean validate(){
        EditText editUser = (EditText) findViewById(R.id.editText);
        EditText editPass = (EditText) findViewById(R.id.editText2);
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
