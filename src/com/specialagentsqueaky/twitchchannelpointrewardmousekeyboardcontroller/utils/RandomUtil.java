package com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.utils;

import java.util.List;
import java.util.Random;

public class RandomUtil {

    public static <T> T random(List<T> items) {
        Random rand = new Random();
        return items.get(rand.nextInt(items.size()));
    }

}
