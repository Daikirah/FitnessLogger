package com.example.joshu.fitnesslogger.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joshu.fitnesslogger.model.Exercise;
import com.example.joshu.fitnesslogger.model.ExerciseViewModel;
import com.example.joshu.fitnesslogger.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private List<Exercise> mExercises;
    private final OnItemClickListener listener;
    private ExerciseViewModel exerciseViewModel;
    private Context context;

    public ExerciseAdapter(List<Exercise> mExercises, OnItemClickListener listener) {
        this.mExercises = mExercises;
        this.listener = listener;
        exerciseViewModel = new ExerciseViewModel(context);
    }

    public interface OnItemClickListener{
        void onItemClick (Exercise exercise);
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(int position);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_item, null);
        ViewHolder vh = new ExerciseAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int i) {
        final Exercise exercise = mExercises.get(i);
        viewholder.title.setText(exercise.getTitle());
        viewholder.reps.setText(exercise.getReps());
        viewholder.weight.setText(exercise.getWeight());
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(exercise);

            }
        });

        viewholder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title, reps, weight;
        public View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.exercise_title);
            reps = itemView.findViewById(R.id.exercise_reps);
            weight = itemView.findViewById(R.id.exercise_weight);
        }

    }

    public void swapList (List<Exercise> newList){

        mExercises = newList;
        if (newList != null){
            this.notifyDataSetChanged();
        }
    }
}
