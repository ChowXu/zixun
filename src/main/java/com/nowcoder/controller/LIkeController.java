package com.nowcoder.controller;

import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventProducer;
import com.nowcoder.async.EventType;
import com.nowcoder.model.HostHolder;
import com.nowcoder.service.LikeService;
import com.nowcoder.service.NewsService;
import com.nowcoder.util.ToutiaoUtil;
import dataDictionary.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/3 下午4:10
 */

@Controller
public class LikeController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    LikeService likeService;

    @Autowired
    NewsService newsService;

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(path = {"/like"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String like(@RequestParam("newsId") int newsId) {
        int userId = hostHolder.getUser().getId();
        long likeCount = likeService.like(userId, EntityType.ENTITY_NEWS.getindex(),newsId);
        newsService.updateLikeCount(newsId, likeCount);

        EventModel eventModel = new EventModel(EventType.LIKE);
        eventProducer.fireEvent(eventModel);
        return ToutiaoUtil.getJSONString(0,String.valueOf(likeCount));
    }


    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String disLike(@RequestParam("newsId") int newsId) {
        int userId = hostHolder.getUser().getId();
        long likeCount = likeService.disLike(userId, EntityType.ENTITY_NEWS.getindex(),newsId);
        newsService.updateLikeCount(newsId, likeCount);
        return ToutiaoUtil.getJSONString(0,String.valueOf(likeCount));
    }




}
