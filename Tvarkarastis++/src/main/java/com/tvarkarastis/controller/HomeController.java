package com.tvarkarastis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by audri on 2017-04-10.
 */

@Controller
public class HomeController {
    @RequestMapping(value="/")
    public static String index(){
        return "index";
    }
}
