package com.hbx.study.redission;

import com.hbx.study.redission.learn.base.hash.HashType;
import com.hbx.study.redission.learn.base.set.SetType;
import com.hbx.study.redission.learn.bloom.BloomFilter;
import com.hbx.study.redission.learn.base.string.BucketTypeOperate;
import com.hbx.study.redission.learn.base.string.KeyOperate;
import com.hbx.study.redission.learn.lock.RateLimeter;
import com.hbx.study.redission.learn.lock.CountDownLatchService;
import com.hbx.study.redission.learn.lua.LuaService;
import org.junit.jupiter.api.Test;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = MyRedission.class)
//@ActiveProfiles("test")
public class BaseTest {
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testInsertKV() {
        RBucket<Object> bucket = redissonClient.getBucket("buck2");
        Object o = bucket.get();
        System.out.println(o);

    }

    @Autowired
    private KeyOperate keyOperate;

    @Test
    public void testKeys() {
        List<String> keys = keyOperate.keys();
        System.out.println(keys);
    }

    @Test
    public void testDeleteKey() {
        keyOperate.deleteKey("test2");
    }


    @Autowired
    private BucketTypeOperate bucketTypeOperate;

    @Test
    public void kvSet() {
        bucketTypeOperate.KVSet("buck2", Arrays.asList("3", new Object(), 3.0), null, false);
    }

    @Autowired
    private BloomFilter bloomFilter;

    @Test
    public void testBloom() {
        RBloomFilter<String> test = bloomFilter.getBloomFilter("hbxadd", 35500000L, 0.08f);
        test.add("aa1");
        if (test.contains("aa")) {
            System.out.println("aa命中");
        }
    }

    @Autowired
    private RateLimeter rateLimeter;

    @Test
    public void testLimit() {
        rateLimeter.tryAcquire("rate", 1, 5);
        if (!rateLimeter.tryAcquire("rate", 1, 5)) {
            System.out.println("限流了");
        }
    }

    @Autowired
    private HashType hashType;

    @Test
    public void testHash() {
        hashType.set("hash1", "h1", "v2", false);
        Object fieldValue = hashType.getFieldValue("hash1", "h1");
        System.out.println(fieldValue);
        if (hashType.removeField("hash1", "h1")) {
            System.out.println("删除成功");
        }
        fieldValue = hashType.getFieldValue("hash1", "h1");
        Assert.isNull(fieldValue, "not null");
    }

    @Autowired
    private SetType setType;

    @Test
    public void testSet() {
        setType.set("s1", "sv1");

        List<Integer> list = Arrays.asList(2, 1);
        setType.sortSet("ss1", 2, (i, j) -> {
            return i - j;
        });
        setType.sortSet("ss1", 1, (i, j) -> {
            return i - j;
        });
    }


    @Autowired
    private LuaService luaService;

    @Test
    public void testLua() {
        String script = """
                return redis.call('SET',KEYS[1],ARGV[1])
                """;
        Object o = luaService.luaWrite(script, RScript.ReturnType.STATUS, Arrays.asList("lua"), 2);
        System.out.println(o);

        script = """
                --[[
                if redis.call('GET',KEYS[1])==2
                then
                return redis.error_reply('OPERATION_ALREADY_EXECUTED')
                end
                ]]
                return redis.error_reply('KEY_NOT_FOUND')
                """;
        Object o1 = luaService.luaRead(script, RScript.ReturnType.MULTI, Arrays.asList("lua"));
        System.out.println(o1.getClass());
    }


    @Autowired
    private CountDownLatchService countDownLatchService;

    @Test
    public void testCountDown() throws InterruptedException {
        countDownLatchService.lock("down");
//        RCountDownLatch countDownLatch2 = redissonClient.getCountDownLatch("down");
//        System.out.println("count--");
//        countDownLatch2.countDown(); //计数器减一 ，如果变为零，通知所有的线程
    }


}
