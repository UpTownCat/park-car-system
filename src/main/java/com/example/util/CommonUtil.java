package com.example.util;

/**
 * Created by Administrator on 2017/5/3.
 */
public final class CommonUtil {
    private CommonUtil(){};

    public static final String getPageSql(int index, int size) {
        int begin = (index - 1) * size;
        return " limit " + begin + " , " + size;
    }
}
