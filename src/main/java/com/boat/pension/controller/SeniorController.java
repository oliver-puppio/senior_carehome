package com.boat.pension.controller;

import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.EventDTO;
import com.boat.pension.dto.LogDTO;
import com.boat.pension.dto.SeniorDTO;
import com.boat.pension.dto.SeniorParam;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Senior;
import com.boat.pension.service.EntityService;
import com.boat.pension.service.SeniorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 老人管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "SeniorController", description = "老人管理")
@RequestMapping("/api/senior")
public class SeniorController {
    @Autowired
    private SeniorService seniorService;

    @Autowired
    EntityService entityService;

    //    已测试
    @ApiOperation(value = "获取某一养老院的全部老人列表,flag=0表示获取院内的老人列表，flag=1表示获取院外接入社区的老人列表")
    @RequestMapping(value = "/list/{flag}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getList( @PathVariable("flag") int flag) {
        Admin admin=entityService.getAdmin();
        if (flag == 0) {//获取养老院的老人列表

            return CommonResult.success(seniorService.listAllByInstid(admin.getInstid()));
        } else {//获取养老院管理机构的老人列表
            return CommonResult.success(seniorService.listAllinCommunityByInstid(admin.getInstid()));
        }
    }


    //    已测试
    @ApiOperation("老人入住")
    @RequestMapping(value = "/addincarehome", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addincarehome(@RequestParam String pic,
                            @RequestParam String name,
                            @RequestParam String gender,
                            @RequestParam String idnum,
                            @RequestParam String tel,
                            @RequestParam Integer heart,
                            @RequestParam Integer breath,
                            @RequestParam String cont1,
                            @RequestParam String ctel1,
                            @RequestParam(required = false) String cont2,
                            @RequestParam(required = false) String ctel2,
                            @RequestParam Integer bedID) {
        Admin admin= entityService.getAdmin();
        SeniorParam senior = new SeniorParam();
        senior.setPic(pic);
        senior.setName(name);
        senior.setGender(gender);
        senior.setIdnum(idnum);
        senior.setTel(tel);
        senior.setHeart(heart);
        senior.setBreath(breath);
        senior.setCont1(cont1);
        senior.setCtel1(ctel1);
        senior.setCont2(cont2);
        senior.setCtel2(ctel2);
        senior.setBedid(bedID);
        senior.setFlag(0);

        System.out.println(senior);
        CommonResult commonResult;
        int count = seniorService.addSenior(admin.getInstid(), senior);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation("老人入住")
    @RequestMapping(value = "/addincommunity", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addincommunity(@RequestParam String pic,
                                      @RequestParam String name,
                                      @RequestParam String gender,
                                      @RequestParam String idnum,
                                      @RequestParam String tel,
                                      @RequestParam("heart") Integer heart,
                                      @RequestParam("breath") Integer breath,
                                      @RequestParam String cont1,
                                      @RequestParam String ctel1,
                                      @RequestParam(required = false) String cont2,
                                      @RequestParam(required = false) String ctel2,
                                      @RequestParam Integer communityID,
                                      @RequestParam String buildingName,
                                      @RequestParam Integer deviceID,
                                      @RequestParam String roomNum) {
        Admin admin= entityService.getAdmin();
        SeniorParam senior = new SeniorParam();
        senior.setPic(pic);
        senior.setName(name);
        senior.setGender(gender);
        senior.setIdnum(idnum);
        senior.setTel(tel);
        senior.setHeart(heart);
        senior.setBreath(breath);
        senior.setCont1(cont1);
        senior.setCtel1(ctel1);
        senior.setCont2(cont2);
        senior.setCtel2(ctel2);
        senior.setFlag(1);
        senior.setCommunityid(communityID);
        senior.setBuildingname(buildingName);
        senior.setDeviceid(deviceID);
        senior.setNum(roomNum);


        System.out.println(senior);
        CommonResult commonResult;
        int count = seniorService.addSenior(senior.getCommunityid(), senior);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    //    已测试
    @ApiOperation("修改指定老人信息")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id,
                               @RequestParam String pic,
                               @RequestParam String name,
                               @RequestParam String gender,
                               @RequestParam String idnum,
                               @RequestParam String tel,
                               @RequestParam Integer heart,
                               @RequestParam Integer breath,
                               @RequestParam String cont1,
                               @RequestParam String ctel1,
                               @RequestParam String cont2,
                               @RequestParam String ctel2) {
        SeniorParam senior = new SeniorParam();
        senior.setPic(pic);
        senior.setName(name);
        senior.setGender(gender);
        senior.setIdnum(idnum);
        senior.setTel(tel);
        senior.setHeart(heart);
        senior.setBreath(breath);
        senior.setCont1(cont1);
        senior.setCtel1(ctel1);
        senior.setCont2(cont2);
        senior.setCtel2(ctel2);


        int count = seniorService.update(id, senior);
        if (count > 0) {
            return CommonResult.success(count);
        }
        System.out.println(count);
        return CommonResult.failed();
    }

    //    已测试
    @ApiOperation("老人离院")
    @RequestMapping(value = "/{id}/leave", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult leave(@PathVariable Integer id) {
        int count = seniorService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    //    已测试
    @ApiOperation("获取指定老人信息")
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult detail(@PathVariable Integer id) {
        return CommonResult.success(seniorService.getSeniorBySeniorId(id));
    }

    //    已测试
    @ApiOperation("分配床位")
    @RequestMapping(value = "/{seniorid}/bind/{bedid}", method = RequestMethod.POST)
    @ResponseBody //表示此方法的响应结果以json格式进行数据的响应给到前端
    public CommonResult bind(@PathVariable("seniorid") Integer seniorid,
                             @PathVariable(value = "bedid") Integer bedid) {

        int count = seniorService.bind(seniorid, bedid);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();

    }

    //    已测试
    @ApiOperation("老人健康日志")
    @RequestMapping(value = {"/{id}/log/{date}", "/{id}/log"}, method = RequestMethod.GET)
    @ResponseBody
    public CommonResult log(@PathVariable("id") int id, @PathVariable(value = "date", required = false) String date) {
        LogDTO result = date == null ? seniorService.log(id) : seniorService.log(id, date);
        return CommonResult.success(result);
    }

    //    已测试
    @ApiOperation("老人异常事件")
    @RequestMapping(value = "/{id}/events", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult event(@PathVariable("id") int id) {
        List<EventDTO> result = seniorService.event(id);
        return CommonResult.success(result);
    }
}
