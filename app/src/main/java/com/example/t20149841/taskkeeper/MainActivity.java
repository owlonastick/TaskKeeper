package com.example.t20149841.taskkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button taskButton;
    EditText taskText;
    public final static String EXTRA_MESSAGE = "com.example.t20149841.taskkeeper.TASK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskButton = (Button)findViewById(R.id.taskButton);
        taskText = (EditText)findViewById(R.id.taskText);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                String task = taskText.getText().toString(); //Get text from the task
                intent.putExtra( EXTRA_MESSAGE, task); //Put that in the new intent
                startActivity(intent);
            }
        });
    }
}
