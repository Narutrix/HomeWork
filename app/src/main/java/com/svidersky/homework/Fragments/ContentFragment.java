package com.svidersky.homework.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svidersky.homework.R;

/**
 * Created by Eren on 04.11.2014.
 */
public class ContentFragment extends Fragment {
    public static ContentFragment newInstance(int pos) {
        ContentFragment content = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt("position", pos);
        content.setArguments(args);
        return content;
    }

    public int getPosition() {
        return getArguments().getInt("position", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.details, container, false);
        TextView tv = (TextView) v.findViewById(R.id.tvText);
        tv.setText(getResources().getStringArray(R.array.content)[getPosition()]);
        return v;
    }
}