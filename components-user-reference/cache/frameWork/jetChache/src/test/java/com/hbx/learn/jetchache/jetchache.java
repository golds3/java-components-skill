package com.hbx.learn.jetchache;

import com.hbx.learn.jetchache.domain.service.impl.TestJetChache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = MyJetChache.class)
public class jetchache {

    @Autowired
    private TestJetChache testJetChache;

    @Test
    public void testInsert() {
        Object xx = testJetChache.insert("xx", "10");
        System.out.println(xx);
    }

    @Test
    public void testInsertAnn() throws InterruptedException {
        testJetChache.insertAnno("hxx", "123");
        Object o = testJetChache.insertAnno("hxx", "1234");
        System.out.println(o); //123
        TimeUnit.SECONDS.sleep(1);
        testJetChache.delAnno("hxx");
        TimeUnit.SECONDS.sleep(2);
        o = testJetChache.insertAnno("hxx", "1234");
        System.out.println(o); //123
//        testJetChache.delAnno("hxx");
        testJetChache.updateAnno("hxx", "1234");
        o = testJetChache.insertAnno("hxx", "1234");
        System.out.println(o);

    }
}
