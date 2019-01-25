package com.example.joshu.fitnesslogger.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.joshu.fitnesslogger.model.Exercise;
import com.example.joshu.fitnesslogger.model.ExerciseViewModel;
import com.example.joshu.fitnesslogger.R;

public class EditExerciseActivity extends AppCompatActivity {

    TextInputEditText mTitle, mReps, mWeight;
    String titleString, repsString, weightString;
    Exercise updatedExercise;
    ExerciseViewModel mExerciseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        final Exercise exercise = getIntent().getParcelableExtra(MainActivity.EDIT_EXERCISE);
        mTitle = findViewById(R.id.edit_exercise_title_text);
        mReps = findViewById(R.id.edit_exercise_reps_text);
        mWeight = findViewById(R.id.edit_exercise_weight_text);

        mTitle.setText(exercise.getTitle());
        mReps.setText(exercise.getReps());
        mWeight.setText(exercise.getWeight());

        FloatingActionButton fab = findViewById(R.id.edit_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedExercise = exercise;
                updatedExercise.setTitle(mTitle.getText().toString());
                updatedExercise.setReps(mReps.getText().toString());
                updatedExercise.setWeight(mWeight.getText().toString());

                mExerciseViewModel = new ExerciseViewModel(getApplicationContext());
                mExerciseViewModel.update(updatedExercise);
                Intent intent = new Intent(EditExerciseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
