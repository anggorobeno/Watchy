package com.example.utils;

import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.data.source.local.entity.TvEntity;

import java.util.ArrayList;

public class FakeDataDummy {
    public static ArrayList<TvEntity> generatePopularTv() {
        ArrayList<TvEntity> tvEntities = new ArrayList<>();
        tvEntities.add(new TvEntity(
                91363,
                "2021-08-11",
                "Taking inspiration from the comic books of the same name, each episode explores a pivotal moment from the Marvel Cinematic Universe and turns it on its head, leading the audience into uncharted territory.",
                "What If...?",
                "/lztz5XBMG1x6Y5ubz7CxfPFsAcW.jpg",
                "/4N6zEMfZ57zNEQcM8gWeERFupMv.jpg",
                7,
                null
        ));
        return tvEntities;

    }

    public static ArrayList<MovieEntity> generatePopularMovie() {
        ArrayList<MovieEntity> movieEntities = new ArrayList<>();
        movieEntities.add(new MovieEntity(
                550988,
                "A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.",
                "Free Guy",
                "/xmbU4JTUm8rsdtn7Y3Fcm30GpeT.jpg",
                "/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                "2021-08-11",
                7,
                false
        ));
        return movieEntities;
    }
}