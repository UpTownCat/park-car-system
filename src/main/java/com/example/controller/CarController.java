package com.example.controller;

import com.example.bean.Car;
import com.example.bean.CarExample;
import com.example.bean.CarOwner;
import com.example.service.CarService;
import com.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 */
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 添加车辆
     * @param car
     * @param carPhoto
     * @param session
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public int addCar(String branch, String number, MultipartFile carPhoto, HttpSession session) {
        try {
            number =  URLDecoder.decode(number, "utf-8");
            branch = URLDecoder.decode(branch, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Car car = new Car();
        car.setNumber(number);
        car.setBranch(branch);
        CarOwner owner = (CarOwner) session.getAttribute("owner");
        if(owner == null) {
            car.setCarOwnerId(1);
        } else {
            car.setCarOwnerId(owner.getId());
        }
        String realName = null;
        if(carPhoto != null && !carPhoto.isEmpty()) {
            try {
                realName = CommonUtil.savePhoto(carPhoto, CommonUtil.getConfigValue("photoLocation"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        car.setPhoto(realName);
        return carService.insert(car);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCars(Integer ownerId, Integer index, Map<String, Object> map) {
        if(index == null || index < 1) {
            index = 1;
        }
        CarExample example = new CarExample();
        CarExample.Criteria criteria = example.createCriteria();
        criteria.andCarOwnerIdEqualTo(ownerId);
        example.setOrderByClause(" 1 " + CommonUtil.getPageSql(index, 2));
        List<Car> cars = carService.selectByExample(example);
        long count = carService.countByExample(example);
        map.put("cars", cars);
        map.put("ownerId", ownerId);
        map.put("index", index);
        map.put("total", CommonUtil.getTotal(count, 2));
        return "car/car_list";
    }

    @RequestMapping(value = "/json/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Car> listCarsJson(Integer ownerId) {
        CarExample example = new CarExample();
        CarExample.Criteria criteria = example.createCriteria();
        criteria.andCarOwnerIdEqualTo(ownerId);
        return carService.selectByExample(example);
    }

    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    @ResponseBody
    public String greet() {
        return "1";
    }

}
