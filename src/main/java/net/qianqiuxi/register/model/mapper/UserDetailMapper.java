package net.qianqiuxi.register.model.mapper;

import net.qianqiuxi.register.model.dao.UserDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDetailMapper {

    @Update("update userdetail set  `win` = `win` + 1 where id = #{userDetail.id}")
    int updateWin(@Param("userDetail") UserDetail userDetail);

    @Update("update userdetail set  `lose` = `lose` + 1 where id = #{userDetail.id}")
    int updateLose(@Param("userDetail") UserDetail userDetail);

    @Insert("insert into userdetail ( `id` ) values (#{id})")
    int insert(@Param("id")Integer id);

    @Select("select * from userdetail where id = #{id}")
    UserDetail get(@Param("id") Integer id);
}
