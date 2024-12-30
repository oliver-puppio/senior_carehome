package com.boat.pension.controller;

import com.boat.pension.api.CommonResult;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Record;
import com.boat.pension.model.Senior;
import com.boat.pension.service.RecordService;
import com.boat.pension.service.SeniorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 生成测试数据Controller
 * Created by hxy on 2022/7/1.
 */
@RestController
@Api(tags = "TestController", description = "生成测试数据")
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private RecordService recordService;

    @ApiOperation(value = "生成测试数据")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public List<Record> create() {
        return recordService.create();
    }

    @RequestMapping(value="/test2",method = RequestMethod.GET)
    public CommonResult test2() {
        return CommonResult.success("test2");
    }
}
