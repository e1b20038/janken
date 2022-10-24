package oit.is.z0484.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0484.kaizi.janken.model.Janken;
import oit.is.z0484.kaizi.janken.model.Entry;
import oit.is.z0484.kaizi.janken.model.User;
import oit.is.z0484.kaizi.janken.model.UserMapper;
import oit.is.z0484.kaizi.janken.model.Match;
import oit.is.z0484.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
  Entry entry;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @GetMapping("/janken")
  public String janken(ModelMap model, Principal prin) {
    ArrayList<User> users = userMapper.selectAllUserName();
    ArrayList<Match> result = matchMapper.selectAllMatches();
    String loginUser = prin.getName();
    model.addAttribute("login_user", loginUser);
    model.addAttribute("users", users);
    model.addAttribute("result", result);
    return "janken.html";
  }

  @PostMapping("/janken")
  public String janken(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam Integer id, ModelMap model, Principal prin) {
    String loginUserName = prin.getName();
    User loginUser = userMapper.selectByName(loginUserName);
    User cpu = userMapper.selectById(id);
    System.out.println(cpu.getName());

    model.addAttribute("loginUser", loginUser);
    model.addAttribute("cpu", cpu);
    return "match.html";
  }

  @GetMapping("/hand")
  public String hand(@RequestParam String te, ModelMap model) {
    Janken result = new Janken(te);
    model.addAttribute("hand", te);
    model.addAttribute("enemy", result.getEnemy());
    model.addAttribute("hantei", result.getResult());
    return "match.html";
  }

}
