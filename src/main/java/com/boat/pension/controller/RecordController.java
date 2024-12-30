package com.boat.pension.controller;

import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.HealthRepo;
import com.boat.pension.dto.RealTimeRecordDTO;
import com.boat.pension.model.Admin;
import com.boat.pension.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "RecordController", value = "监测数据管理")
@RequestMapping("/api/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @ApiOperation("设备实时监控记录")
    @RequestMapping(value = "/live", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult live(@RequestAttribute("admin") Admin admin){
        List<RealTimeRecordDTO> realTimeRecordDTO = recordService.realtimeRecord(admin.getInstid());
        if(realTimeRecordDTO==null)
            return CommonResult.failed();
        return CommonResult.success(realTimeRecordDTO);
    }

    @ApiOperation("设备实时监控检查")
    @RequestMapping(value = "/livecheck", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult liveCheck(@RequestAttribute("admin") Admin admin) {
        List<HealthRepo> realTimeRecordDTO = recordService.realtimeHealthCheck(admin.getInstid());
        return CommonResult.success(realTimeRecordDTO);
    }

    @ApiOperation("设备实时监控记录")
    @RequestMapping(value = "/{instid}/testlive", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult testlive(@PathVariable Integer instid){
        List<RealTimeRecordDTO> realTimeRecordDTO = recordService.realtimeRecord(instid);
        if(realTimeRecordDTO==null)
            return CommonResult.failed();
        return CommonResult.success(realTimeRecordDTO);
    }
}
