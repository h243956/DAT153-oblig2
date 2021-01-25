package com.oblig1.entities;

import android.content.Context;
import android.util.Log;

import com.oblig1.repository.Repository;

import java.util.ArrayList;

public class Quiz {


  private ArrayList<Picture> quizItems;
  private int currentIndex, countCorrectAnswers, countWrongAnswers, currentState;
  private boolean isRunning;

  public Quiz(ArrayList<Picture> pictures) {
    quizItems=pictures;
    restart();
  }

  public void restart() {
    currentState = 1;
    currentIndex = 0;
    countCorrectAnswers = 0;
    countWrongAnswers = 0;
    isRunning=true;
  }

  public int getSize() {
    return quizItems.size();
  }

  public boolean checkAnswer(String answerName) {
    if(quizItems.get(currentIndex).getName().toLowerCase().equals(answerName.toLowerCase())) {
      countCorrectAnswers++;
      return true;
    } else {
      countWrongAnswers++;
      return false;
    }
  }

  public int getCurrentIndex() {
    return currentIndex;
  }

  public int getCountCorrectAnswers() {
    return countCorrectAnswers;
  }

  public int getCountWrongAnswers() {
    return countWrongAnswers;
  }

  public int getCurrentState() {
    return currentIndex;
  }

  public void incrementCurrentIndex() {
    currentIndex++;
  }

  public Picture getCurrentQuizItem() {
    return quizItems.get(currentIndex);
  }

  public boolean isRunning() {
    return isRunning;
  }

  public void setRunning(boolean running) {
    isRunning = running;
  }

  public String getResultsToString() {
    String countAnswers=Integer.toString(getSize());
    String correctAnswers=Integer.toString(countCorrectAnswers);
    return "You answered " + correctAnswers + "/" + countAnswers + " correctly!";
  }

  public String getProgressToString() {
    String doneQuizItems=Integer.toString(currentIndex+1);
    String totalQuizItems=Integer.toString(getSize());
    return "On picture " + doneQuizItems + "/" + totalQuizItems;
  }
}
