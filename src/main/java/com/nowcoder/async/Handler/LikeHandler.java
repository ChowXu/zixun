package com.nowcoder.async.Handler;

import com.nowcoder.async.EventHandler;
import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventType;
import com.nowcoder.util.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/3 下午11:30
 */

@Service
public class LikeHandler implements EventHandler {

    @Autowired
    MailService mailService;

    @Override
    public void doHandler(EventModel model) {
        System.out.println("liked");
        Map<String, Object> map = new HashMap<>();
        map.put("username", "zhx");
//        mailService.sendWithHTMLTemplate("15651342081@163.com", "为XXX 点了赞", "mails/welcome.html", map);

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Collections.singletonList(EventType.LIKE);
    }
}
