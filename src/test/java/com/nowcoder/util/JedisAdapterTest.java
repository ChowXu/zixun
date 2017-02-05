package com.nowcoder.util;

import com.nowcoder.ToutiaoApplication;
import com.nowcoder.model.User;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/3 下午9:54
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
public class JedisAdapterTest {


    @Autowired
    JedisAdapter jedisAdapter;

    @Test
    public void testObject(){
        User user = new User();
        user.setHeadUrl("http://image.nowcoder/head/");
        user.setSalt("salt");
        user.setId(1);
        user.setName("chown");
        jedisAdapter.setObject("user1xxx",user);

        User u = jedisAdapter.getObject("user1xxx",User.class);
        System.out.println(ToStringBuilder.reflectionToString(u));

    }




}
