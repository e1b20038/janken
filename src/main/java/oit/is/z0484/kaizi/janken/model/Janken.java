package oit.is.z0484.kaizi.janken.model;

import java.util.Random;

public class Janken {
  String hantei;
  String enemy;

  public Janken(String me) {

    Random rand = new Random();
    int num = rand.nextInt(3);

    if (num == 0) {
      enemy = "gu";
    } else if (num == 1) {
      enemy = "pa";
    } else {
      enemy = "tyoki";
    }

    if (me.equals(enemy)) {
      hantei = "Draw";
    } else if (me.equals("gu") && enemy.equals("tyoki") || me.equals("pa") && enemy.equals("gu")
        || me.equals("tyoki") && enemy.equals("pa")) {
      hantei = "You Win!";
    } else {
      hantei = "You Lose";
    }
  }

  public static String match(Match hand) {
    String user1Hand = hand.getUser1Hand();
    String user2Hand = hand.getUser2Hand();

    if (user1Hand == user2Hand) {
      return "Draw";
    } else if (user1Hand.equals("gu") && user2Hand.equals("choki") || user1Hand.equals("pa") && user2Hand.equals("gu")
        || user1Hand.equals("choki") && user2Hand.equals("pa")) {
      return "user1 Win!";
    } else {
      return "user2 Win!";
    }
  }

  public String getResult() {
    return hantei;
  }

  public void setResult(String hantei) {
    this.hantei = hantei;
  }

  public String getEnemy() {
    return enemy;
  }

  public void setEnemy(String enemy) {
    this.enemy = enemy;
  }
}
