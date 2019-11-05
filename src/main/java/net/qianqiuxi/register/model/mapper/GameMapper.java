package net.qianqiuxi.register.model.mapper;

import net.qianqiuxi.register.model.dao.Game;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GameMapper {

    @Insert("INSERT INTO `game` ( `token` ) VALUES (#{token})")
    int insert(@Param("token") String token);

    @Update("UPDATE `game` " +
            "SET `player1_id` = #{game.player1Id}" +
            ", `player2_id` = #{game.player2Id}" +
            ", `winner` =  #{game.winner}" +
            ", `detail` =  #{game.detail}" +
            "  WHERE token = #{game.token}")
    int update(@Param("game") Game game);
}
