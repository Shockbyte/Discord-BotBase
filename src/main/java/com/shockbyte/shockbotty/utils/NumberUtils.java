package com.shockbyte.shockbotty.utils;

public class NumberUtils {

    public static int getInt(String arg, int defaultVal) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public static double getDouble(String arg, double defaultVal) {
        try {
            return Double.parseDouble(arg);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public static long getLong(String arg, long defaultVal) {
        try {
            return Long.parseLong(arg);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}
