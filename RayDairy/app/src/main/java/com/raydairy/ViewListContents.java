package com.raydairy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mitch on 2016-05-13.
 */
public class ViewListContents extends AppCompatActivity {

    ListView listView;
    ArrayList<UserData> userDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);
            userDataList = new ArrayList<>();
            UserData ud = new UserData(3.0, 4.0, 22.35);
            UserData ud1 = new UserData(3.0, 4.5, 22.35);
            userDataList.add(ud);
            userDataList.add(ud1);
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,
                                                R.layout.activity_fat_snf_price, userDataList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }