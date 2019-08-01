package com.divakrishna.gamerandomwordssqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private DBSource dbSource;

    private ArrayList<Words> values;

    private TextView tvQuestion;
    private EditText etAnswer;
    private Button btnNext;

    private int valuesLength;
    private String answer;
    private int count = 0;
    private int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        tvQuestion = findViewById(R.id.tv_question);
        etAnswer = findViewById(R.id.et_answer);
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);

        dbSource = new DBSource(this);
        dbSource.open();

        values = dbSource.getAllWord();

        valuesLength = values.size();

        NextWord(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:

                    String answering = etAnswer.getText().toString().toLowerCase();
                    if(answering.equals(answer)){
                        Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT).show();
                        etAnswer.getText().clear();
                        score += 10;
                        count += 1;
                        NextWord(count);
                    }
                    else{
                        Toast.makeText(this, "You are wrong", Toast.LENGTH_SHORT).show();
                        etAnswer.getText().clear();
                        count += 1;
                        NextWord(count);
                    }

                break;
        }
    }

    private void NextWord(int i){
        if(count < valuesLength){
            String question = values.get(i).getQuestion().substring(0, 1).toUpperCase() + values.get(i).getQuestion().substring(1);
            tvQuestion.setText(question);
            answer = values.get(i).getAnswer().toLowerCase();
        }
        else{
            Intent gameOver = new Intent(this, GameOverActivity.class);
            gameOver.putExtra("score", String.valueOf(score));
            startActivity(gameOver);
        }
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
