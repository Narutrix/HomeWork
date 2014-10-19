package com.svidersky.homework;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Eren on 19.10.2014.
 */
public class Fragment_2 extends Fragment implements View.OnClickListener {

    private char [] resul;
    private TextView tv;
    private TextView tv_2;
    private Button but;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        tv = (TextView)getActivity().findViewById(R.id.textView);
        but = (Button)getActivity().findViewById(R.id.button2);
        tv_2 = (TextView)getActivity().findViewById(R.id.textView2);
        but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = tv.getText().toString();
        if(!str.isEmpty())
        {
            resul = new char[str.length()];
            for (int i = 0; i < str.length(); i++ )
            {
                resul[i] = decode(str.charAt(i));
            }
            String result = new String(resul);
            tv_2.setText(result);
        }
    }

    public  char decode (char c)
    {
        char temp = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 7; j++)
            {
                if(c == Fragment_1.table[i][j])
                {
                    if(i == 0 )
                        temp = Fragment_1.table[5][j];
                    else
                        temp = Fragment_1.table[i-1][j];
                }
            }
        return temp;
    }
}
