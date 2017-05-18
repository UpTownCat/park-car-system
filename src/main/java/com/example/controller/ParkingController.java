package com.example.controller;


import com.example.bean.*;
import com.example.service.CarService;
import com.example.service.ParkingPlaceService;
import com.example.service.ParkingSeatService;
import com.example.service.ParkingService;
import com.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/3.
 */
@Controller
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ParkingSeatService parkingSeatService;
    @Autowired
    private ParkingPlaceService parkingPlaceService;
    @Autowired
    private CarService carService;

    /**
     * 停车
     * @param parking
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public int addParking(Parking parking) {
        parking.setInTime(new Date());
        parking.setPrice(0d);
        ParkingSeat parkingSeat = new ParkingSeat();
        parkingSeat.setId(parking.getParkingSeatId());
        parkingSeat.setCarId(parking.getCarId());
        //更新车位表
        parkingSeatService.updateByPrimaryKeySelective(parkingSeat);
        return parkingService.insertSelective(parking);
    }

    /**
     * 结束停车
     * @param parking
     * @return
     */
    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public int leave(Parking parking) {
        parking.setOutTime(new Date());
        //计算停车费用
        ParkingSeat parkingSeat = parkingSeatService.selectByPrimaryKey(parking.getParkingSeatId());
        ParkingPlace parkingPlace = parkingPlaceService.selectByPrimaryKey(parkingSeat.getParkingPlaceId());
        double moneyOfParking = CommonUtil.countMoneyOfParking(parking.getInTime(), parking.getOutTime(), parkingPlace.getMoneyPerHour());
        parking.setPrice(moneyOfParking);
        //更新车位
        parkingSeat.setCarId(null);
        parkingSeatService.updateByPrimaryKey(parkingSeat);
        //更新停车记录
        return parkingService.updateByPrimaryKey(parking);
    }

    /**
     * 显示停车列表
     * @param carOwnerId
     * @param index
     * @param map
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listParkings(Integer carOwnerId, ParkingCondiction parkingCondiction, Map<String, Object> map) {
        ParkingExample example = null;
        try {
            example = getParkingExample(parkingCondiction);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Parking> parkings = parkingService.selectByExample(example);
        //为停车记录绑定所在的停车场和所使用的车辆
        for(int i = 0; i < parkings.size(); i++) {
            Parking parking = parkings.get(i);
            parking.setCar(carService.selectByPrimaryKey(parking.getCarId()));
            ParkingSeat parkingSeat = parkingSeatService.selectByPrimaryKey(parking.getParkingSeatId());
            parking.setParkingPlace(parkingPlaceService.selectByPrimaryKey(parkingSeat.getParkingPlaceId()));
            if(parking.getOutTime() == null) {
                parking.setPrice(CommonUtil.countMoneyOfParking(parking.getInTime(), new Date(), parking.getParkingPlace().getMoneyPerHour()));
            }
        }
        //查询该用户的车辆
        CarExample carExample = new CarExample();
        CarExample.Criteria carExampleCriteria = carExample.createCriteria();
        carExampleCriteria.andCarOwnerIdEqualTo(carOwnerId);
        List<Car> cars = carService.selectByExample(carExample);
        long count = parkingService.countByExample(example);
        long total = CommonUtil.getTotal(count, 8);
        map.put("parkings", parkings);
        map.put("cars", cars);
        map.put("index", parkingCondiction.getIndex());
        map.put("total", total);
        map.put("carOwnerId", carOwnerId);
        return "parking/parking_list";
    }

    /**
     * 获取数据库的模型数据
     * @param id
     * @return
     */
    @ModelAttribute
    public Parking getModelFromDatabase(@RequestParam(value = "id", required = false) Integer id) {
        if(id != null) {
            return parkingService.selectByPrimaryKey(id);
        }
        return new Parking();
    }

    /**
     *获取查询条件的parkingExample
     * @param parkingCondiction
     */
    private ParkingExample getParkingExample(ParkingCondiction parkingCondiction) throws ParseException {
        ParkingExample example = new ParkingExample();
        ParkingExample.Criteria criteria = example.createCriteria();
        Integer index = 1;
        if(parkingCondiction.getIndex() != null && parkingCondiction.getIndex().trim().length() != 0){
            index = Integer.parseInt(parkingCondiction.getIndex());
            if(index < 1) {
                index = 1;
            }
        }
        String  order = " 1 ";
        if(parkingCondiction.getOrder() != null) {
            if(parkingCondiction.getOrder().equals("1")) {
                order = " price ";
            }else {
                if(parkingCondiction.getOrder().equals("2")) {
                    order = " price desc ";
                }
            }
        }
        if(parkingCondiction.getDate() != null && parkingCondiction.getDate().trim().length() != 0) {
            Date inTime = CommonUtil.stringToDate(parkingCondiction.getDate());
            Date outTime = new Date(inTime.getTime() + CommonUtil.DAY);
            criteria.andInTimeGreaterThan(inTime);
            criteria.andOutTimeLessThan(outTime);
        }
        example.setOrderByClause(order + CommonUtil.getPageSql(index, 8));
        if(parkingCondiction.getCarId() != null && parkingCondiction.getCarId().trim().length() != 0) {
            int carId = Integer.parseInt(parkingCondiction.getCarId());
            if(carId > 0) {
                criteria.andCarIdEqualTo(carId);
            }
        }
        return example;
    }
}
