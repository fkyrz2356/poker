package jp.kobe_u.cs27.Poker.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jp.kobe_u.cs27.Poker.application.Service.ResultDataService;

@Controller
public class DataController {
  
    private final ResultDataService service;

    public DataController(ResultDataService service) {
        this.service = service;
    }

    @PostMapping("/submitData")
    public String handlePostRequest(@RequestParam("userId") String userId,
                                    @RequestParam("solved") String solved, 
                                    @RequestParam("date") String date, 
                                    @RequestParam("restTime") String restTime) {
        // 数値型に変換
        int solvedNum = Integer.parseInt(solved);
        double restTimeNum = Double.parseDouble(restTime.substring(0, restTime.length() - 1));
        service.saveData(userId, solvedNum, date, restTimeNum);
    
        return "redirect:/home"; // 成功ページへのリダイレクト
    }
    
}
