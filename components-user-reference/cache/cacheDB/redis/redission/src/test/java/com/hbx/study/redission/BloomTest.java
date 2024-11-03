package com.hbx.study.redission;

import com.hbx.study.redission.learn.bloom.BloomFilter;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class BloomTest extends BaseTest{

    @Autowired
    private BloomFilter bloomFilter;

    @Test
    @Override
    public void testBloom() {
        RBloomFilter<String> b1 = bloomFilter.getBloomFilter("b1", 2l, 0.01f);
        b1.add("a1");
        b1.add("a2");

        long contains = b1.contains(Arrays.asList("a1", "a2"));
        Assert.isTrue(contains==2,"test fail");

        Assert.isTrue(!b1.contains("false"),"test fail");
        b1.add("false");
        Assert.isTrue(b1.contains("false"),"test fail");
    }

    RBloomFilter<Integer> b3;
    @PostConstruct
    public void init(){
        b3 = bloomFilter.getBloomFilter("b3", null,null);
//        for(int i=0;i<1000000;i++){
//            b3.add(i);
//        }
    }

    @Test
    public void testMulBloom() {
        int failCount = 0;
        for(int i = 0;i<50;i++){
            try {
                Assert.isTrue(b3.contains(i),"test fail");
            }catch (Exception e){
                failCount++;
            }
        }
        System.out.println(failCount);
    }
}
