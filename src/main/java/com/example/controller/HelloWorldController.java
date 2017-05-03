package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/5/2.
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    public String greeting() {
        return "hello";
    }
}
