package com.example.joshu.fitnesslogger.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.joshu.fitnesslogger.model.Exercise;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExerciseRepository {

    private ExerciseDatabase mExerciseDatabase;
    private ExerciseDao mExerciseDao;
    private LiveData<List<Exercise>> mExercises;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public ExerciseRepository(Context context){
        mExerciseDatabase = ExerciseDatabase.getDbInstance(context);
        mExerciseDao = mExerciseDatabase.exerciseDao();
        mExercises = mExerciseDao.getAllExercises();
    }

    public LiveData<List<Exercise>> getAllExercises(){
        return mExercises;
    }

    public void insert (final Exercise exercise){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mExerciseDao.insertExercises(exercise);
            }
        });
    }

    public void update (final Exercise exercise){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mExerciseDao.updateExercises(exercise);
            }
        });
    }

    public void delete (final Exercise exercise){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mExerciseDao.deleteExercises(exercise);
            }
        });
    }



}
