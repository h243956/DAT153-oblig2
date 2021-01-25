package com.oblig1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.oblig1.MainActivity;
import com.oblig1.R;
import com.oblig1.entities.Picture;
import com.oblig1.entities.Quiz;
import com.oblig1.repository.Repository;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

public class QuizActivity extends AppCompatActivity {

  private Quiz quiz;
  private Repository repository;
  private TextView progressTextView, resultTextView;
  private Button nextQuizItemButton, finishQuizButton;
  private ImageView quizItemImageView;
  private TextInputEditText answerNameInputText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz);
    initializeView();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflate = getMenuInflater();
    inflate.inflate(R.menu.menu_quiz, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId()==R.id.homeQuizMenuItem) {
      startActivity(new Intent(this, MainActivity.class));
      return true;
    } else if(item.getItemId()==R.id.restartQuizMenuItem) {
      startActivity(new Intent(this, QuizActivity.class));
      return true;
    } else {
      return super.onOptionsItemSelected(item);
    }
  }

  public void initializeView() {
    repository = Repository.getInstance(this);
    quiz = new Quiz(repository.getPictures());
    progressTextView = (TextView) findViewById(R.id.progressTextView);
    resultTextView = (TextView) findViewById(R.id.resultTextView);
    nextQuizItemButton = (Button) findViewById(R.id.nextQuizItemButton);
    finishQuizButton = (Button) findViewById(R.id.finishQuizButton);
    quizItemImageView = (ImageView) findViewById(R.id.quizItemImageView);
    answerNameInputText = (TextInputEditText) findViewById(R.id.answerNameInputText);
    progressTextView.setText(quiz.getProgressToString());

    answerNameInputText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
          hideKeyboard(v);
        }
      }
    });

    nextQuizItemButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        quiz.checkAnswer(answerNameInputText.getText().toString());
        quiz.incrementCurrentIndex();
        progressTextView.setText(quiz.getProgressToString());
        answerNameInputText.setText("");
        setQuizItemImage();
        handleQuizState();
      }
    });

    finishQuizButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        quiz.checkAnswer(answerNameInputText.getText().toString());
        resultTextView.setText(quiz.getResultsToString());
        quiz.setRunning(false);
        handleQuizState();
      }
    });
    setQuizItemImage();
    handleQuizState();
  }

  public void handleQuizState() {
    if(!quiz.isRunning()) {
      finishQuizButton.setVisibility(View.INVISIBLE);
      nextQuizItemButton.setVisibility(View.INVISIBLE);
      progressTextView.setVisibility(View.INVISIBLE);
      answerNameInputText.setVisibility(View.INVISIBLE);
      quizItemImageView.setVisibility(View.INVISIBLE);
      resultTextView.setVisibility(View.VISIBLE);
    } else {
      if(quiz.getCurrentIndex() == (quiz.getSize() - 1)) {
        nextQuizItemButton.setVisibility(View.INVISIBLE);
        finishQuizButton.setVisibility(View.VISIBLE);
      } else {
        finishQuizButton.setVisibility(View.INVISIBLE);
        nextQuizItemButton.setVisibility(View.VISIBLE);
      }
      resultTextView.setVisibility(View.INVISIBLE);
      quizItemImageView.setVisibility(View.VISIBLE);
      progressTextView.setVisibility(View.VISIBLE);
      answerNameInputText.setVisibility(View.VISIBLE);
    }
  }

  public void setQuizItemImage() {
    Glide.with(this)
            .asBitmap()
            .load(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + quiz.getCurrentQuizItem().getFilename() + ".jpg")
            .into(quizItemImageView);
  }

  public void hideKeyboard(View view) {
    InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }

}