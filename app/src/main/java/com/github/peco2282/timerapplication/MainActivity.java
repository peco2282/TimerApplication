package com.github.peco2282.timerapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  private TextView mTextViewCountDown;
  private Button mButtonStartPause, getmButtonReset;

  private CountDownTimer mCountDownTimer;

  private static final long START_TIME = 10000;
  private long mTimeLeftInMillis;
  private boolean mTimerRunning;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextViewCountDown = findViewById(R.id.textViewCountDown);
    mButtonStartPause = findViewById(R.id.buttonStartPause);
    getmButtonReset = findViewById(R.id.buttonReset);

    mButtonStartPause.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        System.out.println(mTimerRunning);
        if (mTimerRunning){
          pauseTimer();
        } else {
          startTimer();
        }
      }
    });

    getmButtonReset.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        resetTimer();
      }
    });

  }

  private void startTimer(){
    mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
      @Override
      public void onTick(long l) {
        mTimeLeftInMillis = l;
        updateCountDownText();
      }

      @Override
      public void onFinish() {
        mTimerRunning = false;
        mButtonStartPause.setText("スタート");
        getmButtonReset.setVisibility(View.INVISIBLE);
      }
    }.start();

    mTimerRunning = true;
    mButtonStartPause.setText("一時停止");
    getmButtonReset.setVisibility(View.INVISIBLE);
  }
  private void pauseTimer(){
    System.out.println("一時停止処理前");
    mCountDownTimer.cancel();
    mTimerRunning = false;
    System.out.println("一時停止処理完了");
    mButtonStartPause.setText("スタート");
    getmButtonReset.setVisibility(View.VISIBLE);
  }
  private void resetTimer(){
    mTimeLeftInMillis = START_TIME;
    updateCountDownText();
    mButtonStartPause.setVisibility(View.VISIBLE);
    getmButtonReset.setVisibility(View.INVISIBLE);
  }
  private void updateCountDownText(){
    int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
    int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
  }
}