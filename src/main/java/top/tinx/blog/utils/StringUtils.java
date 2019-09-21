package top.tinx.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    //去除字符串中的所有标点符号和空格
    public static String replaceOtherBadThing(String str){
        str = str.replaceAll("[\\pP‘’“”~!@#$%^&*()_=]", "");
        str = str.replace(" ","");
        return str;
    }

    //获取当前的系统时间
    public static String getNowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
