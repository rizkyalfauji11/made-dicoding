
package com.code.alpha.alphamade.submission.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewMovieModel implements Parcelable {

    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("genre_ids")
    private List<Long> genreIds;
    @Expose
    private Long id;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @Expose
    private String overview;
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @Expose
    private String title;
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Long voteCount;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("first_air_date")
    private String firstAirDate;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public NewMovieModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.adult);
        dest.writeString(this.backdropPath);
        dest.writeList(this.genreIds);
        dest.writeValue(this.id);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeString(this.overview);
        dest.writeValue(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.releaseDate);
        dest.writeString(this.title);
        dest.writeValue(this.video);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.voteCount);
        dest.writeString(this.originalName);
        dest.writeString(this.firstAirDate);
    }

    protected NewMovieModel(Parcel in) {
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.backdropPath = in.readString();
        this.genreIds = new ArrayList<>();
        in.readList(this.genreIds, Long.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.overview = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.posterPath = in.readString();
        this.releaseDate = in.readString();
        this.title = in.readString();
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Long) in.readValue(Long.class.getClassLoader());
        this.originalName = in.readString();
        this.firstAirDate = in.readString();
    }

    public static final Creator<NewMovieModel> CREATOR = new Creator<NewMovieModel>() {
        @Override
        public NewMovieModel createFromParcel(Parcel source) {
            return new NewMovieModel(source);
        }

        @Override
        public NewMovieModel[] newArray(int size) {
            return new NewMovieModel[size];
        }
    };
}
