package com.boat.pension.controller;

import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.CarehomeParam;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Institution;
import com.boat.pension.service.AdminService;
import com.boat.pension.service.CarehomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 养老院管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "CarehomeController", description = "养老院管理")
@RequestMapping("/api/carehome")
public class CarehomeController {

    @Autowired
    private CarehomeService carehomeService;

//    已测试
    @ApiOperation("获取指定养老院信息")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody //表示此方法的响应结果以json格式进行数据的响应给到前端
    public CommonResult<Institution> getItem(@RequestAttribute("admin") Admin admin) {
        Institution carehome = carehomeService.getCarehomeByCarehomeId(admin.getInstid());
        return CommonResult.success(carehome);
    }

//    已测试
//    @ApiOperation("添加养老院")
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult add(@Validated @RequestBody CarehomeParam carehome) {
//        CommonResult commonResult;
//        int count = carehomeService.addCarehome(carehome);
//        if (count == 1) {
//            commonResult = CommonResult.success(count);
//        } else {
//            commonResult = CommonResult.failed();
//        }
//        return commonResult;
//    }

//    已测试，修改名字时会影响床位、房间
    @ApiOperation("修改指定养老院信息")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id,
                               @RequestParam String name,
                               @RequestParam String loc,
                               @RequestParam String companyName,
                               @RequestParam String license,
                               @RequestParam String leader,
                               @RequestParam String tel) {

        CarehomeParam carehomeParam = new CarehomeParam();
        carehomeParam.setName(name);
        carehomeParam.setLoc(loc);
        carehomeParam.setCompanyName(companyName);
        carehomeParam.setLicense(license);
        carehomeParam.setLeader(leader);
        carehomeParam.setTel(tel);

        int count = carehomeService.update(id, carehomeParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        System.out.println(count);
        return CommonResult.failed();
    }

//    已测试
//    @ApiOperation("删除指定养老院信息")
//    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult delete(@PathVariable Integer id) {
//        int count = carehomeService.delete(id);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
}
