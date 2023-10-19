package com.example.cloud.project.integrated.common.utils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/18 15:39
 */
public class Collections {

    public static <T> Set<T> newHashSet(T... dataArray) {
        return Arrays.stream(dataArray).collect(Collectors.toSet());
    }
}
