package com.divakrishna.gamerandomwordssqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    private DBSource dbSource;

    private RecyclerView rvScores;
    private RecyclerView.Adapter adpScores;
    private RecyclerView.LayoutManager lmScores;

    private ArrayList<Scores> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        dbSource = new DBSource(this);
        dbSource.open();

        values = dbSource.getAllScore();

        rvScores = findViewById(R.id.rv_scores);

        lmScores = new LinearLayoutManager(this);
        rvScores.setLayoutManager(lmScores);
        rvScores.setHasFixedSize(true);

        adpScores = new ScoresAdapter(values);
        rvScores.setAdapter(adpScores);
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
