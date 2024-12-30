package com.boat.pensionwx.controller;

import com.boat.pensionwx.dto.EventDTO;
import com.boat.pensionwx.dto.LogDTO;
import com.boat.pensionwx.dto.SeniorHealthDetailDTO;
import com.boat.pensionwx.result.CommonResult;
import com.boat.pensionwx.service.SeniorService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@Api(tags = "SeniorController", value = "老人管理")
@RequestMapping("/api/senior")
public class SeniorController {
    @Autowired
    SeniorService seniorService;

//    @ApiOperation("老人详情")
    @GetMapping(value = "/{id}/detail")
    @ResponseBody
    public CommonResult detail(@PathVariable("id") int id) {
        SeniorHealthDetailDTO result = seniorService.detail(id);
        if (result == null)
            return CommonResult.failed();
        return CommonResult.success(result);
    }

//    @ApiOperation("老人健康日志")
    @RequestMapping(value = {"/{id}/log/{date}", "/{id}/log"}, method = RequestMethod.GET)
    @ResponseBody
    public CommonResult log(@PathVariable("id") int id, @PathVariable(value = "date", required = false) String date) {
        LogDTO result = date == null ? seniorService.log(id) : seniorService.log(id, date);
        return CommonResult.success(result);
    }

//    @ApiOperation("老人异常事件")
    @RequestMapping(value = "/{id}/events", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult event(@PathVariable("id") int id) {
        List<EventDTO> result = seniorService.event(id);
        return CommonResult.success(result);
    }
}
