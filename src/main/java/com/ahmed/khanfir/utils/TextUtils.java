package com.ahmed.khanfir.utils;

/**
 * Created by ahmed on 2/1/19.
 */
public final class TextUtils {

    private TextUtils() {
        throw new IllegalAccessError("Utility class. Static access only.");
    }

    public static boolean isEmpty(String string){
        return string == null || string.length() == 0;
    }
}
