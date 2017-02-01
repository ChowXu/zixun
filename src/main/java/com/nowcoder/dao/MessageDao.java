package com.nowcoder.dao;

import com.nowcoder.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/1 上午10:41
 */
@Mapper
public interface MessageDao {

    String TABLE_NAME = "message";
    String INSERT_FIELDS = " from_id, to_id, content, has_read, conversation_id, created_date";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{fromId}, #{toId}, #{content}, #{hasRead}, #{conversationId}, #{createdDate})"})
    int addMessage(Message message);


    @Select({"select", SELECT_FIELDS, " from ", TABLE_NAME, " where conversation_id = #{conversationId} order by id desc limit #{offset}, #{limit}"})
    List<Message> getConversationDetail(@Param("conversationId") String ConversationId,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);

    List<Message> getConversationList(@Param("userId") int userId,
                                      @Param("offset") int offset,
                                      @Param("limit") int limit);

    @Select({"select count(id) from ", TABLE_NAME, " where has_read = 0 and to_id =#{userId} and conversation_id = #{conversationId}"})
    int getConversationUnreadCount(@Param("userId") int userId, @Param("conversationId") String conversationId);


}
