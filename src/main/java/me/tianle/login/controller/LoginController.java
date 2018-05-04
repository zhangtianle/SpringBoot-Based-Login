package me.tianle.login.controller;

import me.tianle.login.bean.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class LoginController {

    Set<User> set = new HashSet<>();
    Map<String, String> userMap = new HashMap<>();

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String reg(@ModelAttribute User user) {
        String name = user.getName();
        String password = user.getPassword();
        set.add(user);
        if (userMap.containsKey(name)) {
            return "0";
        } else {
            userMap.put(name, password);
            return "1";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {

        String name = user.getName();
        String password = user.getPassword();
        if (!userMap.containsKey(name)) {
            return "0";
        } else if (userMap.get(name).equals(password)) {
            return "1";
        } else {
            return "2";
        }

    }
}
