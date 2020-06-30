package com.backend.Utilities;

public class StringUtilites {
    public static boolean isNotNullOrEmpty(String value){
        return value != null && value.length() > 0 && !value.equals(" ");
    }
}
