package xyz.ldszyn.news.dao;

import org.apache.ibatis.annotations.*;
import xyz.ldszyn.news.POJO.Result;
import xyz.ldszyn.news.POJO.comments;
import xyz.ldszyn.news.POJO.news;
import xyz.ldszyn.news.POJO.type;

import java.util.List;

@Mapper
public interface newsDao {
    @Select("SELECT type_name FROM news_type WHERE user_id=#{id}")
    String[] gettype(int id);
    @Select("SELECT type_name FROM news_type")
    String[] getalltype();

    @Insert("INSERT INTO news(title,content,type_id,create_time,user_id) VALUES (#{title},#{content},#{typeId},now(),#{userId})")
    int addnews(news news);
    @Insert("INSERT INTO news_type(type_name,create_tim,user_id) VALUES (#{typeName},now(),#{userId})")
    int addtype(type type);
    @Select("SELECT id,title,type_id,create_time FROM news WHERE user_id=#{id}")
    news[] getnews(int id);
    @Select("SELECT content FROM news WHERE id=#{id}")
    String getnewsdata(int id);
    @Delete("DELETE FROM news WHERE id=#{id} and user_id=#{userId}")
    int delenews(int userId, int id);

    @Delete("DELETE FROM news_type WHERE type_name=#{typeName} and user_id=#{id}")
    int delenewstype(int id, String typeName);
    @Update("UPDATE news SET title=#{news.title}, content=#{news.content}, type_id=#{news.typeId}, create_time=now() WHERE id = #{news.id} and user_id=#{userId}")
    int upnews(@Param("news") news news, @Param("userId") int userId);

    @Select("SELECT id,title,type_id,create_time FROM news")
    news[] getallnews();
    @Select("<script>"
            + "SELECT id, title, type_id, create_time FROM news WHERE id IN "
            + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<news> getalllikenews(@Param("ids") int[] ids);
    @Insert("INSERT INTO users_like(user_id,news_id) VALUES (#{id},#{id1})")
    int addlist(int id, int id1);
    @Delete("DELETE FROM users_like WHERE user_id=#{id} and news_id=#{id1}")
    int removelist(int id, int id1);
    @Select("SELECT COUNT(1) FROM users_like WHERE user_id=#{id} and news_id=#{id1}")
    int looklist(int id, int id1);
    @Select("SELECT id,title,type_id,create_time FROM news WHERE type_id=#{typeId}")
    news[] looktype(String typeId);

    @Select("SELECT news_id FROM users_like WHERE user_id=#{id}")
    int[] getlike(int id);
    @Select("SELECT id,user_id,news_id,text,time FROM comments WHERE news_id=#{id}")
    comments[] getcomments(int id);
    @Insert("INSERT INTO comments(user_id,news_id,text,time) VALUES (#{userId},#{newsId},#{text},now())")
    int addcomments(comments comments);
    @Delete("DELETE FROM comments WHERE news_id=#{id}")
    void deluserenews(int id);
    @Delete("DELETE FROM users_like WHERE news_id=#{id}")
    void deluserelike(int id);
    @Delete("DELETE FROM news WHERE type_id=#{typeName}")
    void deltypenews(String typeName);
}
