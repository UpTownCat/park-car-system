package com.example.controller;

import com.example.bean.CarOwner;
import com.example.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/3.
 */
@Controller
@RequestMapping("/carowner")
public class CarOwnerController {
    @Autowired
    private CarOwnerService ownerService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public int registCarOwner(CarOwner carOwner) {
        carOwner.setBalance(0d);
        carOwner.setGender(0);
        carOwner.setPhoto("default.jpg");
        return ownerService.insert(carOwner);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCarOwner(@PathVariable Integer id, Map<String, Object> map) {
        CarOwner carOwner = ownerService.selectByPrimaryKey(id);
        map.put("carOwner", carOwner);
        return "carowner/carowner_detail";
    }
}
