package com.example.faid.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faid.DiagnosisActivity;
import com.example.faid.R;
import com.example.faid.SymptomActivity;
import com.example.faid.models.Symptom;

import java.util.ArrayList;

public class SymptomsRecyclerAdapter extends  RecyclerView.Adapter<SymptomsRecyclerAdapter.ViewHolder> {

    private ArrayList<Symptom> mSymptoms;
    int n=0;

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
        viewHolder.id.setText(mSymptoms.get(i).getID());
        viewHolder.bind(mSymptoms.get(i));
    }

    @Override
    public int getItemCount() {
        return mSymptoms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView symptom;
        TextView id;
        CheckBox chk;
        LinearLayout listlinear;
        ImageView check;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symptom = itemView.findViewById(R.id.symptom);
            id = itemView.findViewById(R.id.ID);
            listlinear = itemView.findViewById(R.id.listlinear);
            check = itemView.findViewById(R.id.check);
        }

        void bind(final Symptom symp) {
            check.setVisibility(symp.isSelect() ? View.VISIBLE : View.GONE);
            symptom.setText(symp.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    symp.setSelect(!symp.isSelect());
                    check.setVisibility(symp.isSelect() ? View.VISIBLE : View.GONE);
                }
            });
        }
    }

    public ArrayList<Symptom> getAll() {
        return mSymptoms;
    }

    public ArrayList<Symptom> getSelected() {
        ArrayList<Symptom> selected = new ArrayList<>();
        if (mSymptoms.size()==0) {
            n=1;
        }
        else {
            for (int i = 0; i < 281; i++) {
                if (mSymptoms.get(i).isSelect()) {
                    selected.add(mSymptoms.get(i));
                } else {
                    n = 1;
                }
            }
        }
        return selected;
    }
}


