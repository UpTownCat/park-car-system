package com.example.controller;

import com.example.bean.ParkingPlace;
import com.example.bean.ParkingPlaceExample;
import com.example.bean.ParkingSeatExample;
import com.example.service.ParkingPlaceService;
import com.example.service.ParkingSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/3.
 */
@Controller
@RequestMapping("/parkingplace")
public class ParkingPlaceController {
    @Autowired
    private ParkingPlaceService parkingPlaceService;
    @Autowired
    private ParkingSeatService parkingSeatService;

    /**
     * 增加一个停车场
     * @param parkingPlace
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public int addParkingPlace(ParkingPlace parkingPlace) {
        System.out.println("init");
        return parkingPlaceService.insert(parkingPlace);
    }

    /**
     * 获得停车场列表
     * @param index
     * @param map
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listParkingPlace(@RequestParam(value = "index", required = false)Integer index, Map<String, Object> map) {
        if(index == null || index < 1) {
            index = 1;
        }
        ParkingPlaceExample example = new ParkingPlaceExample();
        int size = 9;
        int begin = (index - 1) * size;
        int end = begin + size;
        example.setOrderByClause("1 limit " + begin + " , " + size);
        List<ParkingPlace> places = parkingPlaceService.selectByExample(example);
        for(int i = 0; i < places.size(); i++) {
            ParkingPlace place = places.get(i);
            ParkingSeatExample seatExample = new ParkingSeatExample();
            ParkingSeatExample.Criteria criteria = seatExample.createCriteria();
            criteria.andParkingPlaceIdEqualTo(place.getId());
            criteria.andCarIdIsNull();
            long availableSeat = parkingSeatService.countByExample(seatExample);
            place.setAvailableSeat(availableSeat);
        }
        int row = places.size() % 3 == 0 ? places.size() / 3 : places.size() / 3 + 1;
        map.put("places", places);
        map.put("row", row);
        return "/parkingplace/parkingplace_list";
    }

}
