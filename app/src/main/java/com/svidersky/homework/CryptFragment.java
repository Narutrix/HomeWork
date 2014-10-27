package com.svidersky.homework;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.svidersky.homework.R;

public class CryptFragment extends Fragment {

    EditText ed_1;
    TextView tv;
    TextView tv_2;
    GridView gvMain;
    char [] resul;
    ArrayAdapter<String> adapter;
    char table [][] = new char[][] {{'й','м','о','в','і','р','н'},
            {'а','б','г','д','е','ё','ж'},
            {'з','и','к','л','п','с','т'},
            {'у','ф','х','ц','ч','ш','щ'},
            {'ъ','ы','ь','э','ю','я','ґ'},
            {'є','ї','!','?',',','.',' '}};

    public CryptFragment(){}


	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_crypt, container, false);

        ed_1 = (EditText) rootView.findViewById(R.id.editText);
        tv = (TextView) rootView.findViewById(R.id.textView);
        tv_2 = (TextView) rootView.findViewById(R.id.textView2);
        Button button1 = (Button) rootView.findViewById(R.id.button);
        Button button2 = (Button) rootView.findViewById(R.id.button2);
        Button button3 = (Button) rootView.findViewById(R.id.button3);

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        encrypt(v);
                        break;
                    case R.id.button2:
                        decrypt(v);
                        break;
                    case R.id.button3:
                        clear(v);
                        break;

                }
            }
        };

        button1.setOnClickListener(oclBtn);
        button2.setOnClickListener(oclBtn);
        button3.setOnClickListener(oclBtn);

        return rootView;
    }

    public void encrypt(View v)
    {
        String str = ed_1.getText().toString();
        if(!str.isEmpty())
        {
            resul = new char[str.length()];
            for (int i = 0; i < str.length(); i++ )
            {
                resul[i] = encode(str.charAt(i));
            }
            String result = new String(resul);
            tv.setText(result);
        }
    }

    public void decrypt(View v)
    {
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

    public char encode (char c)
    {
        char temp = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 7; j++)
            {
                if(c == table[i][j])
                {
                    if(i == 5 )
                        temp = table[0][j];
                    else
                        temp = table[i+1][j];
                }
            }
        return temp;
    }
    public  char decode (char c)
    {
        char temp = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 7; j++)
            {
                if(c == table[i][j])
                {
                    if(i == 0 )
                        temp = table[5][j];
                    else
                        temp = table[i-1][j];
                }
            }
        return temp;
    }

    public  void clear (View v)
    {
        tv.setText("");
        tv_2.setText("");
        ed_1.setText("");
    }

}
