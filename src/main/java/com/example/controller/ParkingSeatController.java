package com.example.controller;

import com.example.bean.ParkingSeat;
import com.example.bean.ParkingSeatExample;
import com.example.service.ParkingSeatService;
import com.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/3.
 */
@Controller
@RequestMapping("/parkingseat")
public class ParkingSeatController {
    @Autowired
    private ParkingSeatService seatService;

    /**
     * 指定的停车场的车位列表
     * @param placeId
     * @param index
     * @param map
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listParkingSeat(@RequestParam(value = "placeId", required = false) Integer placeId,
                                  @RequestParam(value = "index", required = false) Integer index,
                                  Map<String, Object> map) {
        if(index == null || index < 1) {
            index = 1;
        }
        int size = 20;
        ParkingSeatExample example = new ParkingSeatExample();
        ParkingSeatExample.Criteria criteria = example.createCriteria();
        criteria.andParkingPlaceIdEqualTo(placeId);
        example.setOrderByClause(" 1 " + CommonUtil.getPageSql(index, size));
        List<ParkingSeat> seats = seatService.selectByExample(example);
        long count = seatService.countByExample(example);
        //计算一共有多少行， （每行五列）
//        int row = seats.size() % 5 == 0 ? seats.size() / 5 : seats.size() / 5 + 1;
        int row = CommonUtil.getRow(seats.size(), 5);
        map.put("seats", seats);
        map.put("row", row);
        map.put("total", CommonUtil.getTotal(count, 20));
        map.put("index", index);
        map.put("placeId", placeId);
        return "parkingseat/parkingseat_list";
    }
}
