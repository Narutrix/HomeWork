package com.svidersky.homework;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class Listfragment extends android.app.ListFragment {

    ArrayList arrayList = new ArrayList();
    String s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone._ID,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);

        if (cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                s = cursor.getString(1) + "  tel:" + cursor.getString(2);
                arrayList.add(s);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, arrayList);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        s = arrayList.get(position).toString();
        int i = s.lastIndexOf("tel:");
        String  str = s.substring(i+4, s.length());

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + str));
        startActivity(smsIntent);
    }

}
