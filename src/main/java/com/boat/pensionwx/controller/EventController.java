package com.boat.pensionwx.controller;

import com.boat.pension.model.Wechatuser;
import com.boat.pensionwx.dto.EventDTO;
import com.boat.pensionwx.result.CommonResult;
import com.boat.pensionwx.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 异常事件管理Controller
 * Created by hxy on 2022/7/1,edited by oliver on 2022/7/24.
 */
@Controller
@Api(tags = "EventController", description = "异常事件管理")
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    //    已测试
    @ApiOperation(value = "获取某用户绑定的所有老人的全部异常事件列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getList(@RequestAttribute("user") Wechatuser user) {
        List<EventDTO> result = eventService.listAllByUser(user);
        System.out.println("api/event/list:");
        System.out.println(result);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "获取某用户绑定的所有老人的全部异常事件列表")
    @RequestMapping(value = "/list/active", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getActiveList(@RequestAttribute("user") Wechatuser user) {
        List<EventDTO> result = eventService.listAllActiveByUser(user);
        System.out.println("api/event/list/active:");
        System.out.println(result);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "护工确认接收")
    @RequestMapping(value = "/{eventid}/confirm", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult confirm(@PathVariable Integer eventid,
                                @RequestAttribute("user") Wechatuser user) {
        int count = eventService.confirm(eventid, user.getWorkerid());
        if (count > 0) {
            return CommonResult.success(count);
        }
        System.out.println(count);
        return CommonResult.failed();
    }

    @ApiOperation(value = "护工提交处理结果")
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