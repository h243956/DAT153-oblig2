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

  private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
  private static final String TAG = "DatabaseActivity";

  private RecyclerView databaseRecyclerView;
  private DatabaseRecyclerViewAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_database);
    ArrayList<Picture> pictures = this.createPictures();

    adapter = new DatabaseRecyclerViewAdapter(this);
    databaseRecyclerView = (RecyclerView) findViewById(R.id.databaseRecyclerView);
    Toast.makeText(getApplicationContext(), "creating images", Toast.LENGTH_LONG).show();

    databaseRecyclerView.setAdapter(adapter);
    databaseRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    adapter.setPictures(pictures);
  }

  public ArrayList<Picture> createPictures() {
    ArrayList<Picture> pictures = new ArrayList<Picture>();
    this.saveImage("https://upload.wikimedia.org/wikipedia/commons/8/82/Damon_cropped.jpg", "damon");
    pictures.add(new Picture("damon", "Matt Damon"));
    this.saveImage("https://upload.wikimedia.org/wikipedia/commons/b/bd/Glasto17-44_%2835547413626%29_Cropped.jpg", "cooper");
    pictures.add(new Picture("cooper", "Bradley Cooper"));
    this.saveImage("https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Kevin_Smith_%2848477230947%29_%28cropped%29.jpg/1024px-Kevin_Smith_%2848477230947%29_%28cropped%29.jpg", "smith");
    pictures.add(new Picture("smith", "Kevin Smith"));
    return pictures;
  }

  public void saveImage(String url, final String filename) {
    Glide.with(this).asBitmap().load(url).into(new CustomTarget<Bitmap>() {
      @Override
      public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        try {
          File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());
          if(!myDir.exists()) {
            myDir.mkdirs();
          }
          String fileUri = myDir.getAbsolutePath() + "/" + filename + ".jpg";
          FileOutputStream outputStream = new FileOutputStream(fileUri);
          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
          outputStream.flush();
          outputStream.close();
        } catch(IOException e) {
          e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_LONG).show();

      }
      @Override
      public void onLoadCleared(Drawable placeholder) {
      }
    });
  }


}