package com.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button quizButton, databaseButton, addButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.initializeView();

    databaseButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
        startActivity(intent);
      }
    });

  }
  private void initializeView() {
    quizButton = (Button) findViewById(R.id.quizButton);
    databaseButton = (Button) findViewById(R.id.databaseButton);
    addButton = (Button) findViewById(R.id.addButton);
  }
}