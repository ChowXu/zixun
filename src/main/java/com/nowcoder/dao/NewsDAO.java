package com.nowcoder.dao;

import com.nowcoder.model.News;
import com.nowcoder.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by nowcoder on 2016/7/2.
 */
@Mapper
public interface NewsDAO {
    String TABLE_NAME = "news";
    String INSERT_FIELDS = " title, link, image, like_count, comment_count, created_date, user_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    String SELECT_USER_FIELDS = " id, name, password, salt, head_url ";
    String USER_TABLE_NAME = "user";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{title},#{link},#{image},#{likeCount},#{commentCount},#{createdDate},#{userId})"})
    int addNews(News news);

    List<News> selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset,
                                       @Param("limit") int limit);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where id = #{newsId}"})
    News selectById(String newsId);

    @Select({"select", SELECT_USER_FIELDS, "from", USER_TABLE_NAME, "where id in ", "( select user_id from", TABLE_NAME, "where id = #{newsId})"})
    User selectUserByNewsId(String newsId);

    @Update({"update ", TABLE_NAME, " set comment_count = #{count} where id = #{entityId}"})
    int updateCommentCount(@Param("entityId") int entityId,
                           @Param("count") int count);


    @Update({"update ", TABLE_NAME, " set like_count = #{likeCount} where id = #{newsId}"})
    void updateLikeCont(@Param("newsId")int newsId, @Param("likeCount")int likeCount);
}

