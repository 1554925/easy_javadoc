package com.example.cloud.project.integrated.common.domain;

import java.util.Arrays;

/**
 * @author gys
 * @date 2023/10/21
 * @description
 */
public enum LanguageType {
    CH,
    EN,
    NONE
    ;

    public boolean isNotNone() {
        return this != NONE;
    }
    public static boolean isAnyNone(LanguageType... types){
        for (LanguageType type:types){
            if(!type.isNotNone()){
                return true;
            }
        }
        return false;
    }
    public static LanguageType language(String name){
        return Arrays.stream(values()).filter(type -> type.name().equals(name)).findFirst().orElse(NONE);
    }
}
