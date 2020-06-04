package com.spo.controller;

import com.spo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        log.info("home controller");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            User user = (User) authentication.getPrincipal();
            model.addAttribute("sessionUser",user);
            log.info("[home controller] --------- 세션유저 : " + user);
        }

        return "home";
    }
}
