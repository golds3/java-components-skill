package com.hbx.study.redission;

import com.hbx.study.redission.learn.base.hash.HashType;
import com.hbx.study.redission.learn.base.set.SetType;
import com.hbx.study.redission.learn.base.string.BloomFilter;
import com.hbx.study.redission.learn.base.string.BucketTypeOperate;
import com.hbx.study.redission.learn.base.string.KeyOperate;
import com.hbx.study.redission.learn.base.string.RateLimeter;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
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
    public void testInsertKV(){
        RBucket<Object> bucket = redissonClient.getBucket("buck2");
        Object o = bucket.get();
        System.out.println(o);

    }

    @Autowired
    private KeyOperate keyOperate;
    @Test
    public void testKeys(){
        List<String> keys = keyOperate.keys();
        System.out.println(keys);
    }

    @Test
    public void testDeleteKey(){
        keyOperate.deleteKey("test2");
    }


    @Autowired
    private BucketTypeOperate bucketTypeOperate;
    @Test
    public void kvSet(){
        bucketTypeOperate.KVSet("buck2", Arrays.asList("3",new Object(),3.0),null,false);
    }
    @Autowired
    private BloomFilter bloomFilter;
    @Test
    public void testBloom(){
        RBloomFilter<String> test = bloomFilter.getBloomFilter("test", 255000000L, 0.03);
        test.add("aa1");
        if (test.contains("aa")){
            System.out.println("aa命中");
        }
    }

    @Autowired
    private RateLimeter rateLimeter;
    @Test
    public void testLimit(){
        rateLimeter.tryAcquire("rate",1,5);
        if (!rateLimeter.tryAcquire("rate",1,5)){
            System.out.println("限流了");
        }
    }

    @Autowired
    private HashType hashType;
    @Test
    public void testHash(){
        hashType.set("hash1","h1","v2",false);
        Object fieldValue = hashType.getFieldValue("hash1", "h1");
        System.out.println(fieldValue);
        if (hashType.removeField("hash1","h1")){
            System.out.println("删除成功");
        }
        fieldValue = hashType.getFieldValue("hash1", "h1");
        Assert.isNull(fieldValue,"not null");
    }

    @Autowired
    private SetType setType;
    @Test
    public void testSet(){
        setType.set("s1","sv1");

        List<Integer> list = Arrays.asList(2, 1);
        setType.sortSet("ss1", 2, (i,j)->{return i-j;});
        setType.sortSet("ss1", 1, (i,j)->{return i-j;});
    }
}
