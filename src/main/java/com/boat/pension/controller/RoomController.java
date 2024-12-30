package com.boat.pension.controller;


import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.RoomDTO;
import com.boat.pension.dto.RoomParam;
import com.boat.pension.model.Admin;
import com.boat.pension.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房间管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "RoomController", description = "房间管理")
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomService roomService;



    //    已测试
    @ApiOperation(value = "获取某一机构的全部房间列表和入住情况")
    @RequestMapping(value = "/list/{flag}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getList(@RequestAttribute("admin") Admin admin,@PathVariable Integer flag) {
        return CommonResult.success(roomService.listAllByInstId(admin.getInstid(),flag));
    }

    @ApiOperation(value = "获取某一机构的接入社区中的房间列表和入住情况")
    @RequestMapping(value = "/listcommunity", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getListInCommunities(@RequestAttribute("admin") Admin admin) {
        return CommonResult.success(roomService.listAllInCommunityByInstId(admin.getInstid()));
    }

    //    已测试
    @ApiOperation("添加房间和对应的床位")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestAttribute("admin") Admin admin,
                            @RequestParam Integer buildingID,
                            @RequestParam String num,
                            @RequestParam Integer size,
                            @RequestParam Integer workerID) {
        RoomParam room = new RoomParam();
        room.setBuildingid(buildingID);
        room.setNum(num);
        room.setSize(size);
        room.setInstid(admin.getInstid());
        room.setWokerid(workerID);

//        if(room.getInstid()==null){
//            room.setInstid(admin.getInstid());
//        }
        CommonResult commonResult;
        int count = roomService.addRoom(admin, room);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    //    已测试
//    @ApiOperation("修改指定房间信息")
//    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult update(@RequestAttribute("admin") Admin admin,@PathVariable Integer id,
//                               @RequestParam String buildingName,
//                               @RequestParam String num,
//                               @RequestParam Integer size,
//                               @RequestParam Integer instID) {
//        RoomParam room = new RoomParam();
//        room.setBuildingname(buildingName);
//        room.setNum(num);
//        room.setSize(size);
//        room.setInstid(instID);
//
//        if(room.getInstid()==null){
//            room.setInstid(admin.getInstid());
//        }
//        int count = roomService.update(id, room);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        System.out.println(count);
//        return CommonResult.failed();
//    }

    //    已测试
    @ApiOperation("删除指定房间信息")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id) {
        int count = roomService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    //    已测试
    @ApiOperation("获取指定房间信息")
    @RequestMapping(value = "/{id}/query", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getItem(@PathVariable Integer id) {
        JSONObject roomDetail = roomService.getRoomDetailByRoomId(id);
        return CommonResult.success(roomDetail);
    }
}
