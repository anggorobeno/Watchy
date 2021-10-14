package com.example.myviewmodel.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tvTable")
public class TvEntity implements Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "idTv")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "date")
    @SerializedName("first_air_date")
    private String firstAirDate;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo(name = "title")
    private String name;

    @ColumnInfo(name = "poster")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo(name = "backdrop")
    @SerializedName("backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "rating")
    @SerializedName("vote_average")
    private float voteAverage;

    @ColumnInfo(name = "fave")
    private boolean fave = false;

    public TvEntity(int id, String firstAirDate, String overview, String name, String posterPath, String backdropPath, float voteAverage, Boolean fave) {
        this.id = id;
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
        if (fave != null) {
            this.fave = fave;
        }
    }

    protected TvEntity(Parcel in) {
        id = in.readInt();
        firstAirDate = in.readString();
        overview = in.readString();
        name = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        voteAverage = in.readFloat();
        fave = in.readByte() != 0;
    }

    public static final Creator<TvEntity> CREATOR = new Creator<TvEntity>() {
        @Override
        public TvEntity createFromParcel(Parcel in) {
            return new TvEntity(in);
        }

        @Override
        public TvEntity[] newArray(int size) {
            return new TvEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        parcel.writeString(firstAirDate);
        parcel.writeString(overview);
        parcel.writeString(name);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeFloat(voteAverage);
        parcel.writeByte((byte) (fave ? 1 : 0));
    }
}