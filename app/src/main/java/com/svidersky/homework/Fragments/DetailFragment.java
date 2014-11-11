package com.svidersky.homework.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.svidersky.homework.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Eren on 11.11.2014.
 */
public class DetailFragment extends android.app.Fragment {

    TextView text;
    ImageView bmImage;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.menu_detail_fragment, container, false);
        //String str = getArguments().getString("url") + "  "  + getArguments().getString("uploaded");
        String str = getArguments().getString("description") + "  Дата добавления "+ getArguments().getString("uploaded")
                +"Посилання: " +getArguments().getString("url");
        text = (TextView) view.findViewById(R.id.detail);
       // iv = (ImageView) view.findViewById(R.id.imageView); final URL finalUrl = url;

        text.setText(str);
        return view;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {


        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
