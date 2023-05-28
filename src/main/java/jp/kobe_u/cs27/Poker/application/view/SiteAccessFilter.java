package jp.kobe_u.cs27.Poker.application.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import jp.kobe_u.cs27.Poker.application.repository.SiteAccessRepository;
import jp.kobe_u.cs27.Poker.application.bean.SiteAccess;

@Component
public class SiteAccessFilter implements Filter {

    @Autowired
    private SiteAccessRepository siteAccessRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if ("/twoPlayers".equals(httpRequest.getServletPath())) {
            java.util.Date today = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            SiteAccess siteAccess = siteAccessRepository.findByDate(sqlDate);
            if (siteAccess == null) {
                siteAccess = new SiteAccess();
                siteAccess.setDate(sqlDate);
                siteAccess.setCount(1L);
            } else {
                siteAccess.setCount(siteAccess.getCount() + 1);
            }
            siteAccessRepository.save(siteAccess);
        }
        chain.doFilter(request, response);
    }
}