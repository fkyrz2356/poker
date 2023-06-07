package jp.kobe_u.cs27.Poker.application.controller;

import jp.kobe_u.cs27.Poker.application.bean.User;
import jp.kobe_u.cs27.Poker.application.Service.UserService;
import jp.kobe_u.cs27.Poker.application.bean.ResultData;
import jp.kobe_u.cs27.Poker.application.repository.ResultDataRepository;
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
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;





@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    private ResultDataRepository resultDataRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String registerNewUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "ID or Password do not meet the conditions");
            return "signup";  // バリデーションエラーがあればsignupページに戻す
        }
        
        try {
            // ユーザーを保存する
            userService.saveNewUser(user);
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }

        return "redirect:/login";
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
    
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<ResultData> results = resultDataRepository.findByUserIDAndDate(user.getId(), today);
        if (!results.isEmpty()) {
            model.addAttribute("score", results.get(0).getSolved());
            model.addAttribute("notChallenge", false);
        } else {
            model.addAttribute("score", 0);
            model.addAttribute("notChallenge", true);
        }

        // yesterDate ではない
        LocalDate yesterdayDate = LocalDate.now().minusDays(0);
        String yesterday = yesterdayDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<ResultData> results2 = resultDataRepository.findByDateOrderBySolvedDescRestTimeDesc(today);
        model.addAttribute("results", results2);
        model.addAttribute("date", yesterdayDate.format(DateTimeFormatter.ofPattern("M/d")));
        model.addAttribute("participate", (results2.size() + 1) / 2);
    
        return "home";
    }
    

    @PostMapping("/startPractice")
    public String startPractice() {
        return "redirect:/practice";
    }

    @PostMapping("/startTwoPlayers")
    public String startTwoPlayers() {
        return "redirect:/twoPlayers";
    }

    @PostMapping("/startPerformance")
    public String startPerformance() {
        return "redirect:/challenge";
    }

}