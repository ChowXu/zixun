package com.nowcoder.async;

import java.util.List;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/3 下午11:29
 */
public interface EventHandler {

    void doHandler(EventModel model);

    List<EventType> getSupportEventTypes();
}
