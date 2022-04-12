package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class score extends AppCompatActivity {
    TextView tv;
    int i;
    int score;
    private ProgressBar progressBar;
    private TextView progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tv= (TextView) findViewById(R.id.score);
        progressBar=(ProgressBar) findViewById(R.id.progress_bar);

        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);
        progressText.setText(global.score*50+"%");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i*50 <= global.score*50) {

                    progressBar.setProgress(i*50);
                    i++;
                    handler.postDelayed(this, global.score*50);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, global.score*50);

        Intent intent=getIntent();
        score =intent.getIntExtra("score", 0);
        progressBar.setProgress(100*score/3);
        tv.setText(100*score/3+"%");
    }
}