package com.nowcoder.async.Handler;

import com.nowcoder.async.EventHandler;
import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventType;
import org.springframework.stereotype.Service;

import javax.xml.ws.handler.Handler;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/3 下午11:30
 */

@Service
public class LikeHandler implements EventHandler {
    @Override
    public void doHandler(EventModel model) {
        System.out.println("liked");
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Collections.singletonList(EventType.LIKE);
    }
}
