package com.nowcoder.service;

import com.nowcoder.ToutiaoApplication;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/5 下午5:46
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
public class LikeServiceTest {


    @Autowired
    LikeService likeService;


    @Before
    public void setUp() {
        System.out.println("setUp");

    }

    @After
    public void tearDown() {
        System.out.println("tearDown");

    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");

    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");

    }


    @Test
    public void getLikeStatus() throws Exception {

    }

    @Test
    public void like() throws Exception {

        likeService.like(123, 1, 1);
        Assert.assertEquals(1, likeService.getLikeStatus(123, 1, 1));


    }

    @Test
    public void disLike() throws Exception {
        System.out.println("dislike");

    }

    @Test
    public void testException() {
//        throw new IllegalArgumentException("xxx");
    }



}