package oit.is.z0484.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  @Select("SELECT * FROM matches")
  ArrayList<Match> selectAllMatches();

  @Select("SELECT * FROM matches where isActive = true")
  Match selectActiveMatch();

  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, #{isActive});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match matches);

  @Update("UPDATE matches SET isActive = false where isActive = true")
  void updateMatchF();

}
