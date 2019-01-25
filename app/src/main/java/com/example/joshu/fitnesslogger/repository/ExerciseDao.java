package com.example.joshu.fitnesslogger.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.joshu.fitnesslogger.model.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercise")
    public LiveData<List<Exercise>> getAllExercises();

    @Insert
    public void insertExercises(Exercise exercises);


    @Delete
    public void deleteExercises(Exercise exercises);


    @Update
    public void updateExercises(Exercise exercises);


}
