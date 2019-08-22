package top.tinx.blog.utils;

import java.util.Random;

/**
 * 创建人: Wills
 * 创建时间：2019/8/22 15:27
 * 描述:
 */
public class RandomNumber {

    public static int getRandomNumber(int numCount){
        /*
        int score = 10;
        for(int i=1;i<numCount;i++){
            score *=10;
        }*/
        Random random = new Random();
        return random.nextInt(numCount);
    }
}
