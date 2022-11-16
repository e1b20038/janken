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
import oit.is.z0484.kaizi.janken.model.MatchInfo;
import oit.is.z0484.kaizi.janken.model.MatchInfoMapper;

@Controller
public class JankenController {

  @Autowired
  Entry entry;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchInfoMapper matchinfoMapper;

  @GetMapping("/janken")
  public String janken(ModelMap model, Principal prin) {
    ArrayList<User> users = userMapper.selectAllUserName();
    ArrayList<Match> result = matchMapper.selectAllMatches();
    ArrayList<MatchInfo> matchinfo = matchinfoMapper.selectActiveInfo();
    String loginUser = prin.getName();
    model.addAttribute("login_user", loginUser);
    model.addAttribute("users", users);
    model.addAttribute("result", result);
    model.addAttribute("matchinfo", matchinfo);
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

  @GetMapping("/fight")
  public String fight(@RequestParam Integer id, @RequestParam String te, ModelMap model, Principal prin) {
    String loginUserName = prin.getName();
    User loginUser = userMapper.selectByName(loginUserName);
    User cpu = userMapper.selectById(id);
    Janken result = new Janken(te);
    String enemy = result.getEnemy();

    model.addAttribute("loginUser", loginUser);
    model.addAttribute("cpu", cpu);
    model.addAttribute("hand", te);
    model.addAttribute("enemy", enemy);
    model.addAttribute("hantei", result.getResult());

    Match addMatch = new Match(loginUser.getId(), id, te, enemy);

    matchMapper.insertMatch(addMatch);

    return "match.html";
  }

  @GetMapping("/wait")
  public String wait(@RequestParam Integer id, @RequestParam String te, ModelMap model, Principal prin) {
    String loginUserName = prin.getName();
    User loginUser = userMapper.selectByName(loginUserName);

    ArrayList<MatchInfo> lookMatchInfo = matchinfoMapper.selectAllMatchInfo();
    model.addAttribute("login_user", loginUserName);

    int flag = 0;

    for (MatchInfo mi : lookMatchInfo) {
      if (mi.getUser2() == loginUser.getId() && mi.getUser1() == id && mi.getIsActive() == true) {
        Match matching = new Match(mi.getUser1(), loginUser.getId(), mi.getUser1Hand(), te);
        matching.setIsActive(true);
        matchMapper.insertMatch(matching);
        flag = 1;
      }
    }
    if (flag == 0) {
      MatchInfo addMatchInfo = new MatchInfo(loginUser.getId(), id, te);
      addMatchInfo.setIsActive(true);

      matchinfoMapper.insertMatchInfo(addMatchInfo);
    }
    return "wait.html";
  }
}
