package com.raydairy;

/**
 * Created by Mitch on 2016-05-13.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter extends ArrayAdapter<UserData> {

    private LayoutInflater mInflater;
    private int mViewResourceId;
    private ArrayList<UserData> mUserData;
    private static final String TAG = "RAYActivity";

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<UserData> usersData) {
        super(context, textViewResourceId, usersData);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
        mUserData = usersData;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        Log.v(TAG, Integer.toString(position));
        TextView fat = (TextView) convertView.findViewById(R.id.textFAT);
        TextView snf = (TextView) convertView.findViewById(R.id.textSNF);
        TextView price = (TextView) convertView.findViewById(R.id.textPrice);

        if (fat != null) {
            Log.v(TAG, mUserData.get(position).getFat());

            fat.setText(mUserData.get(position).getFat());
        }
        if (snf != null) {
            snf.setText(mUserData.get(position).getSNF());
        }
        if (price != null) {
            price.setText(mUserData.get(position).getPrice());
        }

        return convertView;
    }
}