package net.qianqiuxi.register.model.mapper;

import net.qianqiuxi.register.model.dao.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user ( `id`, `username`, `password` ) values (NULL, #{user.username}, #{user.password})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn=" id")
    int insert(@Param("user")User user);

    @Select("select id from user where username = #{user.username} and password = #{user.password}")
    Integer checkAuth(@Param("user")User user);

    @Select("select id from user where username = #{user.username}")
    Integer getUserId(@Param("user")User user);

    @Select("select id from user where username = #{username}")
    Integer getUserIdByName(@Param("username")String username);
}
