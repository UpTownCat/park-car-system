package com.example.controller;

import com.example.bean.CarOwner;
import com.example.service.CarOwnerService;
import com.example.util.CommonUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    /**
     * 充值
     * @param id
     * @param carOwner
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public int updateCarOwner(@PathVariable Integer id, CarOwner carOwner) {
        carOwner.setId(id);
        return ownerService.updateByPrimaryKeySelective(carOwner);
    }

    /**
     * 更换头像
     * @param photo
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String changePhoto(@PathVariable Integer id, String oldPhoto, MultipartFile photo) {
        System.out.println("init");
        CarOwner carOwner = new CarOwner();
        carOwner.setId(id);
        try {
            String realName = CommonUtil.savePhoto(photo, CommonUtil.getConfigValue("photoLocation"));
            carOwner.setPhoto(realName);
            if(oldPhoto.length() > 8) {
                FileUtils.deleteQuietly(new File(CommonUtil.getConfigValue("photoLocation") + oldPhoto));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ownerService.updateByPrimaryKeySelective(carOwner) + "";
    }

}
