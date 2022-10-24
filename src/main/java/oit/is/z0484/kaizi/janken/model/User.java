package oit.is.z0484.kaizi.janken.model;

import org.springframework.stereotype.Component;

@Component
public class User {
  int id;
  String name;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
