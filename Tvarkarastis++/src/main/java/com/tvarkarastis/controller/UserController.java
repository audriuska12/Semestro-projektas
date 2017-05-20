package com.tvarkarastis.controller;

import com.tvarkarastis.dao.LoginDao;
import com.tvarkarastis.dao.RegisterDao;
import com.tvarkarastis.dao.UserManagerDao;
import com.tvarkarastis.entity.LoginBean;
import com.tvarkarastis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

/**
 * Created by Edvinas on 2017-05-17.
 */
@Controller
public class UserController {

    @Autowired
    ResourceBundle resources;

    @GetMapping("/login")
    public String login(ModelMap modelMap) {
        LoginBean obj = new LoginBean();
        modelMap.put("obj", obj);
        return "login";
    }

    @PostMapping("/loginprocess")
    public String loginprocess (LoginBean obj, HttpSession session, ModelMap modelMap) {
        boolean status = LoginDao.validate(obj);

        if (status) {
            session.setAttribute("username", obj.getUsername());
            int userId = UserManagerDao.getUserId(obj.getUsername());
            session.setAttribute("userId", userId);
            session.removeAttribute("logfailed");
            return "redirect:/";
        } else {
            session.setAttribute("logfailed", true);
            modelMap.put("obj", obj);
            modelMap.put("errorMessage", resources.getString("msg.loginFailed"));
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("username");
        return "index";
    }

    @GetMapping("/register")
    public String register (ModelMap modelMap) {
        User user = new User();
        modelMap.put("newUser", user);
        return "register";
    }

    @PostMapping("/registerprocess")
    public String registerprocess (User newUser, HttpSession session, ModelMap modelMap) {

        int status = RegisterDao.register(newUser);
        if(status > 0) {
            session.setAttribute("username", newUser.getUsername());
            session.removeAttribute("regfailed");
            return "redirect:/";
        } else {
            session.setAttribute("regfailed", true);
            modelMap.put("newUser", newUser);
            switch (status) {
                case -4:
                    modelMap.put("errorMessage", resources.getString("msg.userExists"));
                    break;
                case -3:
                    modelMap.put("errorMessage", resources.getString("msg.invalidPassword"));
                    break;
                case -2:
                    modelMap.put("errorMessage", resources.getString("msg.invalidEmail"));
                    break;
                case -1:
                    modelMap.put("errorMessage", resources.getString("msg.invalidUsername"));
                    break;
                default:
                    modelMap.put("errorMessage", resources.getString("msg.createUserError"));
            }
            return "register";
        }
    }

    @GetMapping("/profile")
    public String profile () {
        return "profile";
    }
}
