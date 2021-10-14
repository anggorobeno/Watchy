package com.example.myviewmodel.utils;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class Espresso {
    private static final String RESOURCE = "GLOBAL";
    private static final CountingIdlingResource idlingResource = new CountingIdlingResource(RESOURCE);

    public static void increment() {
        idlingResource.increment();
    }

    public static void decrement() {
        idlingResource.decrement();
    }

    public static IdlingResource getEspressoIdlingResource() {
        return idlingResource;
    }
}

