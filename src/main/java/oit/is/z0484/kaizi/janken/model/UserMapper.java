package oit.is.z0484.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT name from users")
  ArrayList<User> selectAllUserName();

  @Insert("INSERT INTO users (name) VALUES (#{userName});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(User users);
}
