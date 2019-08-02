package com.divakrishna.gamerandomwordssqlite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private DBSource dbSource;

    private ArrayList<Words> values;

    private TextView tvQuestion;
    private EditText etAnswer;
    private Button btnNext;
    private LinearLayout linearLayout;

    private int valuesLength;
    private String answer;
    private int count = 0;
    private int score = 0;

    private String[] keys;

    private String moreKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        tvQuestion = findViewById(R.id.tv_question);
        etAnswer = findViewById(R.id.et_answer);
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);

        linearLayout = findViewById(R.id.layout_parent);

        dbSource = new DBSource(this);
        dbSource.open();

        values = dbSource.getAllWord();

        valuesLength = values.size();

        NextWord(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                String answering = etAnswer.getText().toString().toLowerCase();
                if (answering.equals(answer)) {
                    Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT).show();
                    etAnswer.getText().clear();
                    score += 10;
                    count += 1;
                    NextWord(count);
                } else {
                    Toast.makeText(this, "You are wrong", Toast.LENGTH_SHORT).show();
                    etAnswer.getText().clear();
                    count += 1;
                    NextWord(count);
                }

                break;
        }
    }

    private void NextWord(int i) {
        if (count < valuesLength) {
            String question = values.get(i).getQuestion().substring(0, 1).toUpperCase() + values.get(i).getQuestion().substring(1);
            tvQuestion.setText(question);
            answer = values.get(i).getAnswer().toLowerCase();

            moreKeys = answer+"akdb";
            keys = moreKeys.split("(?!^)");
            keys = shuffleArray(keys);

            linearLayout.removeAllViews();
            for (String key : keys) {
                addView(((LinearLayout) findViewById(R.id.layout_parent)), key, ((EditText) findViewById(R.id.et_answer)));
            }
        } else {
            Intent gameOver = new Intent(this, GameOverActivity.class);
            gameOver.putExtra("score", String.valueOf(score));
            startActivity(gameOver);
        }
    }

    private String[] shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    private void addView(LinearLayout viewParent, final String text, final EditText editText) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        linearLayoutParams.rightMargin = 20;

        final TextView textView = new TextView(this);

        textView.setLayoutParams(linearLayoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.setText(editText.getText().toString() + text);
                textView.animate().alpha(0).setDuration(300);
            }
        });
        viewParent.addView(textView);
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
