package com.example.myapp.tronn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.tronn.R;


/**
 * Created by ziddik on 4/5/2018.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private TextView textView;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public Object getItem(int position) {
        return null;
    }


    public int getCount() {
        return mThumbIds.length;
    }

    public long getItemId(int position) {
        return 0;
    }




    public View getView(int position, View convertView, ViewGroup parent) {





        View v;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater li = LayoutInflater.from(mContext);
            v = li.inflate(R.layout.home_screen, null);
        } else {
            v = (View) convertView;
        }
        TextView text = v.findViewById(R.id.textv);
        text.setText(mThumbtext[position]);
        ImageView image = (ImageView) v.findViewById(R.id.imgGrid);
        image.setImageResource(mThumbIds[position]);
        return v;
    }






    // create a new ImageView for each item referenced by the Adapter
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ImageView iv;
//        View v = convertView;
//        if (v == null) {
//
//
//
//            // if it's not recycled, initialize some attributes
//
//                TextView tv =v.findViewById(R.id.textv);
//                tv.setText(mThumbtext[position]);
//                 iv = v.findViewById(R.id.imgGrid);
//                iv.setImageResource(mThumbIds[position]);
//
//            } else {
//                v = convertView;



//            }
//
//
//
//            return v;
//        }






// references to our images

//public void loadfromasset (){
//  try
//    {
//        // get input stream
//
//    }
//catch(IOException ex)
//    {
//        return;
//    }


            private Integer[] mThumbIds = {





            R.drawable.fb,
            R.drawable.cnn,
            R.drawable.bbc,
            R.drawable.twitter,
            R.drawable.insta,
            R.drawable.wikipedia,
            R.drawable.zids,
            R.drawable.quora,






    };


    private String[] mThumbtext = {

            "facebook",
            "cnn",
            "bbc",
            "twitter",
            "instagram",
            "wikipedia",
            "zidsworld",
            "quora",
    };}


