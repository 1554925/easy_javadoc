package com.example.cloud.project.integrated.translate.utils;

import java.util.Locale;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/24 11:18
 */
public class ObjectTolls {
    public static String firstWordLower(String word){
        String first = word.substring(0,1);
        return first.toLowerCase(Locale.ROOT) + word.substring(1);
    }

}
