package com.code.alpha.alphamade.submission.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    @SerializedName("title")
    private String name;

    @SerializedName("desc")
    private String description;

    @SerializedName("lengthOfTime")
    private String lenghtOfTime;

    @SerializedName("year")
    private String year;

    @SerializedName("rating")
    private String rating;
    @SerializedName("photo")
    private String image;

    public String getLenghtOfTime() {
        return lenghtOfTime;
    }

    public void setLenghtOfTime(String lenghtOfTime) {
        this.lenghtOfTime = lenghtOfTime;
    }

    private List<Crew> crews;

    public List<Crew> getCrews() {
        return crews;
    }

    public void setCrews(List<Crew> crews) {
        this.crews = crews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    class Crew {
        private String name, job;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.lenghtOfTime);
        dest.writeString(this.year);
        dest.writeString(this.rating);
        dest.writeString(this.image);
        dest.writeList(this.crews);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.lenghtOfTime = in.readString();
        this.year = in.readString();
        this.rating = in.readString();
        this.image = in.readString();
        this.crews = new ArrayList<>();
        in.readList(this.crews, Crew.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
