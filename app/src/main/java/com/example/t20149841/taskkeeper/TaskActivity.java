package com.example.t20149841.taskkeeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TaskActivity extends AppCompatActivity {

    TextView messageView;
    ListView taskList;
    ArrayList <String> tasks = new ArrayList<>();
    ArrayAdapter<String> adapter;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Set<String> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        messageView = (TextView)findViewById(R.id.messageView);
        taskList = (ListView)findViewById(R.id.taskList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks);
        taskList.setAdapter(adapter);

        loadTasks();

        Intent intent = getIntent(); //Get an intent object
        String task = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        messageView.setText(task);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CharSequence text = "Task: "+tasks.get(position);
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(TaskActivity.this, text, duration);
                toast.show();
                //Homework - remove an element that you clicked on.  Perhaps replace the Toast with a removal.
            }
        });

        tasks.add(task);
        Collections.sort(tasks);

        adapter.notifyDataSetChanged();
        //Save tasks to shared preferences and on startup, load them from same file

        saveTasks();
    }

    private void loadTasks() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        set = prefs.getStringSet("tasks", null);
        if (set!=null){
            tasks.addAll(set);
            Collections.sort(tasks);

        }
    }

    private void saveTasks() {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);         //Get handle to the preference file

        editor = prefs.edit();         //Create editor

        set = new HashSet<String>();
        set.addAll(tasks);
        editor.putStringSet("tasks", set);
        editor.apply();        //Commit

    }
}
