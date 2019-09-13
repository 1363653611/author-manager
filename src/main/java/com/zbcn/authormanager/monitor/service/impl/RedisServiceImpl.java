package com.zbcn.authormanager.monitor.service.impl;

import com.zbcn.authormanager.common.JedisExecutor;
import com.zbcn.authormanager.common.exception.RedisConnectException;
import com.zbcn.authormanager.monitor.entity.RedisInfo;
import com.zbcn.authormanager.monitor.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName RedisServiceImpl.java
 * @Description redis 的实现类
 * @createTime 2019年08月18日 14:48:00
 */
@Service
public class RedisServiceImpl implements IRedisService{

    @Autowired
    private JedisPool jedisPool;

    private static String separator = System.getProperty("line.separator");

    /**
     *  处理 jedis请求
     * @param j j 处理逻辑，通过 lambda行为参数化
     * @param <T> 处理结果
     * @return
     * @throws RedisConnectException
     */
    private <T> T excuteByJedis(JedisExecutor<Jedis,T> j) throws RedisConnectException {

        try(Jedis jedis = jedisPool.getResource()){
            try {
                return j.execute(jedis);
            } catch (RedisConnectException e) {
                throw new RedisConnectException(e.getMessage());
            }
        }
    }

    /**
     * 获取 redis 的详细信息
     *
     * @return List
     */
    @Override
    public List<RedisInfo> getRedisInfo() throws RedisConnectException {

        String info = this.excuteByJedis(j -> {
            Client client = j.getClient();
            client.info();
            return client.getBulkReply();
        });
        List<RedisInfo> infoList = new ArrayList<>();
        String[] strs = Objects.requireNonNull(info).split(separator);

        RedisInfo redisInfo;
        if (strs.length > 0) {
            for (String str1 : strs) {
                redisInfo = new RedisInfo();
                String[] str = str1.split(":");
                if (str.length > 1) {
                    String key = str[0];
                    String value = str[1];
                    redisInfo.setKey(key);
                    redisInfo.setValue(value);
                    infoList.add(redisInfo);
                }
            }
        }
        return infoList;
    }

    /**
     * 获取 redis key 数量
     *
     * @return Map
     */
    @Override
    public Map<String, Object> getKeysSize() throws RedisConnectException {
        Long dbSize = this.excuteByJedis(j -> {
            Client client = j.getClient();
            client.dbSize();
            return client.getIntegerReply();
        });
        Map<String, Object> map = new HashMap<>();
        map.put("dbSize", dbSize);
        return map;
    }

    /**
     * 获取 redis 内存信息
     *
     * @return Map
     */
    @Override
    public Map<String, Object> getMemoryInfo() throws RedisConnectException {
        String info = this.excuteByJedis(
                j -> {
                    Client client = j.getClient();
                    client.info();
                    return client.getBulkReply();
                }
        );
        String[] strs = Objects.requireNonNull(info).split(separator);
        Map<String, Object> map = null;
        for (String s : strs) {
            String[] detail = s.split(":");
            if ("used_memory".equals(detail[0])) {
                map = new HashMap<>();
                map.put("used_memory", detail[1].substring(0, detail[1].length() - 1));
                break;
            }
        }
        return map;
    }

    /**
     * 获取 key
     *
     * @param pattern 正则
     * @return Set
     */
    @Override
    public Set<String> getKeys(String pattern) throws RedisConnectException {
        return this.excuteByJedis(j -> j.keys(pattern));
    }

    /**
     * get命令
     *
     * @param key key
     * @return String
     */
    @Override
    public String get(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.get(key.toLowerCase()));
    }

    /**
     * set命令
     *
     * @param key   key
     * @param value value
     * @return String
     */
    @Override
    public String set(String key, String value) throws RedisConnectException {
        return this.excuteByJedis(j -> j.set(key.toLowerCase(), value));
    }

    /**
     * set 命令
     *
     * @param key         key
     * @param value       value
     * @param milliscends 毫秒
     * @return String
     */
    @Override
    public String set(String key, String value, Long milliscends) throws RedisConnectException {
        String result = this.set(key.toLowerCase(), value);
        this.pexpire(key, milliscends);
        return result;
    }

    /**
     * del命令
     *
     * @param key key
     * @return Long
     */
    @Override
    public Long del(String... key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.del(key));
    }

    /**
     * exists命令
     *
     * @param key key
     * @return Boolean
     */
    @Override
    public Boolean exists(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.exists(key));
    }

    /**
     * pttl命令
     *
     * @param key key
     * @return Long
     */
    @Override
    public Long pttl(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.pttl(key));
    }

    /**
     * pexpire命令
     *
     * @param key         key
     * @param milliscends 毫秒
     * @return Long
     */
    @Override
    public Long pexpire(String key, Long milliscends) throws RedisConnectException {
        return this.excuteByJedis(j -> j.pexpire(key, milliscends));
    }

    /**
     * zadd 命令
     *
     * @param key    key
     * @param score  score
     * @param member value
     */
    @Override
    public Long zadd(String key, Double score, String member) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zadd(key, score, member));
    }

    /**
     * zrangeByScore 命令
     *
     * @param key key
     * @param min min
     * @param max max
     * @return Set<String>
     */
    @Override
    public Set<String> zrangeByScore(String key, String min, String max) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zrangeByScore(key, min, max));
    }

    /**
     * zremrangeByScore 命令
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return Long
     */
    @Override
    public Long zremrangeByScore(String key, String start, String end) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zremrangeByScore(key, start, end));
    }

    /**
     * zrem 命令
     *
     * @param key     key
     * @param members members
     * @return Long
     */
    @Override
    public Long zrem(String key, String... members) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zrem(key, members));
    }
}
