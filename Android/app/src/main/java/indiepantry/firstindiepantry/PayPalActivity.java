/****************************************************************************************/
/*
/* FILE NAME: PayPalActivity.java
/*
/* DESCRIPTION: Activity for user to submit an order through PayPal
/*
/* REFERENCE:
/*
/* DATE BY CHANGE         REF   DESCRIPTION
/* ======= ============== ===== =============
/* 4/25/17 Brandon Hollier      Created the class
/*
/*
/*
/*
/****************************************************************************************/

package indiepantry.firstindiepantry;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static indiepantry.firstindiepantry.CartActivity.*;

public class PayPalActivity extends AppCompatActivity {

    final static String TAG = "PayPalActivity";

    private WebView checkoutWebView;
    String checkoutURL;
    ProgressDialog dialog;
    String successURL;
    String cencelURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Double orderTotal = intent.getDoubleExtra(EXTRA_ORDER_TOTAL,0);


        Log.i(TAG,"created");
        // Generate PayPal Checkout URL
        String checkoutURL_base = "https://www.paypal.com/cgi-bin/webscr?cmd=_cart&upload=1&rm=2&return=success&cancel_return=cancelled&no_shipping=1";
        int invoice = 4123784;
        String vendorPayPal = "vendor@indiepantry.com";
        String itemName = "order";
        String itemPrice = "";
        double tax = orderTotal*0.1;
        String taxCart = String.format("%.2f", tax);
        StringBuilder builder = new StringBuilder();
        builder.append(checkoutURL_base);
        builder.append("&invoice=");
        builder.append(invoice);
        builder.append("&business=");
        builder.append(vendorPayPal);
        builder.append("&item_name_1=");
        builder.append(itemName);
        builder.append("&amount_1=");
        builder.append(orderTotal);
        builder.append("&tax_cart=");
        builder.append(taxCart);
        checkoutURL = builder.toString();

        loadWebView();
    }

    private void loadWebView() {
        Log.i(TAG,"Loading "+checkoutURL);

        checkoutWebView = (WebView) findViewById(R.id.checkoutWebView);
        checkoutWebView.loadUrl(checkoutURL);
        checkoutWebView.getSettings().setJavaScriptEnabled(true);

        checkoutWebView.setWebViewClient(new WebViewClient(){
            @Override
            @TargetApi(21)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                return shouldOverrideUrlLoading(view, uri);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                return shouldOverrideUrlLoading(view, uri);
            }

            public boolean shouldOverrideUrlLoading(WebView view, Uri uri) {
                // Check if a link being loaded is to PayPal
                if (uri.getHost().equals("www.paypal.com")) {
                    if (uri.getPath().equals("/webapps/success")) {
                        Log.i(TAG,"ORDER SUCCESS");
                        finish();
                        return true;
                    } else
                    if (uri.getPath().equals("/webapps/cancelled")) {
                        Log.i(TAG,"ORDER CANCELLED");
                        finish();
                        return true;
                    }
                    // Let WebView load page
                    Log.i(TAG,"Loading "+uri.getPath());
                    return false;
                }
                // Otherwise, launch new Activity
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && !checkoutWebView.canGoBack()) {
            Log.i(TAG,"ORDER CANCELLED");
        }
        return super.onKeyDown(keyCode, event);
    }
}
