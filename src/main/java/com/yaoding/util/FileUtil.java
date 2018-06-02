package com.yaoding.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2018-06-02
 * Time: 22:25
 */
public class FileUtil {
    public static void mkdirs(String dir){
        if(StringUtils.isEmpty(dir)){
            return;
        }

        File file = new File(dir);
        if(file.isDirectory()){
            return;
        } else {
            file.mkdirs();
        }
    }
}
