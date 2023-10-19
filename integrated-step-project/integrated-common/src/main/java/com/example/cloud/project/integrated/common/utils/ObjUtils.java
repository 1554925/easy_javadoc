package com.example.cloud.project.integrated.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gys
 * @version 1.0
 * @date 2023/8/21 9:57
 */
@SuppressWarnings({"unchecked","unused"})
public class ObjUtils {

    public static <T,R> R copy(T source, Class<R> rClass){
        JSONObject targetJSON = JSON.parseObject(JSON.toJSONString(source));
        return JSON.toJavaObject(targetJSON,rClass);
    }
    @SuppressWarnings("unchecked")
    public static <T,R> R copy(T source, R target){
        return copy(source,target,(Class<R>) target.getClass());
    }
    public static <T,R> R copy(T source, R target, Class<R> rClass){
        if(source == null){
            return null;
        }
        if(target == null){
            try{
                target = rClass.newInstance();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        String sourceStr;
        if(source instanceof String){
            sourceStr = (String) source;
        }else {
            sourceStr = JSON.toJSONString(source);
        }
        JSONObject sourceJSON = JSON.parseObject(sourceStr);
        JSONObject targetJSON = JSON.parseObject(JSON.toJSONString(target));
        for(String key : sourceJSON.keySet()){
            Object value = sourceJSON.get(key);
            if(isNotEmpty(value)){
                targetJSON.put(key,value);
            }
        }
        return JSON.toJavaObject(targetJSON,rClass);
    }
    public static <T> boolean isEmpty(T object){
        if(object == null){
            return true;
        }
        if(object instanceof CharSequence){
            return StringUtils.isBlank((CharSequence)object);
        }
        if(object instanceof Collection){
            return ((Collection<?>)object).isEmpty();
        }
        if(object instanceof Map){
            return ( (Map<?,?>)object).isEmpty();
        }
        if(object.getClass().isArray()){
            return Array.getLength(object) == 0;
        }
        return false;
    }


    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
    public static boolean isNotAllEmpty(Object... objects) {
        for(Object obj : objects){
            if(isEmpty(obj)){
                return false;
            }
        }
        return true;
    }
    public static boolean isAnyEmpty(Object... objects) {
        for(Object obj : objects){
            if(isEmpty(obj)){
                return true;
            }
        }
        return false;
    }

    public static String column2Hump(String data) {
        String[] dataArr = data.split("_");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dataArr.length; i++) {
            String one = dataArr[i];
            if(i == 0){
                builder.append(one);
            }else {
                String first = one.substring(0,1);
                builder.append(first.toUpperCase(Locale.ROOT)).append(one.substring(1));
            }
        }
        return builder.toString();
    }
    public static String hump2column(String data) {
        char[] dataArr = data.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char one : dataArr) {
            if (builder.length() !=0 && one <= 'Z') {
                builder.append("_");
            }
            builder.append(one);
        }
        return builder.toString().toLowerCase(Locale.ROOT);
    }
    public static List<String> column2Hump(List<String> dataList) {
        return dataList.stream().map(ObjUtils::column2Hump).collect(Collectors.toList());
    }

    public static InputStream stringRead2InputStream(String data) {
        return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    }
    public static String stringOrNil(Object data) {
        return org.apache.commons.lang3.ObjectUtils.isEmpty(data)?null:data.toString();
    }

    public static String toUpperCaseFirstCode(String serviceCode) {
        if(isEmpty(serviceCode)){
            return null;
        }
        String first = serviceCode.substring(0,1);
        return first.toUpperCase(Locale.ROOT) + serviceCode.substring(1);
    }
}
