package com.svidersky.homework.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.svidersky.homework.R;

/**
 * Created by Eren on 19.10.2014.
 */
public class Fragment_1 extends Fragment {

   private char [] resul;
   public static char table [][] = new char[][] {{'й','м','о','в','і','р','н'},
                                                  {'а','б','г','д','е','ё','ж'},
                                                  {'з','и','к','л','п','с','т'},
                                                  {'у','ф','х','ц','ч','ш','щ'},
                                                  {'ъ','ы','ь','э','ю','я','ґ'},
                                                  {'є','ї','!','?',',','.',' '}};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_1, null);
        Button button = (Button) v.findViewById(R.id.button);
        final EditText ed_1 = (EditText) v.findViewById(R.id.editText);
        final TextView tv = (TextView) v.findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
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
        });
        return v;
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
}
