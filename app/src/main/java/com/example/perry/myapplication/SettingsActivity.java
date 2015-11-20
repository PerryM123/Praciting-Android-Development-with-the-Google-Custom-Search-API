package com.example.perry.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
// check
public class SettingsActivity extends AppCompatActivity { // extends Activity is better??
    ArrayAdapter<String> adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // do I need this????
        setSupportActionBar(toolbar); // do I need this????

        setUpSettings();
        listView = (ListView) findViewById(R.id.settingsListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void setUpSettings() {
        String[] list = new String[]{"About","Link to developer"};
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);

    }

}
