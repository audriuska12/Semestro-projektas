package com.tvarkarastis.controller;

import com.tvarkarastis.dao.EventManagerDao;
import com.tvarkarastis.dao.UserManagerDao;
import com.tvarkarastis.entity.Event;
import com.tvarkarastis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by audri on 2017-04-10.
 */

@Controller
public class HomeController {

    @RequestMapping(value="/")
    public static String indexHome (HttpSession session, ModelMap modelMap){

        modelMap.put("username", session.getAttribute("username"));
        return "index";

    }
    @RequestMapping("/index")
    public static String index (HttpSession session, ModelMap modelMap) {
        return indexHome(session, modelMap);
    }



}
