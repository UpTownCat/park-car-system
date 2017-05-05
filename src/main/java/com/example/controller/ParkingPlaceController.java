package com.example.controller;

import com.example.bean.ParkingPlace;
import com.example.bean.ParkingPlaceExample;
import com.example.bean.ParkingSeatExample;
import com.example.service.ParkingPlaceService;
import com.example.service.ParkingSeatService;
import com.example.util.CommonUtil;
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
        return parkingPlaceService.insert(parkingPlace);
    }

    /**
     * 获得停车场列表
     * @param index
     * @param map
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listParkingPlace(@RequestParam(value = "index", required = false)Integer index,
                                   @RequestParam(value = "order", required = false)Integer tag,
                                   @RequestParam(value = "name", required = false)String name,
                                   Map<String, Object> map) {
        if(index == null || index < 1) {
            index = 1;
        }
        ParkingPlaceExample example = new ParkingPlaceExample();
        //拼接查询条件
        ParkingPlaceExample.Criteria criteria = example.createCriteria();
        if(name != null) {
            criteria.andNameLike("%" + name + "%");
        }else {
            name = "";
        }
        //进行分页和排序
        String order = getPriceOrder(tag);
        example.setOrderByClause(order + CommonUtil.getPageSql(index, 9));
        List<ParkingPlace> places = parkingPlaceService.selectByExample(example);
        setAvailableSeat(places);
        //计算一共有多少行
//        int row = places.size() % 3 == 0 ? places.size() / 3 : places.size() / 3 + 1;
        int row = CommonUtil.getRow(places.size(), 3);
        //获取和计算记录总数
        long count = parkingPlaceService.countByExample(example);
        map.put("places", places);
        map.put("row", row);
        map.put("index", index);
        map.put("order", tag);
        map.put("name", name);
        map.put("total", CommonUtil.getTotal(count, 9));
        return "/parkingplace/parkingplace_list";
    }

    /**
     * 获得根据价格排序的str
     * @param tag
     * @return
     */
    private String getPriceOrder(Integer tag) {
        //不需要排序
        if(tag == null || tag == 0) {
            return "1";
        }else {
            //价格由低到高
            if(tag == 1) {
                return " money_per_hour";
            }else {
                //价格由高到低
                return " money_per_hour desc";
            }
        }
    }

    /**
     * 为获得的停车场查询可用的车位数
     * @param places
     */
    private void setAvailableSeat(List<ParkingPlace> places) {
        for(int i = 0; i < places.size(); i++) {
            ParkingPlace place = places.get(i);
            ParkingSeatExample seatExample = new ParkingSeatExample();
            ParkingSeatExample.Criteria criteria = seatExample.createCriteria();
            criteria.andParkingPlaceIdEqualTo(place.getId());
            criteria.andCarIdIsNull();
            long availableSeat = parkingSeatService.countByExample(seatExample);
            place.setAvailableSeat(availableSeat);
        }
    }

}
