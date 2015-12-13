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
 *    Learn Retrofit
 * 9) Check out my Evernote with ALLLLL of the r/androiddev
 * 10) Learn how to fix leaking Activities (AsyncTask prob,,,,)
 * 11) Work on transitions between Activities
 * 12) Implement MVC pattern properly
 * 13) Write more comments??
 * 14) Change the theme for my ultrabooks Android Studios
 * 15) Learn GIT for Visual Studio Code
 * 16) Add "Email developer" in About Activitiy
 * 18) Prepare the Github README for this
 * 19) If MVC or MVP is not used in real life so much, focus on seperating the Activity/Fragments (View) and the Logic (Model)
 *      Source: https://www.reddit.com/r/androiddev/comments/3frcwd/how_many_of_you_use_mvcmvp_in_your_work_projects/
 * 20) Think about changing the background color or Tab bar colors
 * 21) Along with sharedPreferences, we can also use, intent.putExtra("result", result);
 * 22) Read about the many ways and situations where Activities would restart:
 *      Link:  http://stackoverflow.com/questions/7818717/why-not-use-always-androidconfigchanges-keyboardhiddenorientation
 * 23) Work on:
 *        onCreate, onStart, onRestart, onResume, onFreeze, onPause, onStop, onDestroy, onAttach, onDetach
 * 24) Should each class have a TAG for log console
 * 25) How to use onSaveInstanceState and Bundle between Activities and Fragments
 * 26)
 * 27) Reading about threads - http://android-developers.blogspot.in/2009/05/painless-threading.html
 * 28) Why transaction.addToBackStack(null);  for fragments?????
 *     Explanation: http://sapandiwakar.in/replacing-fragments/
 * 29) Nice example - https://gist.github.com/daichan4649/2480065
 * 30) If the user hits BACK during retrieving JSON data, please destroy the thread
 * 31) Find a way to count how many threads are active to avoid many of the same thread running
 *
 * BIG THINGS TO DO:
 * -Where do I put Async? In the Main Activity? In the fragment?
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
        // Our default fragment on startup is SearchFragment
        SearchFragment searchFrag = new SearchFragment();
        transaction.replace(R.id.frameContainer, searchFrag, "searchFrag");
        transaction.commit();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().length() !=0) { // it's not empty
                    SearchFragment resultFrag = new SearchFragment();
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.frameContainer, resultFrag, "SearchFragment");
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
