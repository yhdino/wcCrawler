package com.zhsz.utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Han on 15-3-5.
 */
public class UploadPathImpl implements UploadPath {
    private static String basePath;
    static {
        basePath = SysConfig.getPropertiesValue("uploadpath");
    }

    public String getUploadPath(String head,String type) {
        return head + basePath + "/" + type + dateToString(new Date());
    }

    private static String dateToString(Date time){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("yyyy/MM/dd");
        String ctime = formatter.format(time);
        return "/" + ctime + "/";
    }
}
