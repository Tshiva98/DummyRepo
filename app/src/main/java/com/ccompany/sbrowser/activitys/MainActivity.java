package com.ccompany.sbrowser.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ccompany.sbrowser.R;
import com.ccompany.sbrowser.activitys.database.HistoryDao;
import com.ccompany.sbrowser.activitys.database.HistoryDatabase;
import com.ccompany.sbrowser.activitys.database.HistoryEntity;
import com.ccompany.sbrowser.activitys.pojoclass.PojoHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView erase_link;
    EditText input_url;
    ProgressBar progress_bar;
    WebView web_view;

    ImageView reload_page, home_page, forward_page, history_page;

    List<HistoryEntity> historyEntity;
    List<PojoHistory> pojoHistories;
    List<HistoryActivity> createNewLine;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        erase_link = findViewById(R.id.erase_link);
        input_url = findViewById(R.id.input_url);
        progress_bar = findViewById(R.id.progress_bar);
        web_view = findViewById(R.id.web_view);
        reload_page = findViewById(R.id.reload_page);
        home_page = findViewById(R.id.home_page);
        forward_page = findViewById(R.id.forward_page);
        history_page = findViewById(R.id.history_page);





        WebSettings webSettings = web_view.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        web_view.setWebViewClient(new MywebViewClient());
        web_view.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progress_bar.setProgress(newProgress);
            }
        });



        loadMyUrl("https://api-t1.fyers.in/api/v3/generate-authcode?client_id=3U6Z5D98Q0-102&redirect_uri=https://trade.talkoptions.in/&response_type=code&state=ZAU707949");

        input_url.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO || i == EditorInfo.IME_ACTION_DONE){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(input_url.getWindowToken(),0);
                    loadMyUrl(input_url.getText().toString());
                    return true;
                }
                return false;
            }
        });

        erase_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_url.setText("");
            }
        });

        reload_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web_view.reload();
            }
        });

        home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web_view.loadUrl("google.com");
            }
        });

        forward_page.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                if (web_view.canGoForward())
                web_view.goForward();
            }
        });

        history_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);



            }
        });
    }

    private void loadMyUrl(String url) {

        boolean matchUrl = Patterns.WEB_URL.matcher(url).matches();
        if (matchUrl){
            web_view.loadUrl(url);
        }else {
            web_view.loadUrl("www.google.com/search?q="+url);
        }

        HistoryDatabase database = Room.databaseBuilder(getApplicationContext(),HistoryDatabase.class,"historyURL")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        HistoryDao historyDao = database.historyDao();

        Intent intentM = getIntent();

        int id = intentM.getIntExtra("id",0);
        String urll = web_view.getUrl().toString();

        Date dateandtime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh/mm/ss", Locale.getDefault());
        String date = dateFormat.format(dateandtime);
        String time = timeFormat.format(dateandtime);


        HistoryEntity historyEntity = new HistoryEntity(id,urll,date,time);

        historyDao.insert(historyEntity);

        Toast.makeText(getApplicationContext(), "Added to History Succssful", Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onBackPressed() {

        if (web_view.canGoBack()){
            web_view.goBack();
        }else {
            super.onBackPressed();
        }

    }

    private class MywebViewClient extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            input_url.setText(web_view.getUrl());
            progress_bar.setVisibility(View.VISIBLE);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progress_bar.setVisibility(View.INVISIBLE);
        }
    }
}