package com.example.perry.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String TAG = "Braver";
    private FragmentTransaction transaction;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button searchButton = (Button) findViewById(R.id.searchBut);
        final EditText editText = (EditText) findViewById(R.id.editText);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        //default fragment...
        SearchFragment searchFrag = new SearchFragment();
        transaction.replace(R.id.frameContainer, searchFrag, "searchFrag");
        transaction.commit();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().length() !=0) { // it's not empty
                    ResultsFragment resultFrag = new ResultsFragment();
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.frameContainer, resultFrag, "ResultFragment");
                    transaction.commit();
                } else  {
                    Toast.makeText(getApplicationContext(), "検索したいことを記入してください", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(1, Menu.FIRST, Menu.FIRST, "Hello");
        menu.add(1, Menu.FIRST+1, Menu.FIRST+1, "Hello2");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id) {
            case R.id.action_settings:
                Log.i(TAG, "settings");
                Toast.makeText(getApplicationContext(), "彼氏", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_info:
                Log.i(TAG, "info");
                Toast.makeText(getApplicationContext(),"彼女", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_random:
                Log.i(TAG, "info");
                Toast.makeText(getApplicationContext(),"一緒", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
