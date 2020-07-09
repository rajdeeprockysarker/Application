package com.raj.application.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.raj.application.R;
import com.raj.application.db.AppDatabase;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    String getValue;

    @Inject
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("", getValue);

        Fragment mFragment = null;
        mFragment = new ListFragment(db);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, mFragment).commit();

    }

    public void jumpAddEditFragment(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);


        Fragment mFragment = null;
        mFragment = new AddEditFragment(db);
        mFragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, mFragment).commit();
    }

    public void jumpListFragment() {

        Fragment mFragment = null;
        mFragment = new ListFragment(db);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, mFragment).commit();
    }


}