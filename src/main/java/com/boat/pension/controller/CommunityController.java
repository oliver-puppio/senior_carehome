package com.boat.pension.controller;


import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.CarehomeParam;
import com.boat.pension.dto.CommunityParam;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Building;
import com.boat.pension.model.Institution;
import com.boat.pension.service.BuildingService;
import com.boat.pension.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 社区管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "CommunityController", description = "社区管理")
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

//    已测试
    @ApiOperation(value = "获取某一养老院的全部社区列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Institution>> getList(@RequestAttribute("admin") Admin admin) {
        return CommonResult.success(communityService.listAllByInstid(admin.getInstid()));
    }

//    已测试
    @ApiOperation("添加社区")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestAttribute("admin") Admin admin,
                            @RequestParam String name,
                            @RequestParam String loc,
                            @RequestParam String leader,
                            @RequestParam String tel) {
        CommunityParam communityParam = new CommunityParam();
        communityParam.setName(name);
        communityParam.setLoc(loc);
        communityParam.setLeader(leader);
        communityParam.setTel(tel);

        CommonResult commonResult;
        int count = communityService.addCommunity(admin.getInstid(),communityParam);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

//    已测试
    @ApiOperation("修改指定社区信息")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id,
                               @RequestParam String name,
                               @RequestParam String loc,
                               @RequestParam String leader,
                               @RequestParam String tel) {
        CommunityParam communityParam = new CommunityParam();
        communityParam.setName(name);
        communityParam.setLoc(loc);
        communityParam.setLeader(leader);
        communityParam.setTel(tel);
        int count = communityService.update(id, communityParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        System.out.println(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除指定社区信息")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id) {
        int count = communityService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
