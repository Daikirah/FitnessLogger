package com.example.joshu.fitnesslogger.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.joshu.fitnesslogger.model.Exercise;
import com.example.joshu.fitnesslogger.model.ExerciseViewModel;
import com.example.joshu.fitnesslogger.R;

public class AddExerciseActivity extends AppCompatActivity {

    private TextInputEditText titleInput, repsInput, weightInput;
    private String titleString, repsString, weightString;
    ExerciseViewModel exerciseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_exercise);

        titleInput = findViewById(R.id.add_exercise_title_text);
        repsInput = findViewById(R.id.add_exercise_reps_text);
        weightInput = findViewById(R.id.add_exercise_weight_text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exercise exercise;
                titleString = titleInput.getText().toString();
                repsString = repsInput.getText().toString();
                weightString = repsInput.getText().toString();

                exercise = new Exercise(titleString, repsString, weightString);
                exerciseViewModel = new ExerciseViewModel(getApplicationContext());
                exerciseViewModel.insert(exercise);
                Intent intent = new Intent(AddExerciseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
