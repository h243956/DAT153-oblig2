package com.oblig1.repository;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.oblig1.entities.Picture;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * For now a static singleton class to be shared throughout the app
 * Later can be used for db connection
 * If later we use multiple threads to fetch things, the getInstance needs to be synchronized
 */
public class Repository {

  private static Repository instance;
  private static ArrayList<Picture> pictures;
  private static Context current_context;

  private Repository() {
    if (pictures == null) {
      initializePictures();
    }
  }

  public static Repository getInstance(Context context) {
    current_context = context;
    if (instance != null) {
      return instance;
    } else {
      return new Repository();
    }
  }

  public static ArrayList<Picture> getPictures() {
    return pictures;
  }

  public static void addPicture(Picture picture) {
    pictures.add(picture);
  }

  public static void removePictureByIndex(int index) {
    pictures.remove(index);
  }

  private static void initializePictures() {
    pictures = new ArrayList<Picture>();

    // Pictures that we can use in app from startup
    saveImage("https://upload.wikimedia.org/wikipedia/commons/8/82/Damon_cropped.jpg", "damon");
    addPicture(new Picture("damon", "Matt Damon"));
    saveImage("https://upload.wikimedia.org/wikipedia/commons/b/bd/Glasto17-44_%2835547413626%29_Cropped.jpg", "cooper");
    addPicture(new Picture("cooper", "Bradley Cooper"));
    saveImage("https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Kevin_Smith_%2848477230947%29_%28cropped%29.jpg/1024px-Kevin_Smith_%2848477230947%29_%28cropped%29.jpg", "smith");
    addPicture(new Picture("smith", "Kevin Smith"));

    // Pictures we can add to app
    saveImage("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Tom_Hanks_TIFF_2019.jpg/1024px-Tom_Hanks_TIFF_2019.jpg", "hanks");
    saveImage("https://upload.wikimedia.org/wikipedia/commons/8/8d/George_Clooney_2016.jpg", "clooney");
  }

  private static void saveImage(String url, final String filename) {
    Glide.with(current_context).asBitmap().load(url).into(new CustomTarget<Bitmap>() {
      @Override
      public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        try {
          File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());
          if (!myDir.exists()) {
            myDir.mkdirs();
          }
          String fileUri = myDir.getAbsolutePath() + "/" + filename + ".jpg";
          FileOutputStream outputStream = new FileOutputStream(fileUri);
          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
          outputStream.flush();
          outputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onLoadCleared(Drawable placeholder) {
      }
    });
  }
}
