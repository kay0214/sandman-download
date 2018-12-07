/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.config.thymeleaf;

import com.sandman.download.utils.NumberUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunpeikai
 * @version FileSizeFormatUtils, v0.1 2018/12/7 15:25
 */
public class FileSizeFormatUtils {

    private static Map<Integer, String> fileSizeMap = new HashMap<>();

    static {//静态代码块，将文件大小的数量级put到map中
        fileSizeMap.put(0, "B");
        fileSizeMap.put(1, "KB");
        fileSizeMap.put(2, "MB");
        fileSizeMap.put(3, "GB");
    }
    /**
     * 处理大小
     * @auth sunpeikai
     * @param
     * @return
     */
    public static String fileSizeFormat(double size){
        String fileSizeUnit = "";
        int mapKey = 0;
        while (size > 1) {//size>1，进入循环，得到下一个数量级,例如1025MB = 1GB
            mapKey++;
            size = size / 1024;
        }
        //size<1，则跳出循环。此时数量级为 0.999GB，稍后进行处理
        fileSizeUnit = fileSizeMap.get(mapKey - 1);//获取到静态代码块中put进去的值。
        size *= 1024;   //进入上一个数量级，得到一个合适的数量级，999MB 而非0.999GB
        size = NumberUtils.getDoubleByDouble(size, 2);//四舍五入，保留2位小数
        return size + fileSizeUnit;
    }
}
