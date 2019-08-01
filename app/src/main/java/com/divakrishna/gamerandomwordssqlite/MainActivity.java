package com.divakrishna.gamerandomwordssqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlay, btnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(this);
        btnScore = findViewById(R.id.btn_score);
        btnScore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                Intent play = new Intent(this, PlayActivity.class);
                startActivity(play);
                break;
            case R.id.btn_score:
                Intent score = new Intent(this, ScoreActivity.class);
                startActivity(score);
                break;
        }
    }
}
