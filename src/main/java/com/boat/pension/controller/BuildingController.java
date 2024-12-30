package com.boat.pension.controller;


import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.BuildingParam;
import com.boat.pension.dto.CarehomeParam;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Building;
import com.boat.pension.service.AdminService;
import com.boat.pension.service.BuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 楼栋管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller//没有问题
@Api(tags = "BuildingController", description = "楼栋管理")
@RequestMapping("/api/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

//    已测试
    @ApiOperation(value = "获取全部楼栋列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Building>> getList(@RequestAttribute("admin") Admin admin) {
        return CommonResult.success(buildingService.listAllBuilding(admin.getInstid()));
    }

//    已测试
    @ApiOperation("添加楼栋")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestAttribute("admin") Admin admin,
                            @RequestParam String buildingname) {
        BuildingParam buildingParam = new BuildingParam();
        buildingParam.setName(buildingname);
        CommonResult commonResult;
        int count = buildingService.addBuilding(admin.getId(), buildingParam);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        System.out.println(count);
        return commonResult;
    }

//    已测试
    @ApiOperation("删除指定楼栋信息")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id) {
        int count = buildingService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
