package oit.is.z0484.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0484.kaizi.janken.model.Janken;
import oit.is.z0484.kaizi.janken.model.Entry;

import oit.is.z0484.kaizi.janken.model.User;
import oit.is.z0484.kaizi.janken.model.UserMapper;

@Controller
public class JankenController {

  /**
   * @GetMapping("/janken")
   * public String janken() {
   * return "janken.html";
   * }
   */

  /*
   * @Autowired
   * private Entry entry;
   *
   * @GetMapping("/janken")
   * public String janken(Principal prin, ModelMap model) {
   * String loginUser = prin.getName();
   * this.entry.addUser(loginUser);
   * model.addAttribute("entry", this.entry);
   * return "janken.html";
   * }
   */

  @Autowired
  UserMapper userMapper;

  @GetMapping("/janken")
  @Transactional
  public String janken(ModelMap model) {
    ArrayList<User> users1 = userMapper.selectAllUserName();
    model.addAttribute("users1", users1);
    return "janken.html";
  }

  @PostMapping("/janken")
  public String janken(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/hand")
  public String hand(@RequestParam String te, ModelMap model) {

    Janken result = new Janken(te);
    model.addAttribute("hand", te);
    model.addAttribute("enemy", result.getEnemy());
    model.addAttribute("hantei", result.getResult());
    return "janken.html";
  }

  /**
   *
   * @param model Thymeleafにわたすデータを保持するオブジェクト
   * @param prin  ログインユーザ情報が保持されるオブジェクト
   * @return
   */

  /*
   * @GetMapping("/janken1")
   * public String janken1(ModelMap model, Principal prin) {
   * String loginUser = prin.getName(); // ログインユーザ情報
   * model.addAttribute("login_user", loginUser);
   * return "janken.html";
   * }
   *
   * @GetMapping("/janken2")
   * public String janken2(Principal prin, ModelMap model) {
   * String loginUser = prin.getName();
   * this.entry.addUser(loginUser);
   * model.addAttribute("entry", this.entry);
   *
   * return "janken.html";
   * }
   */

}
