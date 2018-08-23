package com.shockbyte.shockbotty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String getMessage(String[] args) {
        return getMessage(args, 0, args.length);
    }

    public static String getMessage(String[] args, int min) {
        return getMessage(args, min, args.length);
    }

    public static String getMessage(String[] args, int min, int max) {
        return Arrays.stream(args).skip(min).limit(max).collect(Collectors.joining(" "));
    }

    public static boolean listContains(List<Object> haystack, Object needle) {
        for (Object o : haystack)
            if (o.equals(needle) || o == needle)
                return true;

        return false;
    }
}
