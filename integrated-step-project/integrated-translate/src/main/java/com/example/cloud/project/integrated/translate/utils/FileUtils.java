package com.example.cloud.project.integrated.translate.utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gys
 * @version 1.0
 * @date 2023/8/3 9:34
 */
public class FileUtils {
    public static void stringSave2file(String data,String filePath){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static String getFileStringValue(String filePath){
        return String.join("\n",getFileListValue(filePath));

    }
    public static List<String> getFileListValue(String filePath){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> result = new ArrayList<>();
            String data ;
            while ( (data = reader.readLine()) !=null ){
                result.add(data);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
