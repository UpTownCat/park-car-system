package com.example.controller;

import com.example.bean.Parking;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/navbar", method = RequestMethod.GET)
    public String showNavbar() {
        return "nav";
    }

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    @ResponseBody
    public String testAjaxFileUpload(String name) {
        System.out.println("init ---- name = " + name);
        return "ok";
    }

    @RequestMapping(value = "/dataBinding", method = RequestMethod.GET)
    public void testDataBinding(Parking parking){
        System.out.println(parking);
        System.out.println(parking.getCar().getBranch());
    }
}
