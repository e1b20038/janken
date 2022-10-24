package oit.is.z0484.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT id, name FROM users WHERE id = #{id}")
  User selectById(int id);

  @Select("SELECT id, name FROM users WHERE name = #{name}")
  User selectByName(String name);

  @Select("SELECT * from users")
  ArrayList<User> selectAllUserName();

}
