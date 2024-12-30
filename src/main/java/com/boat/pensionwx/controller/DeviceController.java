package com.boat.pensionwx.controller;

import com.boat.pensionwx.dto.RealTimeRecordDTO;
import com.boat.pensionwx.result.CommonResult;
import com.boat.pensionwx.service.DeviceService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
//@Api(tags = "DeviceController", value = "老人管理")
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

//    @ApiOperation("设备实时监控记录")
    @RequestMapping(value = "/{id}/live", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult live(@PathVariable("id")int id){
        RealTimeRecordDTO realTimeRecordDTO=deviceService.realtimeRecord(id);
        if(realTimeRecordDTO==null)
            return CommonResult.failed("设备未开启健康监测");
        return CommonResult.success(realTimeRecordDTO);
    }
}
