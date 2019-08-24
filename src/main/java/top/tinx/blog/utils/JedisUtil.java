package top.tinx.blog.utils;

import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 创建人: Wills
 * 创建时间：2019/8/23 22:31
 * 描述: Redis的工具类，提供基本配置
 */
public class JedisUtil {

    public static Jedis getJedis(){
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),"127.0.0.1");
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    /**
     * 本方法暂时用于获取shiro登陆得到的sessionKey,查询用户是否session处在有效期（shiro暂时设置session存活周期为30分钟）
     * @param key
     * @return
     */
    public static boolean judgeUserIsActive(String key){
        //shiro:session:38afd3e7-dc28-422a-81a0-a700b69a4817
        String value = getJedis().get("shiro:session:"+key);
        if(StringUtils.isEmpty(value)){
            //如果为空，则返回false
            return  false;
        }else{
            //如果不为空，则返回true
            return true;
        }
    }

    /**
     * 此方法用于获取redis中存储的key对应的value值，
     * @param key
     * @return
     */
    public static String getValue(String key){
        String value = getJedis().get(key);
        return value;
    }

    /**
     * 本人的想法是，用户登陆成功后，将对应用户的基本信息写入到redis中，
     * 起到缓存的作用，设置其与sessionId相同的生命周期
     * 写入成功会返回一个OK
     * @param key   : 使用户的sessionId
     * @param value : 是用户基本信息的json数据
     */
    public static String setKey(String key,String value){
        String result = getJedis().set(key, value);
        //设置30分钟自动过期，后端在get时获取不到数据
        getJedis().expire(key,30*60);
        return result;
    }
}
