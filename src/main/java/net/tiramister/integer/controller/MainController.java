package net.tiramister.integer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import net.tiramister.integer.form.ResultForm;

@Controller
public class MainController {
  @GetMapping("/")
  public String index(
      @RequestParam(name = "integer", defaultValue = "0") Long integer, Model model) {
    model.addAttribute("n", integer);
    model.addAttribute("result", ResultForm.build(integer));
    return "index";
  }
}
