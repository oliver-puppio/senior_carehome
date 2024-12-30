package com.boat.pension.controller;


import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.WorkerParam;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Worker;
import com.boat.pension.service.EntityService;
import com.boat.pension.service.WorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 护工管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "WorkerController", description = "护工管理")
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @Autowired
    private EntityService entityService;

    //    已测试
    @ApiOperation("获取指定护工信息")
    @RequestMapping(value = "/{id}/query", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Worker> query(@PathVariable Integer id) {
        Worker worker = workerService.getWorkerByWorkerId(id);
        return CommonResult.success(worker);
    }

    //    已测试
    @ApiOperation(value = "获取某一养老院的全部护工列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getList() {
        Admin admin= entityService.getAdmin();
        return CommonResult.success(workerService.listAllByInstid(admin.getInstid()));
    }

    //    已测试
    @ApiOperation(value = "获取指定护工信息和监管房间列表")
    @RequestMapping(value = "/{id}/roomlist", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getRoomList(@PathVariable("id") Integer id) {
        Admin admin= entityService.getAdmin();
        return CommonResult.success(workerService.listRoomByWorkerId(id, admin.getInstid()));
    }

    //    已测试
    @ApiOperation("添加护工")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(
            @RequestParam("num") String num,
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("idnum") String idnum,
            @RequestParam("tel") String tel) {
        WorkerParam worker=new WorkerParam();
        worker.setGender(gender);
        worker.setIdnum(idnum);
        worker.setName(name);
        worker.setTel(tel);
        worker.setNum(num);
        Admin admin= entityService.getAdmin();
        CommonResult commonResult;
        int count = workerService.addWorker(admin.getInstid(), worker);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    //    已测试
    @ApiOperation("修改指定护工信息")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id,
                               @RequestParam String num,
                               @RequestParam String name,
                               @RequestParam String gender,
                               @RequestParam String idnum,
                               @RequestParam String tel) {
        WorkerParam worker = new WorkerParam();
        worker.setName(name);
        worker.setNum(num);
        worker.setGender(gender);
        worker.setIdnum(idnum);
        worker.setTel(tel);
        int count = workerService.update(id, worker);
        if (count > 0) {
            return CommonResult.success(count);
        }
        System.out.println(count);
        return CommonResult.failed();
    }

    //    已测试
    @ApiOperation("删除指定护工信息")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id) {
        int count = workerService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    //    已测试
    @ApiOperation("更新监护房间(添加、删除),0表示删除，1表示添加")
    @RequestMapping(value = "/{id}/assign/{roomid}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult assign(@PathVariable(value = "id") Integer workerid,
                               @PathVariable(value = "roomid") Integer roomid,
                               @RequestParam(value = "flag") int flag) {
        int count = 0;
        if (flag > 0) {//添加
            count = workerService.addRoom(workerid, roomid);
            if (count > 0) {
                return CommonResult.success(count);
            } else
                return CommonResult.failed("该房间已有护工");
        } else {
            count = workerService.delRoom(workerid, roomid);
            if (count > 0) {
                return CommonResult.success(count);
            } else
                return CommonResult.failed("该护工不负责该房间");
        }
    }
}
