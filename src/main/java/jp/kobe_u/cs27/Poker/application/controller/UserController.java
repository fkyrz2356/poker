package jp.kobe_u.cs27.Poker.application.controller;

import jp.kobe_u.cs27.Poker.application.bean.User;
import jp.kobe_u.cs27.Poker.application.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login.html")
    public String login() {
        System.out.print("Path3");
        return "login";
    }

    @GetMapping("/signup.html")
    public String signup(Model model) {
        System.out.print("Path1");
        model.addAttribute("user", new User());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String registerNewUser(@ModelAttribute User user) {
        System.out.print("Path2");
        userService.saveNewUser(user);
        return "login";
    }
}
