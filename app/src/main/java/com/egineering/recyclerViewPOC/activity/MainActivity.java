package com.egineering.recyclerViewPOC.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.egineering.recyclerViewPOC.R;
import com.egineering.recyclerViewPOC.fragments.TimeEntriesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTimeEntriesFragment();
    }

    private void addTimeEntriesFragment() {
        Fragment timeEntriesFragment = TimeEntriesFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, timeEntriesFragment)
                .commit();
    }

}
