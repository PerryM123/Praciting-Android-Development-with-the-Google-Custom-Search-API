package com.example.perry.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Things to do:
 * 1) After making commits, write reports
 * 2) Make a SettingsActivity with a library called SettingsActivity (google it)
 * 3) Learn to make branches? instead of committing to the master?
 * 4) Watch video about making nice and flexible relative layout Views
 * 5) Finish learning Fragment's onAttach, onDetach, fragment stack
 * 6) Learn how to implement the JSON from google custom search
 * 7) If I hit Settings on the Action bar and I hit the phone's leftmost button, problem....
 * 8) Learn RecycleView
 * 9) Check out my Evernote with ALLLLL of the r/androiddev
 * 10) Learn how to fix leaking Activities (AsyncTask prob,,,,)
 * 11) Work on transitions between Activities
 * 12) Implement MVC pattern properly
 * 13) Write more comments??
 * 14) Change the theme for my ultrabooks Android Studios
 * 15) Learn GIT for Visual Studio Code
 * 16) Add "Email developer" in About Activitiy
 * 17) Add sounds?
 * 18) Prepare the Github README for this
 * 19)
 * 20)
 *
 */

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
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
