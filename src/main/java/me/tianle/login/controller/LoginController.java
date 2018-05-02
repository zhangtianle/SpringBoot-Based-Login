package me.tianle.login.controller;

import me.tianle.login.bean.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @RequestMapping("/")
    public ModelAndView index(ModelMap map) {
        map.addAttribute("host", "https://tianle.me");
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        String name = user.getName();
        String password = user.getPassword();

        if (name.equals("qinya") && password.equals("tianle")) {
            return "Success";
        } else {
            return "Failed";
        }
    }
}
