package com.example.joshu.fitnesslogger.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity (tableName = "exercise")
public class Exercise implements Parcelable {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @PrimaryKey(autoGenerate = true)
    private int gid;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "reps")
    private String reps;

    @ColumnInfo(name = "weight")
    private String weight;

    @ColumnInfo(name = "date")
    private String date;


    protected Exercise(Parcel in) {
        gid = in.readInt();
        title = in.readString();
        reps = in.readString();
        weight = in.readString();
        date = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Exercise(String title, String reps, String weight){
        this.title = title;
        this.reps = reps;
        this.weight = weight;
        this.date = dateFormat.format(new Date());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(gid);
        dest.writeString(title);
        dest.writeString(reps);
        dest.writeString(weight);
        dest.writeString(date);
    }
}
