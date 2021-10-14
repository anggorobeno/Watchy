package com.example.myviewmodel.data.source.remote.response.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResult implements Parcelable {

    @SerializedName("overview")
    private final String overview;

    @SerializedName("original_language")
    private final String originalLanguage;

    @SerializedName("original_title")
    private final String originalTitle;

    @SerializedName("video")
    private final boolean video;

    @SerializedName("title")
    private final String title;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("release_date")
    private final String releaseDate;

    @SerializedName("popularity")
    private final double popularity;

    @SerializedName("vote_average")
    private final float voteAverage;

    @SerializedName("id")
    private final int id;

    @SerializedName("adult")
    private final boolean adult;

    @SerializedName("vote_count")
    private final int voteCount;

    public MovieResult(String overview, String originalLanguage, String originalTitle, boolean video, String title, List<Integer> genreIds, String posterPath, String backdropPath, String releaseDate, double popularity, float voteAverage, int id, boolean adult, int voteCount) {
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.video = video;
        this.title = title;
        this.genreIds = genreIds;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.id = id;
        this.adult = adult;
        this.voteCount = voteCount;
    }

    protected MovieResult(Parcel in) {
        overview = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        video = in.readByte() != 0;
        title = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        releaseDate = in.readString();
        popularity = in.readDouble();
        voteAverage = in.readFloat();
        id = in.readInt();
        adult = in.readByte() != 0;
        voteCount = in.readInt();
    }

    public static final Creator<MovieResult> CREATOR = new Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel in) {
            return new MovieResult(in);
        }

        @Override
        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };

    public String getOverview() {
        return overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public boolean isVideo() {
        return video;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(overview);
        parcel.writeString(originalLanguage);
        parcel.writeString(originalTitle);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(releaseDate);
        parcel.writeDouble(popularity);
        parcel.writeFloat(voteAverage);
        parcel.writeInt(id);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeInt(voteCount);
    }
}