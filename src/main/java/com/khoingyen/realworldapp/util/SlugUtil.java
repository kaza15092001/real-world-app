package com.khoingyen.realworldapp.util;

import java.util.Date;

public class SlugUtil {
    public static String getSlug(String title) {
        return title.toLowerCase().trim()
                .replaceAll("\\s+", "-") + "-" + new Date().getTime();
    }
}
