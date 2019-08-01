package com.divakrishna.gamerandomwordssqlite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ViewHolder> {

    private ArrayList<Scores> values;

    ScoresAdapter(ArrayList<Scores>  values){
        this.values = values;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNo, tvName, tvScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNo = itemView.findViewById(R.id.tv_no);
            tvName = itemView.findViewById(R.id.tv_name);
            tvScore = itemView.findViewById(R.id.tv_score);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_score, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,@SuppressWarnings("RecyclerView") final int i) {
        final String no = String.valueOf(i+1);
        final String name = values.get(i).getName();
        final String score = values.get(i).getScore();

        viewHolder.tvNo.setText(no);
        viewHolder.tvName.setText(name);
        viewHolder.tvScore.setText(score);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
