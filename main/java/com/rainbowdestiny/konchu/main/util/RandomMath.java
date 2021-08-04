package com.rainbowdestiny.konchu.main.util;

import java.util.Random;

public class RandomMath {

    public static Random RANDOM;

    public RandomMath() {
        RANDOM = new Random();
    }

    public static float randomRange(float min, float max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
