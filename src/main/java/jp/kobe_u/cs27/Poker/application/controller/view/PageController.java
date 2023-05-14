package jp.kobe_u.cs27.Poker.application.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {

  @GetMapping("/")
  public String showLandingPage() {
    return "index";
  }

  @GetMapping("/enter")
  public String enter() {
    return "practice";
  }

}
