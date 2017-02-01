package com.nowcoder.dao;

import com.nowcoder.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.boot.bind.YamlJavaBeanPropertyConstructor;

import java.util.List;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/1/30 下午9:27
 */
@Mapper
public interface CommentDao {

    String TABLE_NAME = " comment";
    String INSERT_FIELDS = "user_id, content, entity_id, entity_type, status, created_date";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId}, #{content}, #{entityId}, #{entityType}, #{status}, #{createdDate})"})
    int addComment(Comment comment);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME,
            " where entity_id = #{entityId} and entity_type = #{entityType} order by id desc"})
    List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);


    @Select({"select count(id) from ", TABLE_NAME,
            " where entity_id = #{entityId} and entity_type = #{entityType} order by id desc"})
    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);


    @Update({"update ", TABLE_NAME, " set status = #{status}  where entity_type = #{entityType} and entity_id = #{entityId}"})
    int updateStatus(@Param("entityId") int entityId, @Param("entityType") int entityType, @Param("status") int status);

}
