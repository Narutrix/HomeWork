package com.svidersky.homework.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.svidersky.homework.R;


/**
 * Created by Eren on 04.11.2014.
 */
public class SelectActivity extends Activity {

    final String[] items = {"Drawer", "Multi pane", "Rss reader"};
    Dialog dialog;
    Context context = this;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.setTitle("Select activity").setItems(items, onItemClickListener).create();
        dialog.show();
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }



    DialogInterface.OnClickListener onItemClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogArg, int which) {
            switch (which) {
                case 0:
                    intent = new Intent (context, MyActivity.class);
                    break;
                case 1:
                    intent = new Intent(context, HeadActivity.class);
                    break;
                case 2:
                    intent = new Intent(context, RssActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
            dialogArg.dismiss();
        }
    };

}
