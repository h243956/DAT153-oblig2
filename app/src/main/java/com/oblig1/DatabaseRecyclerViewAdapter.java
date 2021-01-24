package com.oblig1;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DatabaseRecyclerViewAdapter extends RecyclerView.Adapter<DatabaseRecyclerViewAdapter.ViewHolder> {
  private static final String TAG = "DatabaseRecyclerViewAda";

  private ArrayList<Picture> pictures = new ArrayList<>();
  private Context mContext;

  public DatabaseRecyclerViewAdapter(Context mContext) {
    this.mContext = mContext;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.database_item_picture, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
    Log.d(TAG, "onBindViewHolder: Called");

    // get picture name and display
    holder.pictureNameText.setText(pictures.get(position).getName());

    // get picture file and display
    Glide.with(mContext)
            .asBitmap()
            .load(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + pictures.get(position).getFilename() + ".jpg")
            .into(holder.pictureImageView);

    holder.parent.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(mContext, pictures.get(position).getName() + " selected", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return pictures.size();
  }

  public void setPictures(ArrayList<Picture> pictures) {
    this.pictures = pictures;
    notifyDataSetChanged();
  }



  public class ViewHolder extends RecyclerView.ViewHolder {

    private CardView parent;
    private ImageView pictureImageView;
    private TextView pictureNameText;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      parent = (CardView) itemView.findViewById(R.id.parent);
      pictureImageView = (ImageView) itemView.findViewById(R.id.pictureImage);
      pictureNameText = (TextView) itemView.findViewById(R.id.pictureText);
    }

  }

}
