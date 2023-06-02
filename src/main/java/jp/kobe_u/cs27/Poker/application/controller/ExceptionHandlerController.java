package jp.kobe_u.cs27.Poker.application.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        // ここでエラーメッセージをモデルに追加します
        model.addAttribute("errorMessage", e.getMessage());
        // エラーページにリダイレクトします
        return "error";
    }
}