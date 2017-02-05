package com.nowcoder.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.List;


/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/2 下午10:47
 */

@Service
public class JedisAdapter implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

    private JedisPool pool = null;

    private Jedis jedis = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("localhost", 6379);
        jedis = getJedis();
    }

    private Jedis getJedis() {
        return pool.getResource();
    }


    public void set(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("exception happen" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public String get(String key) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("exception happen" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("exception happen" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public long srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srem(key, value);
        } catch (Exception e) {
            logger.error("exception happen" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error("exception happen" + e.getMessage());
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("exception happen" + e.getMessage());
            //
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void lpush(String key, String json) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.lpush(key, json);
        } catch (Exception e) {
            logger.error("exception happen" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> brpop(int timeout, String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.brpop(timeout,key);
        } catch (Exception e) {
            logger.error("exception happen" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public void setObject(String key, Object obj) {
        set(key, JSON.toJSONString(obj));
    }

    public <T> T getObject(String key, Class<T> clazz) {
        String value = get(key);
        if (value != null) {
            return (T) JSON.parseObject(value, clazz);
        }
        return null;
    }


//    public static void main(String[] args) {
//        Jedis jedis = new Jedis();
//        jedis.flushAll();
//
//        //Map<String, String> map
//        jedis.set("hello", "world");
//        print(1, jedis.get("hello"));
//
//        jedis.rename("hello", "newHello");
//        print(1, jedis.get("newHello"));
//
//        jedis.setex("hello2", 15, "word");
//
//
//        //
//        jedis.set("pv", "100");
//        jedis.incr("pv");
//        print(2, jedis.get("pv"));
//        jedis.incrBy("pv", 5);
//        print(2, jedis.get("pv"));
//
//        //列表操作  很方便的 操作底层的数据
//        String listName = "listA";
//        for (int i = 0; i < 10; i++) {
//            jedis.lpush(listName, "a" + i);
//        }
//        print(3, jedis.lrange(listName, 0, 12));
//
//        print(4, jedis.llen(listName));
//        print(5, jedis.lpop(listName));
//        print(6, jedis.llen(listName));
//        print(7, jedis.lindex(listName, 3));
//        print(8, jedis.linsert(listName, BinaryClient.LIST_POSITION.BEFORE, "a4", "xx"));
//        print(9, jedis.linsert(listName, BinaryClient.LIST_POSITION.AFTER, "a4", "bb"));
//
//
//        //使用场景   不确定的需求
//        //hash 表
//        String userKey = "userxx";
//        jedis.hset(userKey, "name", "jim");
//        jedis.hset(userKey, "age", "2");
//        jedis.hset(userKey, "phone", "18181822123");
//        jedis.hset(userKey, "school", "tai");
//        print(11, jedis.hget(userKey, "name"));
//        print(12, jedis.hgetAll(userKey));
//
//        jedis.hdel(userKey, "phone");
//        print(13, jedis.hgetAll(userKey));
//
//        print(14, jedis.hkeys(userKey));
//        print(14, jedis.hvals(userKey));
//        print(14, jedis.hexists(userKey, "email"));
//        print(14, jedis.hexists(userKey, "age"));
//
//        //
//        jedis.hsetnx(userKey, "name", "uxu");
//        print(19, jedis.hgetAll(userKey));
//
//
//        //集合的概念
//        //求 并 交 差 (好友?)
//        String likeKeys1 = "newsLike1";
//        String likeKeys2 = "newsLike2";
//        for (int i = 0; i < 10; i++) {
//            jedis.sadd(likeKeys1, String.valueOf(i));
//            jedis.sadd(likeKeys2, String.valueOf(i * 2));
//        }
//        print(20, jedis.smembers(likeKeys1));
//        print(21, jedis.smembers(likeKeys2));
//        print(22, jedis.sinter(likeKeys2, likeKeys1));
//        print(22, jedis.sunion(likeKeys2, likeKeys1));
//        print(22, jedis.sdiff(likeKeys2, likeKeys1));
//        print(22, jedis.sdiff(likeKeys1, likeKeys2));
//        print(22, jedis.sismember(likeKeys1, "5"));
//        jedis.srem(likeKeys1, "5");
//        print(22, jedis.sismember(likeKeys1, "5"));
//        print(22, jedis.scard(likeKeys1));
//        jedis.smove(likeKeys2, likeKeys1, "14");
//
//        //优先队列  核心用处 排名
//        String rankKey = "rankKey";
//        jedis.zadd(rankKey, 15, "jim");
//        jedis.zadd(rankKey, 60, "Ben");
//        jedis.zadd(rankKey, 75, "Lee");
//        jedis.zadd(rankKey, 90, "Lucy");
//        jedis.zadd(rankKey, 80, "Lily");
//        print(30, jedis.zcard(rankKey));
//        print(30, jedis.zcount(rankKey, 61, 100));
//        print(30, jedis.zscore(rankKey, "Lucy"));
//
//        jedis.zincrby(rankKey, 2, "Lucy");
//        print(34, jedis.zrange(rankKey, 1, 3));
//        print(34, jedis.zrevrange(rankKey, 1, 3));
//
//
//        //遍历
//        for (Tuple tuple : jedis.zrangeByScoreWithScores(rankKey, "0", "100")) {
//            print(37, tuple.getElement() + ":" + String.valueOf(tuple.getScore()));
//        }
//
//        //查看自己是第几名
//        print(40, jedis.zrank(rankKey, "Ben"));
//        print(40, jedis.zrevrank(rankKey, "Ben"));
//
//
//        //pool 的概念
//        //BlBlock   阻塞线程
//
//        JedisPool pool = new JedisPool();
//        for (int i = 0; i < 100; i++) {
//            Jedis j = pool.getResource();
//            j.get("a");
//            System.out.println("POOL" + i);
//            j.close();
//        }
//
//
//    }

    private static void print(int index, Object obj) {
        System.out.println(String.format("%d, %s", index, obj.toString()));
    }


}
