package com.oblig1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

  private static final String TAG = "DatabaseActivity";
  private Repository repository;

  private RecyclerView databaseRecyclerView;
  private DatabaseRecyclerViewAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_database);
    this.repository=Repository.getInstance(this);

    adapter = new DatabaseRecyclerViewAdapter(this);
    databaseRecyclerView = (RecyclerView) findViewById(R.id.databaseRecyclerView);

    databaseRecyclerView.setAdapter(adapter);
    databaseRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    adapter.setPictures(this.repository.getPictures());
  }




}