package com.example.myviewmodel.utils;

import com.example.myviewmodel.data.source.remote.response.movie.MovieResult;
import com.example.myviewmodel.data.source.remote.response.tv.TvResult;

import java.util.ArrayList;

public class FakeDataDummy {
    public static ArrayList<TvResult> generatePopularTv() {
        ArrayList<TvResult> tvResults = new ArrayList<>();
        tvResults.add(new TvResult(
                "2021-08-11",
                "Taking inspiration from the comic books of the same name, each episode explores a pivotal moment from the Marvel Cinematic Universe and turns it on its head, leading the audience into uncharted territory.",
                "en",
                null,
                "/lztz5XBMG1x6Y5ubz7CxfPFsAcW.jpg",
                null,
                "/4N6zEMfZ57zNEQcM8gWeERFupMv.jpg",
                4098.825,
                8.2f,
                "What If...?",
                "What If...?",
                91363,
                1966

        ));
        return tvResults;

    }

    public static ArrayList<MovieResult> generatePopularMovie() {
        ArrayList<MovieResult> movieResults = new ArrayList<>();
        movieResults.add(new MovieResult(
                "A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.",
                "en",
                "Free Guy",
                false,
                "Free Guy",
                null,
                "/xmbU4JTUm8rsdtn7Y3Fcm30GpeT.jpg",
                "/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                "2021-08-11",
                10620.879,
                8f,
                550988,
                false,
                1586
        ));
        return movieResults;
    }
}