package jp.kobe_u.cs27.Poker.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jp.kobe_u.cs27.Poker.application.repository.SiteAccessRepository;
import jp.kobe_u.cs27.Poker.application.bean.SiteAccess;

@Controller
public class SiteAccessController {

    @Autowired
    private SiteAccessRepository siteAccessRepository;

    @GetMapping("/access-count")
    public String accessCount(Model model) {
        java.util.Date today = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(today.getTime());
        SiteAccess siteAccess = siteAccessRepository.findByDate(sqlDate);
        if (siteAccess != null) {
            model.addAttribute("count", siteAccess.getCount());
        } else {
            model.addAttribute("count", 0);
        }
        return "access-count";
    }
}
