package xyz.ldszyn.news.dao;

import org.apache.ibatis.annotations.*;
import xyz.ldszyn.news.POJO.Users;
@Mapper
public interface usersDao {
    @Select("SELECT id, name, username, password, age, sex, tel, create_time,isban FROM users WHERE username = #{username}")
    Users query(String username);

    @Insert("insert into users(name, username, password, age, sex, tel, create_time) " +
            "VALUES(#{name}, #{username}, #{password}, #{age}, #{sex}, #{tel},now())")
    int reg(Users user);

    @Insert("UPDATE users SET JWT=#{JWT} WHERE id=#{id}")
    int addJWT(String JWT,int id);
    @Select("SELECT COUNT(1) FROM users WHERE JWT = #{JWT}")
    int queryJWT(String JWT);
    @Select("SELECT id, name, username, password, head_img, age, sex, tel, create_time FROM users WHERE JWT = #{JWT}")
    Users getuser(String JWT);
    @Update("UPDATE users SET name=#{name},password=#{password},age=#{age},sex=#{sex},tel=#{tel},head_img=#{headImg} WHERE username=#{username}")
    int upuser(Users user);
    @Update("UPDATE users SET JWT=' ' WHERE JWT = #{JWT}")
    int upjwt(String JWT);
    @Select("SELECT news_id FROM comments WHERE id = #{commentId}")
    int queryid(int commentId);
    @Select("SELECT user_id FROM news WHERE id = #{newsId}")
    int queryuserid(int newsId);

    @Delete("DELETE FROM comments WHERE id=#{commentId}")
    int delcomment(int commentId);
}
