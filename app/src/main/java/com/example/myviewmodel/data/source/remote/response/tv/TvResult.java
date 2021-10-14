package com.example.myviewmodel.data.source.remote.response.tv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResult implements Parcelable {

    @SerializedName("first_air_date")
    private final String firstAirDate;

    @SerializedName("overview")
    private final String overview;

    @SerializedName("original_language")
    private final String originalLanguage;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("origin_country")
    private final List<String> originCountry;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("popularity")
    private final double popularity;

    @SerializedName("vote_average")
    private final float voteAverage;

    @SerializedName("original_name")
    private final String originalName;

    @SerializedName("name")
    private final String name;

    @SerializedName("id")
    private final int id;

    @SerializedName("vote_count")
    private final int voteCount;

    public TvResult(String firstAirDate, String overview, String originalLanguage, List<Integer> genreIds, String posterPath, List<String> originCountry, String backdropPath, double popularity, float voteAverage, String originalName, String name, int id, int voteCount) {
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.genreIds = genreIds;
        this.posterPath = posterPath;
        this.originCountry = originCountry;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.originalName = originalName;
        this.name = name;
        this.id = id;
        this.voteCount = voteCount;
    }

    protected TvResult(Parcel in) {
        firstAirDate = in.readString();
        overview = in.readString();
        originalLanguage = in.readString();
        posterPath = in.readString();
        originCountry = in.createStringArrayList();
        backdropPath = in.readString();
        popularity = in.readDouble();
        voteAverage = in.readFloat();
        originalName = in.readString();
        name = in.readString();
        id = in.readInt();
        voteCount = in.readInt();
    }

    public static final Creator<TvResult> CREATOR = new Creator<TvResult>() {
        @Override
        public TvResult createFromParcel(Parcel in) {
            return new TvResult(in);
        }

        @Override
        public TvResult[] newArray(int size) {
            return new TvResult[size];
        }
    };

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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
        parcel.writeString(firstAirDate);
        parcel.writeString(overview);
        parcel.writeString(originalLanguage);
        parcel.writeString(posterPath);
        parcel.writeStringList(originCountry);
        parcel.writeString(backdropPath);
        parcel.writeDouble(popularity);
        parcel.writeFloat(voteAverage);
        parcel.writeString(originalName);
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeInt(voteCount);
    }
}