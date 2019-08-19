package com.example.myapp.tronn.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.tronn.BuildConfig;
import com.example.myapp.tronn.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.myapp.tronn.Activities.Webactivity.HISTORYPREFERENCES;
import static com.example.myapp.tronn.Activities.Webactivity.HISTORY_LINKS;
import static com.example.myapp.tronn.Activities.Webactivity.HISTORY_TITLE;

public class WebactivityTab2 extends AppCompatActivity {


    // log tag, preferences, runtime permissions

    // create link handler (long clicked links)
    //private final MainActivity.MyHandler linkHandler = new MainActivity.MyHandler(this);
//
    // save images
    private static final int ID_CONTEXT_MENU_SAVE_IMAGE = 2562617;
    private static final int ID_CONTEXT_MENU_SHARE_IMAGE = 2562618;
    private String mPendingImageUrlToSave;
    private static String appDirectoryName;

    private PopupMenu mPopupMenu;
    private Menu mMenu;
    private WebView webl;

    private static final String uanormal = "Mozilla/5.0 (Linux; U; Android 2.3.3; en-gb; " +
            "Nexus S Build/GRI20) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
    public String ial = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

    public static final String MESSENGER_URL = "https://www.messenger.com/login";
    public static final String NOTIFICATION_OLD_MESSAGES_URL = "https://m.insta.com/messages#";
    private static final long UPDATE_CHECK_INTERVAL = 43200000L;  // 12 hours

    public static final int AlarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;
    private ProgressBar loadingProgressBar;

    private static final String TAG = WebactivityTab2.class.getSimpleName();
    private SharedPreferences preferences;
    private static final int REQUEST_STORAGE = 1;

    private static final int REQUEST_LOCATION = 2;
    public ValueCallback<Uri> mUploadMessage;
    public static final int FILECHOOSER_RESULTCODE = 5173;
    public static final String FB_URL = "https://m.insta.com";

    public String fbbasicurl = "http://mbasic.insta.com";
    private MediaPlayer mp;
    private SharedPreferences mPrefs;

    private String mCM;
    private ValueCallback<Uri> mUM;
    private ValueCallback<Uri[]> mUMA;
    private final static int FCR = 1;
    public EditText editText3;


    public static final String PREFERENCES = "PREFERENCES_NAME";
    public static final String WEB_LINKS = "links";
    public static final String WEB_TITLE = "title";



    private String url = null;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webactivity);

        Snackbar.make(getWindow().getDecorView(), "Tab 2 is now Active", Snackbar.LENGTH_SHORT)
                .show();


        webl =  findViewById(R.id.web1);

        ImageButton btnmenu =findViewById(R.id.btnmenu);
        ImageButton btnback =findViewById(R.id.btnback);
        ImageButton btnforwd =findViewById(R.id.btnforward);
        ImageButton btnhome =findViewById(R.id.btnhome);
        ImageButton btnmore =findViewById(R.id.btnmore);


        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                    PopupMenu menu = new PopupMenu(getApplicationContext(), view);
                    menu.getMenuInflater().inflate(R.menu.popupmenu,menu.getMenu());

                    menu.show();


//                    Object menuHelper;
//                    Class[] argTypes;
//                    try {
//                        Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
//                        fMenuHelper.setAccessible(true);
//                        menuHelper = fMenuHelper.get(menu);
//                        argTypes = new Class[]{boolean.class};
//                        menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
//                    } catch (Exception e) {

//                    }
//                    menu.show();

                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem menu_item) {
                            switch (menu_item.getItemId()) {
                                case R.id.reload:

                                    //Do your action here
                                    webl.reload();


                                    break;
                                case R.id.exit:
                                    finish();

                                    //Do your action here
                                    break;

                                case R.id.fullscreen:

                                    try {
                                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    //Do your action here
                                    break;


                                case R.id.about:


                                    String versionName = BuildConfig.VERSION_NAME;

                                    Toast.makeText(WebactivityTab2.this, "Owl Browser Version "+versionName+" Uses free icons from freepik.com", Toast.LENGTH_SHORT).show();

                                    //Do your action here
                                    break;
                                case R.id.clrcache:
                                    webl.clearCache(true);

                                    Toast.makeText(WebactivityTab2.this, "Cache cleared", Toast.LENGTH_SHORT).show();

                                    //Do your action here
                                    break;
                            }
                            return true;
                        }
                    });




                } catch (Exception e) {
                    e.printStackTrace();
                }}});


        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {









                try {


                    PopupMenu menu = new PopupMenu(getApplicationContext(), view);
                    menu.getMenuInflater().inflate(R.menu.menumore,menu.getMenu());

                    menu.show();


                    Object menuHelper;
                    Class[] argTypes;
                    try {
                        Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
                        fMenuHelper.setAccessible(true);
                        menuHelper = fMenuHelper.get(menu);
                        argTypes = new Class[]{boolean.class};
                        menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                    menu.show();

                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem menu_item) {
                            switch (menu_item.getItemId()) {
                                case R.id.tab1:

                                    Intent ind = new Intent(WebactivityTab2.this, WebactivityTab1.class);
                                    startActivity(ind);
                                    overridePendingTransition(R.anim.trs, R.anim.fade);
                                   //Do your action here


                                    break;
                                case R.id.tab2:

                                    Toast.makeText(WebactivityTab2.this, "Tab 2 is Already Active!", Toast.LENGTH_SHORT).show();
                                    //Do your action here
                                    break;

                                case R.id.home:

                                    Intent intent = new Intent(WebactivityTab2.this, Webactivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.trs, R.anim.fade);
                                    //Do your action here
                                    break;


                            }
                            return true;
                        }
                    });




                } catch (Exception e) {
                    e.printStackTrace();
                }}});







//                                           final CharSequence[] items = {"New Tab 1", "New Tab 2", "Home Tab"};
//
//                                           AlertDialog.Builder builder = new AlertDialog.Builder(Webactivity.this);
//
//                                           builder.setTitle("Select Tab");
//                                           builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                                               @Override
//                                               public void onCancel(DialogInterface dialog) {
//
//                                               }
//                                           });
//                                           builder.setItems(items, new DialogInterface.OnClickListener() {
//                                               @Override
//                                               public void onClick(DialogInterface dialog, int item) {
//
//                                                   if (items[item].equals("New Tab 1")) {
//                                                       Intent i = new Intent(Webactivity.this, WebactivityTab1.class);
//                                                       startActivity(i);
//                                                       overridePendingTransition(R.anim.fade, R.anim.trs);
//                                                   } else if (items[item].equals("New Tab 2")) {
//                                                       Intent i = new Intent(Webactivity.this, WebactivityTab2.class);
//                                                       startActivity(i);
//                                                       overridePendingTransition(R.anim.trs, R.anim.fade);
//
//                                                   } else if (items[item].equals("Home Tab")) {
//
//                                                       Snackbar.make(getWindow().getDecorView(), "Home Tab is already Active!", Snackbar.LENGTH_SHORT)
//                                                               .show();
//
//                                                   }
//                                               }
//                                           });
//                                           builder.show();
//                                       }
//
//                                   });


        btnforwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (webl.canGoForward()) {
                    webl.goForward();

                } else {

                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if(v != null) {
                        v.vibrate(50);
                    }


                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (webl.canGoBack()) {
                    webl.goBack();

                } else {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if(v != null) {
                        v.vibrate(50);
                    }

                }
            }
        });




        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(WebactivityTab2.this, MainActivity.class);
                webl.clearCache(true);
                startActivity(i);
                overridePendingTransition(R.anim.fade, R.anim.fade);
                finish();

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbarweb);
        setSupportActionBar(toolbar);

        final EditText editText3 =  findViewById(R.id.urlbox2);

        editText3.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    urldomainfix();
                }
                return false;
            }
        });

        final FrameLayout flayout2 = findViewById(R.id.info);

        //Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.list);
        //toolbar.setOverflowIcon(drawable);
        // get shared preferences and TrayPreferences
//        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        appDirectoryName = getString(R.string.app_name).replace(" ", "");
//
//        if (preferences.getBoolean("first_run", true)) {
//            // show quick start guide
//            // save the fact that the app has been started at least once
//            preferences.edit().putBoolean("first_run", false).apply();
//        }
//
//        if (preferences.getBoolean("keyboard_fix", false))
//            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//
//        if (preferences.getBoolean("fullscreen", false))
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        webl =  findViewById(R.id.web1);

        WebSettings webSettings = webl.getSettings();
        webl.getSettings().setLoadsImagesAutomatically(true);
        webl.getSettings().setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        webl.getSettings().setLoadWithOverviewMode(true);
        webl.getSettings().setUseWideViewPort(true);
        webl.getSettings().setSupportZoom(true);
        webl.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webl.getSettings().setDomStorageEnabled(true);

        webl.getSettings().getBlockNetworkImage();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webl.canGoForward();
        webl.getHitTestResult();

        String Address = getIntent().getStringExtra("Address");
        String Address2 = getIntent().getStringExtra("Address2");
        webl.loadUrl("http://google.com");


        String urlint = getIntent().getStringExtra("url");


//
//        if (getIntent().getStringExtra(null)==null){
//            webl.loadUrl("http://google.com");
//        }


        if (Build.VERSION.SDK_INT < 18) {
            //noinspection deprecation
            webl.getSettings().setAppCacheMaxSize(5 * 1024 * 1024);  // 50 MB
        }


        webl.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        webl.getSettings().setAppCacheEnabled(true);
        webl.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        webl.setDownloadListener(new DownloadListener() {




            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {

                Downloader(url,userAgent,contentDisposition,mimeType,contentLength);



            }});

        webl.setWebViewClient(new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                // Notice Here.
                super.onLoadResource(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {



                flayout2.setVisibility(View.GONE);


                super.onPageFinished(view, url);

                if (url.startsWith("whatsapp://")) {
                    webl.stopLoading();
                    try {
                        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                        whatsappIntent.setType("text/plain");
                        whatsappIntent.setPackage("com.whatsapp");

                        whatsappIntent.putExtra(Intent.EXTRA_TEXT, webl.getUrl() + "  - Shared from The Owl Browser");

                        startActivity(whatsappIntent);
                    } catch (android.content.ActivityNotFoundException ex) {

                        String MakeShortText = "Whatsapp have not been installed";

                        Toast.makeText(WebactivityTab2.this, MakeShortText, Toast.LENGTH_SHORT).show();

                        Toast.makeText(WebactivityTab2.this, MakeShortText, Toast.LENGTH_SHORT).show();
                    }
                }



            }


            @Override
            public void onPageStarted(WebView view, String URL, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                saveHistory();



                flayout2.setVisibility(View.VISIBLE);

// Start the animation
            }


        });


        webl.loadUrl(Address);
        webl.loadUrl(url);

        webl.loadUrl(urlint);





        editText3.setText(webl.getUrl());


        if (Build.VERSION.SDK_INT < 18) {
            //speed webview
            webl.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        }

        loadingProgressBar = (ProgressBar) findViewById(R.id.prog);
        registerForContextMenu(webl);
        final TextView txtinfo =  findViewById(R.id.infotext);
        webl.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                loadingProgressBar.setProgress(newProgress);


                //loadingTitle.setProgres s(newProgress);
                // hide the progress bar if the loading is complete

                if (newProgress == 0) {

                } else {

                    loadingProgressBar.setVisibility(View.VISIBLE);

                    if (newProgress == 5) {
                        txtinfo.setText("Starting to recieve data..");
                    } else {
                        loadingProgressBar.setVisibility(View.VISIBLE);


                        if (newProgress == 30) {

                            flayout2.setVisibility(View.VISIBLE);
                            txtinfo.setText("Recieving Data. 30%");

                        } else {

                            loadingProgressBar.setVisibility(View.VISIBLE);

                            if (newProgress == 40) {

                                flayout2.setVisibility(View.VISIBLE);
                                txtinfo.setText("Reading recieved data..");


                            } else {

                                loadingProgressBar.setVisibility(View.VISIBLE);


                                if (newProgress == 50) {

                                    flayout2.setVisibility(View.VISIBLE);
                                    txtinfo.setText("Recieving Data..50%");
                                } else {

                                    loadingProgressBar.setVisibility(View.VISIBLE);


                                    if (newProgress == 70) {

                                        txtinfo.setText(webl.getTitle());


                                    } else {


                                        loadingProgressBar.setVisibility(View.VISIBLE);

                                        if (newProgress == 85) {
                                            txtinfo.setText("Recieving data 85%");
                                            flayout2.setAlpha(1);
                                        } else {

                                            loadingProgressBar.setVisibility(View.VISIBLE);


                                            if (newProgress == 100) {

                                                loadingProgressBar.setVisibility(View.INVISIBLE);
                                                flayout2.setVisibility(View.VISIBLE);

                                                httpfix();
                                                editText3.setText(webl.getUrl());

                                            } else {

                                                loadingProgressBar.setVisibility(View.VISIBLE);
                                                flayout2.setVisibility(View.VISIBLE);
                                                txtinfo.setText(webl.getUrl());


                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                this.openFileChooser(uploadMsg, "*/*");
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                this.openFileChooser(uploadMsg, acceptType, null);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                WebactivityTab2.this.startActivityForResult(Intent.createChooser(i, "File Browser"),
                        FILECHOOSER_RESULTCODE);
            }


            public boolean onShowFileChooser(


                    WebView webView, ValueCallback<Uri[]> filePathCallback,
                    FileChooserParams fileChooserParams) {
                if (mUMA != null) {
                    mUMA.onReceiveValue(null);
                }
                mUMA = filePathCallback;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(WebactivityTab2.this.getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        takePictureIntent.putExtra("PhotoPath", mCM);
                    } catch (IOException ex) {
                        Log.e(TAG, "Image file creation failed", ex);
                    }
                    if (photoFile != null) {
                        mCM = "file:" + photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    } else {
                        takePictureIntent = null;
                    }
                }

                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("*/*");
                Intent[] intentArray;
                if (takePictureIntent != null) {
                    intentArray = new Intent[]{takePictureIntent};
                } else {
                    intentArray = new Intent[0];
                }

                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooserIntent, FCR);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (Build.VERSION.SDK_INT >= 21) {
            Uri[] results = null;
            //Check if response is positive
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == FCR) {
                    if (null == mUMA) {
                        return;
                    }
                    if (intent == null) {
                        //Capture Photo if no image available
                        if (mCM != null) {
                            results = new Uri[]{Uri.parse(mCM)};
                        }
                    } else {
                        String dataString = intent.getDataString();
                        if (dataString != null) {
                            results = new Uri[]{Uri.parse(dataString)};
                        }
                    }
                }
            }
            mUMA.onReceiveValue(results);
            mUMA = null;
        } else {
            if (requestCode == FCR) {
                if (null == mUM) return;
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUM.onReceiveValue(result);
                mUM = null;
            }
        }
    }


    private class Callback extends WebViewClient {
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(getApplicationContext(), "Failed loading! try reloading", Toast.LENGTH_SHORT).show();
            webl.loadUrl("file:///android_asset/help.html");
        }
    }

    // Create an image file
    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "img_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);


    }


    @Override

    public void onBackPressed() {

        if (webl.canGoBack()) {
            webl.goBack();

        } else {
            webl.clearCache(true);
            finish();
        }

    }
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem stopload = menu.findItem(R.id.stopload);
        MenuItem go = menu.findItem(R.id.go);
        if(webl.getProgress()==100)
        {
            stopload.setVisible(false);
            go.setVisible(true);
            invalidateOptionsMenu();

        }
        else
        {
            stopload.setVisible(true);
            go.setVisible(false);
            invalidateOptionsMenu();
        }
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.browser_menu, menu);


        String current_page_url = webl.getUrl() ;



        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        String links = sharedPreferences.getString(WEB_LINKS, null);

        if (links != null) {

            Gson gson = new Gson();
            ArrayList<String> linkList = gson.fromJson(links, new TypeToken<ArrayList<String>>() {
            }.getType());

            if (linkList.contains(current_page_url)) {
                menu.getItem(0).setIcon(R.drawable.bookmark_36dp);
            } else {
                menu.getItem(0).setIcon(R.drawable.bookmark_36dp);
            }
        } else {
            menu.getItem(0).setIcon(R.drawable.bookmark_36dp);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

         if (id == R.id.addbookmark) {

            String current_page_url = webl.getUrl();
            String message;

            SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
            String jsonLink = sharedPreferences.getString(WEB_LINKS, null);
            String jsonTitle = sharedPreferences.getString(WEB_TITLE, null);


            if (jsonLink != null && jsonTitle != null) {

                Gson gson = new Gson();
                ArrayList<String> linkList = gson.fromJson(jsonLink, new TypeToken<ArrayList<String>>() {
                }.getType());

                ArrayList<String> titleList = gson.fromJson(jsonTitle, new TypeToken<ArrayList<String>>() {
                }.getType());

                if (linkList.contains(current_page_url)) {
                    linkList.remove(current_page_url);
                    titleList.remove(webl.getTitle().trim());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(WEB_LINKS, new Gson().toJson(linkList));
                    editor.putString(WEB_TITLE, new Gson().toJson(titleList));
                    editor.apply();


                    message = "Bookmark Removed";

                } else {
                    linkList.add(current_page_url);
                    titleList.add(webl.getTitle().trim());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(WEB_LINKS, new Gson().toJson(linkList));
                    editor.putString(WEB_TITLE, new Gson().toJson(titleList));
                    editor.apply();

                    message = "Bookmarked";
                }
            } else {

                ArrayList<String> linkList = new ArrayList<>();
                ArrayList<String> titleList = new ArrayList<>();
                linkList.add(current_page_url);
                titleList.add(webl.getTitle());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(WEB_LINKS, new Gson().toJson(linkList));
                editor.putString(WEB_TITLE, new Gson().toJson(titleList));
                editor.apply();

                message = "Bookmarked";
            }

            Toast.makeText(WebactivityTab2.this, message, Toast.LENGTH_SHORT).show();


        } else if (id == R.id.bookmarks) {

           Intent i = new Intent(WebactivityTab2.this,BookmarkActivity.class);
           startActivity(i);



        } else if (id ==R.id.history){

            Intent i =new Intent(WebactivityTab2.this,HistoryList.class);
            startActivity(i);
        } else if (id == R.id.stopload) {
            webl.stopLoading();


        }else if (id==R.id.go){

            urldomainfix();
        } else if (id==R.id.exit){

             webl.clearCache(true);
             webl.clearHistory();
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                 finishAffinity();
             } else
                 finish();



         }




        return super.onOptionsItemSelected(item);

    }


    public boolean urldomainfix() {

        try {



        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(getCurrentFocus() != null) {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        httpfix();
        EditText editText =  findViewById(R.id.urlbox2);
        if (editText.getText().toString().endsWith(".com") || (editText.getText().toString().contains(".com") || (editText.getText().toString().endsWith(".in") || (editText.getText().toString().contains(".in") ||
                (editText.getText().toString().endsWith(".net") || (editText.getText().toString().contains(".net") || (editText.getText().toString().endsWith(".co.uk") ||
                        (editText.getText().toString().contains(".net") || (editText.getText().toString().contains(".org") || (editText.getText().toString().contains(".gov") ||
                                (editText.getText().toString().endsWith(".org") || (editText.getText().toString().endsWith(".gov") || (editText.getText().toString().contains(".co.in") || (editText.getText().toString().contains(".org") ||
                                        (editText.getText().toString().endsWith(".gov.in") || (editText.getText().toString().contains(".uk") || (editText.getText().toString().contains(".edu") ||

                                                (editText.getText().toString().endsWith(".nz/") || (editText.getText().toString().contains(".nz/") ||
                                                        (editText.getText().toString().endsWith(".jp") || (editText.getText().toString().contains(".au") || (editText.getText().toString().contains(".be")))))))))))))))))))))))


        {

            if (editText.getText().toString().contains(" ")) {
                String Address = "http://www.google.co.in/search?q=" + editText.getText().toString();

                webl.loadUrl(Address);

                return true;


            } else {

                String Address = "http://" + editText.getText().toString();
                webl.loadUrl(Address);
            }
            return true;
        } else {

            String Address = "http://www.google.co.in/search?q=" + editText.getText().toString();
            webl.loadUrl(Address);
            return true;
        }
    }


//
//


    public boolean httpfix() {
        EditText editText =  findViewById(R.id.urlbox2);

        if (editText.getText().toString().startsWith("http://") || editText.getText().toString().startsWith("https://")) {
            editText.setText(editText.getText().toString().replaceAll("http://", ""));
            editText.setText(editText.getText().toString().replaceAll("https://", ""));
        }
        return true;
    }

    public boolean spacefix() {
        EditText editText = (EditText) findViewById(R.id.urlbox2);

        if (editText.getText().toString().startsWith(" ") || editText.getText().toString().contains(" ")) {

        } else {
            String Address = "http://www.google.co.in/search?q=" + editText.getText().toString();
            webl.loadUrl(Address);
        }

        return true;
    }


        public void saveHistory(){



    try {


        String current_page_url = webl.getUrl();
        String message;

        SharedPreferences hsharedPreferences = getSharedPreferences(HISTORYPREFERENCES, Context.MODE_PRIVATE);
        String jsonLink = hsharedPreferences.getString(HISTORY_LINKS, null);
        String jsonTitle = hsharedPreferences.getString(HISTORY_TITLE, null);


        if (jsonLink != null && jsonTitle != null) {

            Gson gson = new Gson();
            ArrayList<String> hlinkList = gson.fromJson(jsonLink, new TypeToken<ArrayList<String>>() {
            }.getType());

            ArrayList<String> htitleList = gson.fromJson(jsonTitle, new TypeToken<ArrayList<String>>() {
            }.getType());

            if (hlinkList.contains(current_page_url)) {
                hlinkList.remove(current_page_url);
                htitleList.remove(webl.getTitle().trim());
                SharedPreferences.Editor editor = hsharedPreferences.edit();
                editor.putString(HISTORY_LINKS, new Gson().toJson(hlinkList));
                editor.putString(HISTORY_TITLE, new Gson().toJson(htitleList));
                editor.apply();


            } else {
                hlinkList.add(current_page_url);
                htitleList.add(webl.getTitle().trim());
                SharedPreferences.Editor editor = hsharedPreferences.edit();
                editor.putString(HISTORY_LINKS,new Gson().toJson(hlinkList));

                editor.putString(HISTORY_TITLE, new Gson().toJson(htitleList));
                editor.apply();

            }
        } else {

            ArrayList<String> linkList = new ArrayList<>();
            ArrayList<String> titleList = new ArrayList<>();
            linkList.add(current_page_url);
            titleList.add(webl.getTitle());
            SharedPreferences.Editor editor = hsharedPreferences.edit();
            editor.putString(HISTORY_LINKS, new Gson().toJson(linkList));
            editor.putString(HISTORY_TITLE, new Gson().toJson(titleList));
            editor.apply();}}catch (Exception e){
        e.printStackTrace();
    }}

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(WebactivityTab2.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;


        }
    }


    public void Downloader(String url, String userAgent, String contentDisposition, String mimeType, long contentLength){

        if (Build.VERSION.SDK_INT >= 23) {
            if (isStoragePermissionGranted()) {

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                request.setMimeType(mimeType);
                //------------------------COOKIE!!------------------------
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                //------------------------COOKIE!!------------------------
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(WebactivityTab2.this, "Grant storage permission", Toast.LENGTH_SHORT).show();
            }
        } else {


            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

            request.setMimeType(mimeType);
            //------------------------COOKIE!!------------------------
            String cookies = CookieManager.getInstance().getCookie(url);
            request.addRequestHeader("cookie", cookies);
            //------------------------COOKIE!!------------------------
            request.addRequestHeader("User-Agent", userAgent);
            request.setDescription("Downloading file...");
            request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            dm.enqueue(request);
            Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
        }


    }}

