package com.example.controller;


import com.example.bean.Parking;
import com.example.bean.ParkingExample;
import com.example.bean.ParkingPlace;
import com.example.bean.ParkingSeat;
import com.example.service.CarService;
import com.example.service.ParkingPlaceService;
import com.example.service.ParkingSeatService;
import com.example.service.ParkingService;
import com.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public String listParkings(Integer carOwnerId, @RequestParam(value = "index", required = false) Integer index,
                               Map<String, Object> map) {
        if(index == null || index < 1){
            index = 1;
        }
        ParkingExample example = new ParkingExample();
        ParkingExample.Criteria criteria = example.createCriteria();
        criteria.andCarOwnerIdEqualTo(carOwnerId);
        example.setOrderByClause(" 1 " + CommonUtil.getPageSql(index, 6));
        List<Parking> parkings = parkingService.selectByExample(example);
        for(int i = 0; i < parkings.size(); i++) {
            Parking parking = parkings.get(i);
            parking.setCar(carService.selectByPrimaryKey(parking.getCarId()));
            ParkingSeat parkingSeat = parkingSeatService.selectByPrimaryKey(parking.getParkingSeatId());
            parking.setParkingPlace(parkingPlaceService.selectByPrimaryKey(parkingSeat.getParkingPlaceId()));
            if(parking.getOutTime() == null) {
                parking.setPrice(CommonUtil.countMoneyOfParking(parking.getInTime(), new Date(), parking.getParkingPlace().getMoneyPerHour()));
            }
        }
        map.put("parkings", parkings);
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
}
