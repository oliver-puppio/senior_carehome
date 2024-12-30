package com.boat.pension.controller;

import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.DeviceDTO;
import com.boat.pension.dto.RealTimeRecordDTO;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Device;
import com.boat.pension.service.DeviceService;
import com.boat.pension.service.EntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "DeviceController", description = "设备管理")
@RequestMapping("/api/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private EntityService entityService;

    //    已测试
    @ApiOperation(value = "获取某一养老院的全部未禁用的设备列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getList() {
        Admin admin= entityService.getAdmin();
        return CommonResult.success(deviceService.listAllByInstid(admin.getInstid()));
    }

    //    已测试
    @ApiOperation("添加设备")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestParam(value = "id") Integer id) {
        Admin admin= entityService.getAdmin();
        CommonResult commonResult;
        int count = deviceService.addDevice(admin.getInstid(), id);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    //    已测试，但是在绑定状态下禁用是否 需要跟床位解绑？    不需要，解绑才能禁用，前端判断
    @ApiOperation("禁用/启用")
    @RequestMapping(value = "/{id}/ditch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult ditch(@PathVariable("id") Integer id,
                              @RequestParam(value = "flag") Integer flag) {

        int count = deviceService.updateState(id,flag);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    //    已测试
    @ApiOperation("获取指定机构的获取某一状态的设备列表")
    @RequestMapping(value = "/list/{state}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<Device>> listState(@RequestAttribute("admin") Admin admin,
                                                @PathVariable(value = "state") Integer state) {

        return CommonResult.success(deviceService.listAllByInstidAndState(admin.getInstid(),state));
    }


    //    已测试
    @ApiOperation("查询设备实时监控记录")
    @RequestMapping(value = "/{id}/live", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult live(@PathVariable("id")int id){
        RealTimeRecordDTO realTimeRecordDTO = deviceService.realtimeRecord(id);
        if(realTimeRecordDTO == null)
            return CommonResult.failed();
        return CommonResult.success(realTimeRecordDTO);
    }

//    @Scheduled(fixedRate = 5000)
//    @ApiOperation("设备5s采集数据")
//    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult monitor(@RequestAttribute("admin") Admin admin){
//        CommonResult.success(deviceService.monitor(admin));
//    }
}
