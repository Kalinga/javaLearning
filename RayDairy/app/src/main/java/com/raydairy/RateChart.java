package com.raydairy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class RateChart extends AppCompatActivity {

    ListView listView;
    ArrayList<UserData> userDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_chart);
            userDataList = new ArrayList<>();
            UserData ud = new UserData(3.0, 4.0, 22.35);
            UserData ud1 = new UserData(3.0, 4.5, 22.35);
            userDataList.add(ud);
            userDataList.add(ud1);
        }
    }