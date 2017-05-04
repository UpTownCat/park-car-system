package com.example.controller;

import com.example.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/4.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @RequestMapping(value = "/photo", method = RequestMethod.GET)
    public void getPhoto(String realName, HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        CommonUtil.outputPhoto(realName, out);
    }

}
