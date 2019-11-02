package net.qianqiuxi.register.repo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {

    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
    public static final long TOKEN_EXPIRES_SECOND = 60 * 60 * 24 * 30;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * Set key-value pair into Redis.
     * @param key id of user
     * @param value tmp token for the user
     * @param expireTime expire time for token
     * @return save status
     */
    public boolean saveKeyValue(String key, String value, long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, expireTime,  TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("Redis save key value fail ", e);
        }
        return result;
    }

    /**
     * Get token by user id.
     * @param key id of user
     * @return tmp token of user
     */
    public String getValueByKey(String key) {
        String value = null;
        try {
            value = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.error("Redis get key value fail ", e);
        }
        return value;
    }

    /**
     * Update expire time for input user id
     * @param key user id
     * @param expireTime needed expire time
     * @return whether refresh succeed
     */
    public boolean updateExpireTimeForKey(String key, long expireTime){
        boolean result = false;
        try {
            if(expireTime > 0) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
                result = true;
            }
        } catch (Exception e) {
            logger.error("Redis update key expire time fail ", e);
        }
        return result;
    }
}
