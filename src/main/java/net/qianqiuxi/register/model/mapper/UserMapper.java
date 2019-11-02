package net.qianqiuxi.register.model.mapper;

import net.qianqiuxi.register.model.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user ( `username`, `password` ) values (#{user.username}, #{user.password})")
    int insert(@Param("user")User user);

    @Select("select id from user where username = #{user.username} and password = #{user.password}")
    Integer checkAuth(@Param("user")User user);

    @Select("select id from user where username = #{user.username}")
    Integer getUserId(@Param("user")User user);
}
