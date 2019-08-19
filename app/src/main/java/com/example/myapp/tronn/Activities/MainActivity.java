package com.example.myapp.tronn.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp.tronn.Adapter.ImageAdapter;
import com.example.myapp.tronn.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.example.myapp.tronn.Activities.HistoryList.TAG_LINK;
import static com.example.myapp.tronn.Activities.HistoryList.TAG_TITLE;
import static com.example.myapp.tronn.Activities.Webactivity.HISTORYPREFERENCES;
import static com.example.myapp.tronn.Activities.Webactivity.HISTORY_LINKS;
import static com.example.myapp.tronn.Activities.Webactivity.HISTORY_TITLE;
import static com.example.myapp.tronn.Activities.Webactivity.PREFERENCES;
import static com.example.myapp.tronn.Activities.Webactivity.WEB_LINKS;
import static com.example.myapp.tronn.Activities.Webactivity.WEB_TITLE;

public class MainActivity extends AppCompatActivity {

    String Address;

    String Address2;

    private EditText editText;

    private EditText editText2;

    RelativeLayout rlayoutmain;

    private TextView finalResult;

    private EditText time;

    GridView gridView;
    Context ctx;



    ArrayList<HashMap<String, String>> listRowData;

    public static String TAG_TITLE = "title";
    public static String TAG_LINK = "link";

    ListView listView;
    ListAdapter adapter;
    LinearLayout linearLayout;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        linearLayout = findViewById(R.id.emptyList);

//        new MainActivity.LoadBookmarks().execute();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageButton historybtn = findViewById(R.id.imgbuttonhistory);
        historybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,HistoryList.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.fade);

            }
        });

        ImageButton bookmarkbtn = findViewById(R.id.imgbuttonbookmark);
        bookmarkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,BookmarkActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.fade);
            }
        });

        ImageButton gobtn = findViewById(R.id.imgbuttongo);
        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urldomainfix();

            }
        });





        GridView gridView=findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (position == 0) {

                    String Address = "http://m.facebook.com/";
                    Intent intent = new Intent(MainActivity.this, Webactivity.class);
                    intent.putExtra("Address", Address);
                    startActivity(intent);
                } else if (position == 1) {
                    String Address = "http://www.cnn.com/";
                    Intent intent = new Intent(MainActivity.this, Webactivity.class);
                    intent.putExtra("Address", Address);
                    startActivity(intent);
                } else if (position == 2) {
                    String Address = "http://bbc.com/";
                    Intent intent = new Intent(MainActivity.this, Webactivity.class);
                    intent.putExtra("Address", Address);
                    startActivity(intent);
                } else if (position == 3) {
                    String Address = "http://twitter.com/";
                    Intent intent = new Intent(MainActivity.this, Webactivity.class);
                    intent.putExtra("Address", Address);
                    startActivity(intent);
                } else if (position == 4) {
                    String Address = "http://instagram.com/";
                    Intent intent = new Intent(MainActivity.this, Webactivity.class);
                    intent.putExtra("Address", Address);
                    startActivity(intent);
                } else if (position == 5) {
                    String Address = "http://wikipedia.com/";
                    Intent intent = new Intent(MainActivity.this, Webactivity.class);
                    intent.putExtra("Address", Address);
                    startActivity(intent);

                } else if (position == 6) {
                    String Address = "http://zidsworld.com/";
                    Intent intent = new Intent(MainActivity.this, Webactivity.class);
                    intent.putExtra("Address", Address);
                    startActivity(intent);


                } else if (position == 7) {

                    String Address = "https://quora.com/messages";
                    Intent intent = new Intent(MainActivity.this, Webactivity.class);
                    intent.putExtra("Address", Address);
                    startActivity(intent);

                } else if (position == 8) {
                    String Address = "http://wireclub.com/";
                    Intent intent = new Intent(MainActivity.this, Webactivity.class);
                    intent.putExtra("Address", Address);
                    startActivity(intent);

                }
                overridePendingTransition(R.anim.fade, R.anim.fade);
            }
        });


        final EditText editText3 =  findViewById(R.id.urlbox);

        editText3.setText("www.google.com");



        editText3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    urldomainfix();
                }
                return false;
            }
        });}










    public boolean urldomainfix() {
        httpfix();
        EditText editText = findViewById(R.id.urlbox);
        if (editText.getText().toString().endsWith(".com") || (editText.getText().toString().endsWith(".in") || (editText.getText().toString().endsWith(".net") || (editText.getText().toString().endsWith(".co.uk") ||
                (editText.getText().toString().endsWith(".org") || (editText.getText().toString().endsWith(".gov") || (editText.getText().toString().endsWith(".co.in") ||
                        (editText.getText().toString().endsWith(".gov.in") || (editText.getText().toString().endsWith(".uk") || (editText.getText().toString().endsWith(".edu") ||
                                (editText.getText().toString().endsWith(".jp") || (editText.getText().toString().endsWith(".au") || (editText.getText().toString().endsWith(".be"))))))))))))))

        {
            Address = "http://" + editText.getText().toString();

            Intent intent = new Intent(getApplicationContext(), Webactivity.class);
            intent.putExtra("Address", Address);
            startActivity(intent);
            overridePendingTransition(R.anim.fade, R.anim.fade);
            return false;

        } else {
            String Address;

            Address = "http://www.google.co.in/search?q=" + editText.getText().toString();

            Intent intent = new Intent(getApplicationContext(), Webactivity.class);
            intent.putExtra("Address", Address);
            startActivity(intent);
            overridePendingTransition(R.anim.fade, R.anim.fade);
        }
        return true;
    }

    public boolean httpfix() {
        EditText editText= findViewById(R.id.urlbox);

        if (editText.getText().toString().startsWith("http://") || editText.getText().toString().startsWith("https://")) {
            editText.setText(editText.getText().toString().replaceAll("http://", ""));
            editText.setText(editText.getText().toString().replaceAll("https://", ""));
        }
        return true;
    }}






//private class LoadBookmarks extends AsyncTask<String, String, String> {
//
//
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected String doInBackground(String... args) {
//        // updating UI from Background Thread
//        runOnUiThread(new Runnable() {
//            public void run() {
//
//                SharedPreferences sharedPreferences = getSharedPreferences(HISTORYPREFERENCES, Context.MODE_PRIVATE);
//                String jsonLink = sharedPreferences.getString(HISTORY_LINKS, null);
//                String jsonTitle = sharedPreferences.getString(HISTORY_TITLE, null);
//                listRowData = new ArrayList<>();
//
//                if (jsonLink != null && jsonTitle != null) {
//
//                    Gson gson = new Gson();
//                    ArrayList<String> linkArray = gson.fromJson(jsonLink, new TypeToken<ArrayList<String>>() {
//                    }.getType());
//
//                    ArrayList<String> titleArray = gson.fromJson(jsonTitle, new TypeToken<ArrayList<String>>() {
//                    }.getType());
//
//
//                    for (int i = 0; i < linkArray.size(); i++) {
//                        HashMap<String, String> map = new HashMap<>();
//
//                        if (titleArray.get(i).length() == 0)
//                            map.put(TAG_TITLE, "History " + (i + 1));
//                        else
//                            map.put(TAG_TITLE, titleArray.get(i));
//
//                        map.put(TAG_LINK, linkArray.get(i));
//                        listRowData.add(map);
//                    }
//
//                    adapter = new SimpleAdapter(MainActivity.this,
//                            listRowData, R.layout.bookmark_list_row,
//                            new String[]{TAG_TITLE, TAG_LINK},
//                            new int[]{R.id.title, R.id.link});
//
//                    listView.setAdapter(adapter);
//                }
//
//                linearLayout.setVisibility(View.VISIBLE);
//                listView.setEmptyView(linearLayout);
//
//
//            }
//        });
//        return null;
//    }}}