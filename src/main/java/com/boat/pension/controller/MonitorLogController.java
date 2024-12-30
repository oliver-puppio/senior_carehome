package com.boat.pension.controller;

import com.boat.pension.api.CommonResult;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Monitorlog;
import com.boat.pension.model.Record;
import com.boat.pension.service.EventService;
import com.boat.pension.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 监测日志管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "MonitorLogController", description = "监测日志管理")
@RequestMapping("/api/log")
public class MonitorLogController {
    @Autowired
    private LogService logService;

    @ApiOperation(value = "生成日志")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult create(@RequestAttribute("admin") Admin admin) {
        return CommonResult.success(logService.create(admin.getInstid()));
    }
}
