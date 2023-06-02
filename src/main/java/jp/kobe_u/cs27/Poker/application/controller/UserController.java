package jp.kobe_u.cs27.Poker.application.controller;

import jp.kobe_u.cs27.Poker.application.bean.User;
import jp.kobe_u.cs27.Poker.application.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;



@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/signup.html")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String registerNewUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";  // バリデーションエラーがあればsignupページに戻す
        }

        // ユーザーを保存する
        userService.saveNewUser(user);

        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("password") String password, Model model, HttpSession session) {
        Optional<User> user = userService.findUserById(id);
    
        if (user.isEmpty()) {
            model.addAttribute("errorMessage", "User not found.");
            return "login";
        }
    
        if (!password.equals(user.get().getPassword())) {
            model.addAttribute("errorMessage", "Invalid password.");
            return "login";
        }
    
        session.setAttribute("user", user.get());
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "home";
    }
    



}