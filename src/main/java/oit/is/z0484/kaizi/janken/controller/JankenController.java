package oit.is.z0484.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0484.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String janken() {
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
}
