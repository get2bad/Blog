package top.tinx.blog.utils;

public class StringUtils {

    //去除字符串中的所有标点符号和空格
    public static String replaceOtherBadThing(String str){
        str = str.replaceAll("[\\pP‘’“”~!@#$%^&*()_=]", "");
        str = str.replace(" ","");
        return str;
    }
}
