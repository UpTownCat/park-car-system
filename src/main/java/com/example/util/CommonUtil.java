package com.example.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/3.
 */
public final class CommonUtil {
    public static final long DAY = 1000 * 60 * 60 * 24;
    private static Properties prop = null;
    /**
     * 工具类不需实例化
     */
    private CommonUtil(){};

    /**
     * 获取分页的Limit sql
     * @param index
     * @param size
     * @return
     */
    public static final String getPageSql(int index, int size) {
        int begin = (index - 1) * size;
        return " limit " + begin + " , " + size;
    }

    /**
     * 格式化double类型的数据
     * @param value
     * @return
     */
    public static final Double formatDouble(Double value) {
        DecimalFormat df = new DecimalFormat("0.0000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.valueOf(df.format(value));
    }

    /**
     * 保存图片文件
     * @param file
     * @param path
     * @return 保存在数据库的文件名称
     * @throws IOException
     */
    public static  final String savePhoto(MultipartFile file, String path) throws IOException {
        String fileName = file.getOriginalFilename();
        String realName = System.currentTimeMillis() + "" + new Random().nextInt(1000) + "_s_p" + fileName.substring(fileName.lastIndexOf("."));
        //如果路径后面没带/要自己拼接一个/
        if(path.charAt(path.length() - 1) != '/') {
            path = path + "/";
        }
        file.transferTo(new File(path + realName));
        return realName;
    }

    public static final void outputPhoto(String realName, OutputStream out) {
        String path = CommonUtil.getConfigValue("photoLocation");
        if(path.charAt(path.length() - 1) != '/') {
            path = path + "/";
        }
        File file = new File(path + realName);
        FileInputStream inputStream = null;
        try {
            inputStream = FileUtils.openInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(inputStream != null) {
            byte[] bytes = new byte[1024 * 1024 * 10];
            int tag = 0;
            try {
                while((tag = inputStream.read(bytes, 0, bytes.length)) != -1) {
                    out.write(bytes, 0, tag);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取保存在配置文件中的值
     * @param key
     * @return
     */
    public static final String getConfigValue(String key) {
        synchronized (Object.class) {
            if(prop == null) {
                prop = new Properties();
                try {
                    prop.load(CommonUtil.class.getResourceAsStream("/config.properties"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return prop.getProperty(key);
        }
    }

    /**
     * 获取指定size的总页数
     * @param total
     * @param size
     * @return
     */
    public static final long getTotal(long total, int size) {
        if ( total < size) {
            return 1;
        }
        return total % size == 0 ? total / size : total / size + 1;
    }

    /**
     * 获取指定size的总行数
     * @param total
     * @param size
     * @return
     */
    public static final int getRow(int total, int size) {
//        if(total < size){
//            return 0;
//        }
        return total / size;
    }

    /**
     * 计算停车的费用
     * @param inTime
     * @param outTime
     * @param moneyPerHour
     * @return
     */
    public static final double countMoneyOfParking(Date inTime, Date outTime, double moneyPerHour) {
        long millions = outTime.getTime() - inTime.getTime();
        long hour = 1000 * 60 * 60;
        if(millions < hour) {
            return moneyPerHour;
        }
        return CommonUtil.formatDouble((millions % hour == 0 ? millions / hour : millions / hour + 1) * moneyPerHour);
    }

    /**
     * 字符串转换为日期
     * @param value
     * @return
     * @throws ParseException
     */
    public static final Date stringToDate(String value) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(value);
    }
}
