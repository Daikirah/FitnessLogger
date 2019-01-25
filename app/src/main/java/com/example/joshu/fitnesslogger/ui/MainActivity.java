package com.example.joshu.fitnesslogger.ui;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.joshu.fitnesslogger.adapters.ExerciseAdapter;
import com.example.joshu.fitnesslogger.model.Exercise;
import com.example.joshu.fitnesslogger.model.ExerciseViewModel;
import com.example.joshu.fitnesslogger.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private List<Exercise> mExerciseList;
    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    private ExerciseViewModel mExerciseViewModel;
    public static final String EDIT_EXERCISE = "edit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mExerciseViewModel = new ExerciseViewModel(getApplicationContext());
        mExerciseViewModel.getExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable List<Exercise> exercises) {
                mExerciseList = exercises;
                updateUI();
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mExerciseList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddExerciseActivity.class);
                startActivity(intent);
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback =
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                    int pos = (viewHolder.getAdapterPosition());
                    mExerciseViewModel.delete(mExerciseList.get(pos));
                }
            };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    private void updateUI() {
        if (mAdapter == null){
            mAdapter = new ExerciseAdapter(mExerciseList, new ExerciseAdapter.OnItemClickListener(){

                @Override
                public void onItemClick(Exercise exercise) {
                    Intent intent = new Intent(MainActivity.this, EditExerciseActivity.class);
                    intent.putExtra(EDIT_EXERCISE, exercise);
                    startActivity(intent);
                }
            });
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(mExerciseList);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
