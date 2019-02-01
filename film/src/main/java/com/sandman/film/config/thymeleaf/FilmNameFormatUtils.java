/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.config.thymeleaf;

/**
 * @author sunpeikai
 * @version FileSizeFormatUtils, v0.1 2018/12/7 15:25
 */
public class FilmNameFormatUtils {

    /**
     * 格式化影片名称
     * @auth sunpeikai
     * @param
     * @return
     */
    public static String filmNameFormat(String filmName){
        if(filmName.length()>10){
            return filmName.substring(0,9) + "...";
        }else{
            return filmName;
        }
    }
}
