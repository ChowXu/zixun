package com.nowcoder;

import com.nowcoder.dao.NewsDAO;
import com.nowcoder.service.LikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
@WebAppConfiguration
public class ToutiaoApplicationTests {


	@Autowired
	NewsDAO newsDAO;

	@Autowired
    LikeService likeService;



	@Test
	public void contextLoads() {
//		Assert.notNull(newsDAO.selectById("1"));
//        Assert.notNull(newsDAO.selectUserByNewsId("2"));
        likeService.like(1,1,1);
	}

}
