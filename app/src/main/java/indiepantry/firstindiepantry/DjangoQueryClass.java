/****************************************************************************************/
/*
/* FILE NAME: DjangoQueryClass.java
/*
/* DESCRIPTION: Contains methods for sending requests to django db
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/18/17 Maxwell Reeser       Created the class
/*
/*
/*
/*
/****************************************************************************************/


package indiepantry.firstindiepantry;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Maxwell on 4/18/2017.
 */

public class DjangoQueryClass {

    private final static String USER_AGENT = "Mozilla/5.0";

    public static void get_user() throws  IOException{

        String url = "http://env2.zs6znymmyc.us-east-1.elasticbeanstalk.com/api/customers/2/?format=json";

        URL obj = new URL(url);

        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        con.connect();
        /*
        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        */

    }


    public static void post_new_user() throws IOException {

        String url =
                "http://env2.zs6znymmyc.us-east-1.elasticbeanstalk.com/api/customers/";

        URL url_obj = new URL(url);
        HttpsURLConnection django_api = (HttpsURLConnection) url_obj.openConnection();

        django_api.setRequestMethod("POST");
        django_api.setRequestProperty("User-Agent","Mozilla/5.0");
        django_api.setRequestProperty("Accept-Language","en-US,en;q=0.5");

        String urlParameters = "{" +
                "    \"username\": \"Bob Smitty\"," +
                "    \"passwordC\": \"bad_password\"," +
                "    \"nameC\": \"Schmitty Wergenmanjansen\"," +
                "    \"latC\": 18.0," +
                "    \"lonC\": 76.7," +
                "    \"emailC\": \"schmitty@schmitty.com\"" +
                "}";

        django_api.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(django_api.getOutputStream());
        outputStream.writeBytes(urlParameters);
        outputStream.flush();
        outputStream.close();

        int responsecode = django_api.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(django_api.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());



    }


}
