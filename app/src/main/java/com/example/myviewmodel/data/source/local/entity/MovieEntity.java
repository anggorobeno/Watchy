package com.example.myviewmodel.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movieTable")
public class MovieEntity implements Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "idMovie")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;


    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;


    @ColumnInfo(name = "poster")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo(name = "backdrop")
    @SerializedName("backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "date")
    @SerializedName("release_date")
    private String releaseDate;


    @ColumnInfo(name = "rating")
    @SerializedName("vote_average")
    private float voteAverage;

    @ColumnInfo(name = "fave")
    private boolean fave = false;

    public MovieEntity(int id, String overview, String title, String posterPath, String backdropPath, String releaseDate, float voteAverage, Boolean fave) {
        this.id = id;
        this.overview = overview;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        if (fave != null) {
            this.fave = fave;
        }
    }

    protected MovieEntity(Parcel in) {
        id = in.readInt();
        overview = in.readString();
        title = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readFloat();
        fave = in.readByte() != 0;
    }

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel in) {
            return new MovieEntity(in);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean isFave() {
        return fave;
    }

    public void setFave(boolean fave) {
        this.fave = fave;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(overview);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(releaseDate);
        parcel.writeFloat(voteAverage);
        parcel.writeByte((byte) (fave ? 1 : 0));
    }
}