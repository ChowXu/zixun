package com.nowcoder.async;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/3 下午11:42
 */
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    private Map<EventType, List<EventHandler>> config = new HashMap<>();

    private ApplicationContext applicationContext;

    @Autowired
    JedisAdapter jedisAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if (beans != null) {
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                List<EventType> eventTypes = entry.getValue().getSupportEventTypes();
                for (EventType eventType : eventTypes) {
                    if (!config.containsKey(eventType)) {
                        config.put(eventType, new ArrayList<EventHandler>());
                    }
                    List<EventHandler> list = config.get(eventType);
                    list.add(entry.getValue());
                }
            }
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String key = RedisKeyUtil.getEventQueueKey();
                    List<String> events = jedisAdapter.brpop(0, key);
                    for (String msg : events) {
                        if (msg.equals(key)) {
                            continue;
                        }
                        EventModel eventModel = JSONObject.parseObject(msg, EventModel.class);
                        if (!config.containsKey(eventModel.getType())) {
                            logger.error("不能处理的事件" + eventModel.getType());
                            continue;
                        }
                        for (EventHandler eventhandler : config.get(eventModel.getType())) {
                            eventhandler.doHandler(eventModel);
                        }

                    }
                }

            }
        });
        thread.start();

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
