package com.divakrishna.gamerandomwordssqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvYourScore;
    private EditText etYourName;
    private Button btnSave, btnHighScores, btnHome, btnPlayAgain;

    private DBSource dbSource;

    private String score = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        tvYourScore = findViewById(R.id.tv_your_score);
        etYourName = findViewById(R.id.et_your_name);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        btnHighScores = findViewById(R.id.btn_high_scores);
        btnHighScores.setOnClickListener(this);
        btnHome = findViewById(R.id.btn_home);
        btnHome.setOnClickListener(this);
        btnPlayAgain = findViewById(R.id.btn_play_again);
        btnPlayAgain.setOnClickListener(this);

        score = getIntent().getStringExtra("score");

        tvYourScore.setText(score);

        dbSource = new DBSource(this);
        dbSource.open();

        if(score.equals("0")){
            btnSave.setEnabled(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                String yourName = etYourName.getText().toString().toLowerCase();
                if (!yourName.isEmpty()){
                    dbSource.insertScore(yourName, score);
                    Toast.makeText(this, "Success to save your name", Toast.LENGTH_SHORT).show();
                    Intent highScores = new Intent(this, ScoreActivity.class);
                    finale();
                    startActivity(highScores);
                }else {
                    Toast.makeText(this, "Fill your name", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_high_scores:
                Intent highScores = new Intent(this, ScoreActivity.class);
                finale();
                startActivity(highScores);
                break;
            case R.id.btn_home:
                finish();
                break;
            case R.id.btn_play_again:
                Intent playAgain = new Intent(this, PlayActivity.class);
                finale();
                startActivity(playAgain);
                break;
        }
    }

    public void finale(){
        GameOverActivity.this.finish();
    }

    @Override
    protected void onResume() {
        dbSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dbSource.close();
        super.onPause();
    }
}
