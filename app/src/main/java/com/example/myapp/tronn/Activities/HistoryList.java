package com.example.myapp.tronn.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapp.tronn.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.myapp.tronn.Activities.Webactivity.HISTORYPREFERENCES;
import static com.example.myapp.tronn.Activities.Webactivity.HISTORY_LINKS;
import static com.example.myapp.tronn.Activities.Webactivity.HISTORY_TITLE;


public class  HistoryList extends AppCompatActivity {

    ArrayList<HashMap<String, String>> listRowData;

    public static String TAG_TITLE = "title";
    public static String TAG_LINK = "link";

    ListView listView;
    ListAdapter adapter;
    LinearLayout linearLayout;
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("History");
        toolbar.setNavigationIcon(R.drawable.back_24dp);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));

        Button bt = new Button(this);
        bt.setText("Clear");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
        params.gravity = Gravity.END;
        bt.setLayoutParams(params);
        toolbar.addView(bt);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {


                SharedPreferences preferences = getSharedPreferences(HISTORYPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();


                editor.clear();
                editor.apply();
                editor.notify();
                Toast.makeText(HistoryList.this, " History will be cleared soon", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                    //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    //there is exception here


                }

        }});


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        listView = findViewById(R.id.listView);
        linearLayout = findViewById(R.id.emptyList);

        mSwipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new LoadBookmarks().execute();

            }
        });

        new LoadBookmarks().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Object o = listView.getAdapter().getItem(position);

                if (o instanceof Map) {
                    Map map = (Map) o;
                    String Address= String.valueOf(map.get(TAG_LINK));
                    Intent in = new Intent(HistoryList.this, Webactivity.class);
                    in.putExtra("url", String.valueOf(map.get(TAG_LINK)));
                    startActivity(in);
                    finish();
                }


            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listView.getAdapter().getItem(i);
                if (o instanceof Map) {
                    Map map = (Map) o;
                    deleteBookmark(String.valueOf(map.get(TAG_TITLE)), String.valueOf(map.get(TAG_LINK)));
                }

                return true;
            }
        });

    }

    private class LoadBookmarks extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {

                    SharedPreferences sharedPreferences = getSharedPreferences(HISTORYPREFERENCES, Context.MODE_PRIVATE);
                    String jsonLink = sharedPreferences.getString(HISTORY_LINKS, null);
                    String jsonTitle = sharedPreferences.getString(HISTORY_TITLE, null);
                    listRowData = new ArrayList<>();

                    if (jsonLink != null && jsonTitle != null) {

                        Gson gson = new Gson();
                        ArrayList<String> linkArray = gson.fromJson(jsonLink, new TypeToken<ArrayList<String>>() {
                        }.getType());

                        ArrayList<String> titleArray = gson.fromJson(jsonTitle, new TypeToken<ArrayList<String>>() {
                        }.getType());


                        for (int i = 0; i < linkArray.size(); i++) {
                            HashMap<String, String> map = new HashMap<>();

                            if (titleArray.get(i).length() == 0)
                                map.put(TAG_TITLE, "History " + (i + 1));
                            else
                                map.put(TAG_TITLE, titleArray.get(i));

                            map.put(TAG_LINK, linkArray.get(i));
                            listRowData.add(map);
                        }

                        adapter = new SimpleAdapter(HistoryList.this,
                                listRowData, R.layout.bookmark_list_row,
                                new String[]{TAG_TITLE, TAG_LINK},
                                new int[]{R.id.title, R.id.link});

                        listView.setAdapter(adapter);
                    }

                    linearLayout.setVisibility(View.VISIBLE);
                    listView.setEmptyView(linearLayout);


                }
            });
            return null;
        }

        protected void onPostExecute(String args) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    private void deleteBookmark(final String title, final String link) {

        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("You want delete this history?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sharedPreferences = getSharedPreferences(HISTORYPREFERENCES, Context.MODE_PRIVATE);
                        String jsonLink = sharedPreferences.getString(HISTORY_LINKS, null);
                        String jsonTitle = sharedPreferences.getString(HISTORY_TITLE, null);


                        if (jsonLink != null && jsonTitle != null) {


                            Gson gson = new Gson();
                            ArrayList<String> linkArray = gson.fromJson(jsonLink, new TypeToken<ArrayList<String>>() {
                            }.getType());

                            ArrayList<String> titleArray = gson.fromJson(jsonTitle, new TypeToken<ArrayList<String>>() {
                            }.getType());


                            linkArray.remove(link);
                            titleArray.remove(title);


                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(HISTORY_LINKS, new Gson().toJson(linkArray));
                            editor.putString(HISTORY_TITLE, new Gson().toJson(titleArray));
                            editor.apply();

                            new LoadBookmarks().execute();
                        }
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

}