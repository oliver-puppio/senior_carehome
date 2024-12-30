package com.boat.pension.controller;

import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.EventDTO;
import com.boat.pension.dto.EventParam;
import com.boat.pension.dto.RoomParam;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Event;
import com.boat.pension.model.Institution;
import com.boat.pension.service.EntityService;
import com.boat.pension.service.EventService;
import com.boat.pension.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 异常事件管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "EventController", description = "异常事件管理")
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    EntityService entityService;
//    已测试
    @ApiOperation("添加异常事件")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestAttribute("admin") Admin admin,
                            @RequestParam Integer seniorID,
                            @RequestParam Integer deviceID,
                            @RequestParam Integer type) {
        EventParam eventParam = new EventParam();
        eventParam.setDeviceid(deviceID);
        eventParam.setSeniorid(seniorID);
        eventParam.setType(type);

        CommonResult commonResult;
        int count = eventService.addEvent(admin.getInstid(),eventParam);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

//    已测试
    @ApiOperation(value = "获取某一养老院的全部异常事件列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<EventDTO>> getList() {
        Admin admin=entityService.getAdmin();
        System.out.println(admin);
        return CommonResult.success(eventService.listAllByInstid(admin.getInstid()));
    }

    @ApiOperation(value = "获取某一养老院的未处理的异常事件列表")
    @RequestMapping(value = "/list/finished", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<EventDTO>> getListFinished(){
        Admin admin=entityService.getAdmin();
        return CommonResult.success(eventService.listFinishedByInstid(admin.getInstid()));
    }

    @ApiOperation(value = "获取某一养老院的已处理的异常事件列表")
    @RequestMapping(value = "/list/unfinished", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<EventDTO>> getListUnfinished() {
        Admin admin=entityService.getAdmin();
        return CommonResult.success(eventService.listUnfinishedByInstid(admin.getInstid()));
    }


    @ApiOperation(value = "管理员确认接收")
    @RequestMapping(value = "/{eventid}/confirm", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult confirm(@PathVariable Integer eventid,
                                @RequestAttribute("admin") Admin admin) {
        int count = eventService.confirm(eventid, admin.getId());
        if (count > 0) {
            return CommonResult.success(count);
        }
        System.out.println(count);
        return CommonResult.failed();
    }


    @ApiOperation(value = "管理员提交处理结果")
    @RequestMapping(value = "/{eventid}/finish", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult finish(@PathVariable Integer eventid,
                               @RequestParam(value = "resultText") String resultText
                               ) {
        int count = eventService.finish(eventid, resultText);
        if (count > 0) {
            return CommonResult.success(count);
        }
        System.out.println(count);
        return CommonResult.failed();
    }
}
