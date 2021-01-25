package com.dicoding.mynewsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    private WebView webView;
    String data = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra("EXTRA_URL");

        setContentView(R.layout.activity_web);
        webView= findViewById(R.id.webView);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }

    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
