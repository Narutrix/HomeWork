package com.svidersky.homework.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.svidersky.homework.R;
import com.svidersky.homework.utils.ServiceHandler;
import com.svidersky.homework.fragments.DetailFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eren on 11.11.2014.
 */
public class RssActivity extends Activity {

    private DrawerLayout dLayout;
    private ArrayList<String> menu;
    private ListView dList;
    private ArrayAdapter<String> adapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ProgressDialog pDialog;
    private JSONArray video = null;
    private ArrayList<HashMap<String, String>> videoList;
    private static String url = "https://gdata.youtube.com/feeds/api/users/itsSokolOff/uploads?v=2&alt=jsonc&max-results=7";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_layout);
        videoList = new ArrayList<HashMap<String, String>>();
        new GetData().execute();

        mTitle = mDrawerTitle = getTitle();
        menu = new ArrayList<String>();

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);

        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_dark);

        dList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {

                dLayout.closeDrawers();
                Bundle args = new Bundle();
                args.putString("url", videoList.get(position).get("video"));
                args.putString("uploaded", videoList.get(position).get("uploaded"));
                args.putString("description", videoList.get(position).get("description"));

                Fragment detail = new DetailFragment();
                detail.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();

            }

        });
        // enabling action bar
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, dLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        dLayout.setDrawerListener(mDrawerToggle);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
         return super.onOptionsItemSelected(item);
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(RssActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            Log.d("Response: ", "> " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject data = jsonObj.getJSONObject("data");
                    video = data.getJSONArray("items");

                    for (int i=0; i < video.length(); i++){
                        JSONObject items = video.getJSONObject(i);
                        String title = items.getString("title");
                        String uploaded = items.getString("uploaded");
                        String category = items.getString("category");
                        String description = items.getString("description");
                        JSONObject thumbnail = items.getJSONObject("thumbnail");
                        JSONObject content = items.getJSONObject("player");//content
                        String video = content.getString("mobile");
                        String picture = thumbnail.getString("sqDefault");
                        HashMap<String, String> contact = new HashMap<String, String>();
                        contact.put("title", title);
                        contact.put("picture", picture);
                        contact.put("video", video);
                        contact.put("uploaded", uploaded);
                        contact.put("category", category);
                        contact.put("description", description);
                        videoList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

         @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
              for (int i = 0; i<videoList.size(); i++){
                  menu.add(videoList.get(i).get("title"));
             }
             dList.setAdapter(adapter);
        }

    }

}

