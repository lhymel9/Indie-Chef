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

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        final TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setVisibility(View.INVISIBLE);
        // Ignore this bit, it is for testing queries only

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View textView) {
                login_attempt(textView);
            }

        });


        String output = "";

        textView.setText(output);



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

    private void downloadImage(String urlStr,final TextView v) {
        //progressDialog = ProgressDialog.show(this, "", "Downloading Image from " + urlStr);
        final String url = urlStr;

        new Thread() {
            public void run() {
                InputStream in = null;

                Message msg = Message.obtain();
                msg.what = 1;

                try {
                    //
                    in = openHttpConnection("http://www.google.com");

                    /*
                    bitmap = BitmapFactory.decodeStream(in);
                    Bundle b = new Bundle();
                    b.putParcelable("bitmap", bitmap);
                    msg.setData(b);
                    */
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    v.setText("After buffer!");
                    String output;
                    /*
                    while ((output = br.readLine()) != null)
                        stringBuilder.append(output);
                    //v.setText(stringBuilder.toString());
                    */
                    in.close();
                }catch (IOException e1) {
                    e1.printStackTrace();
                }
                messageHandler.sendMessage(msg);
            }
        }.start();
    }

    private InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode = -1;

        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
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

    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ImageView img = (ImageView) findViewById(R.id.imageView3);
            img.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap")));
            progressDialog.dismiss();
        }
    };


}
