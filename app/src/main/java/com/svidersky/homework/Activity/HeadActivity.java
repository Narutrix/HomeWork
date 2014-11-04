package com.svidersky.homework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.svidersky.homework.R;
import com.svidersky.homework.fragments.ContentFragment;
import com.svidersky.homework.fragments.HeadFragment;

/**
 * Created by Eren on 04.11.2014.
 */
public class HeadActivity extends FragmentActivity implements
        HeadFragment.onItemClickListener {

    int position = 0;
    boolean withDetails = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_pane);
        if (savedInstanceState != null)
            position = savedInstanceState.getInt("position");
        withDetails = (findViewById(R.id.cont) != null);
        if (withDetails)
            showDetails(position);
    }

    void showDetails(int pos) {
        if (withDetails) {
            ContentFragment content = (ContentFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.cont);
            if (content == null || content.getPosition() != pos) {
                content = ContentFragment.newInstance(pos);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.cont, content).commit();
            }
        } else {
            startActivity(new Intent(this, ContentActivity.class).putExtra("position", position));
        }
    }

    @Override
    public void itemClick(int position) {
        this.position = position;
        showDetails(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }
}