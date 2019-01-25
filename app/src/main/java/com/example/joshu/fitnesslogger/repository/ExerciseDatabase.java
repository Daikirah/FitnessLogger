package com.example.joshu.fitnesslogger.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.joshu.fitnesslogger.model.Exercise;


@Database(entities = {Exercise.class},version = 1)
public abstract class ExerciseDatabase extends RoomDatabase {

    public abstract ExerciseDao exerciseDao();
    private final static String NAME_DATABASE = "exercise_db";
    private static ExerciseDatabase dbInstance;

    public static ExerciseDatabase getDbInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(context, ExerciseDatabase.class, NAME_DATABASE).build();
        }
        return dbInstance;
    }

}
