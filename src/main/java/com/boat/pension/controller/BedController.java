package com.boat.pension.controller;


import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.BedDeviceDTO;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Bed;
import com.boat.pension.service.BedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 床位管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "BedController", description = "床位管理")
@RequestMapping("/api/bed")
public class BedController {
    @Autowired
    private BedService bedService;


//    已测试
    @ApiOperation("删除指定床位信息")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id) {
        int count = bedService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

//    已测试，可以添加和解绑，解绑但是设备和床位不符时出错，绑定时已绑定或者选择已绑定设备出错
    @ApiOperation("更改设备绑定（添加、解绑）")
    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult bindChange(@RequestParam Integer deviceid,
                                   @RequestParam Integer bedid,
                                   @RequestParam int flag) {
        BedDeviceDTO bedDeviceDTO = new BedDeviceDTO();
        bedDeviceDTO.setBedid(bedid);
        bedDeviceDTO.setDeviceid(deviceid);
        int count = 0;
        if(flag>0) {//添加
            count = bedService.addDevice(bedDeviceDTO);
        } else {//解绑
            count = bedService.deleteDevice(bedDeviceDTO);
        }

        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

//    已测试
    @ApiOperation(value = "获取某一养老院的未绑定床位列表")
    @RequestMapping(value = "/list/nobind", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Bed>> getListNoBind(@RequestAttribute("admin") Admin admin) {
        return CommonResult.success(bedService.listNoBindByInstid(admin.getInstid()));
    }

    @ApiOperation(value = "获取某一养老院的未入住的床位列表")
    @RequestMapping(value = "/list/nosenior", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Bed>> getListNoSenior(@RequestAttribute("admin") Admin admin) {
        return CommonResult.success(bedService.listNoSeniorByInstid(admin.getInstid()));
    }

}
