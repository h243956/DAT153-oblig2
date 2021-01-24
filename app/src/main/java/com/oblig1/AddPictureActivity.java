package com.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AddPictureActivity extends AppCompatActivity {

  private Button addFromDeviceButton;
  private TextView nameText, nameInputText;
  private ImageView fromDeviceImageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_picture);
    this.initializeViews();
  }

  public void initializeViews() {
    this.addFromDeviceButton = (Button) findViewById(R.id.addFromDeviceButton);
    this.nameText = (TextView) findViewById(R.id.nameText);
    this.nameInputText = (TextView) findViewById(R.id.nameInputText);
    this.fromDeviceImageView = (ImageView) findViewById(R.id.fromDeviceImageView);
  }
}