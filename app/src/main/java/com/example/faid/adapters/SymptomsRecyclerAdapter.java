package com.example.faid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faid.R;
import com.example.faid.models.Symptom;

import java.util.ArrayList;

public class SymptomsRecyclerAdapter extends  RecyclerView.Adapter<SymptomsRecyclerAdapter.ViewHolder> {

    private ArrayList<Symptom> mSymptoms = new ArrayList<>();

    public SymptomsRecyclerAdapter(ArrayList<Symptom> mSymptoms) {
        this.mSymptoms = mSymptoms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.symptom_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.symptom.setText(mSymptoms.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return mSymptoms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView symptom;
        TextView id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symptom = itemView.findViewById(R.id.symptom);
            id = itemView.findViewById(R.id.ID);
        }
    }
}
