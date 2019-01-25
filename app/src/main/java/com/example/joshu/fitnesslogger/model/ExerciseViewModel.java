package com.example.joshu.fitnesslogger.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.joshu.fitnesslogger.repository.ExerciseRepository;

import java.util.List;

public class ExerciseViewModel extends ViewModel {

    private ExerciseRepository mExerciseRepository;
    private LiveData<List<Exercise>> mExercises;

    public ExerciseViewModel(Context context){
        mExerciseRepository = new ExerciseRepository(context);
        mExercises = mExerciseRepository.getAllExercises();
    }

    public LiveData<List<Exercise>> getExercises(){
        return mExercises;
    }

    public void insert (Exercise exercise){
        mExerciseRepository.insert(exercise);
    }

    public void update (Exercise exercise){
        mExerciseRepository.update(exercise);
    }

    public void delete (Exercise exercise){
        mExerciseRepository.delete(exercise);
    }


}
