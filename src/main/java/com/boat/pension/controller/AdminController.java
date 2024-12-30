package com.boat.pension.controller;

import com.boat.pension.api.CommonResult;
import com.boat.pension.dto.AdminDetail;
import com.boat.pension.dto.AdminParam;
import com.boat.pension.model.Admin;
import com.boat.pension.service.AdminService;
import com.boat.pension.service.EntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 管理员管理Controller
 * Created by hxy on 2022/7/1.
 */
@Controller
@Api(tags = "AdminController", description = "管理员管理")
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    @Autowired
//    @Qualifier("BadServiceImpl")
//    @Resource(name = "BadServiceImpl")
    private AdminService adminService;


    @Autowired
    private EntityService entityService;

    @ApiOperation("获取指定管理员信息")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult query() {
        Admin admin = entityService.getAdmin();
        AdminDetail detail = adminService.getAdminDetail(admin);
        return CommonResult.success(detail);
    }

    //    已测试
    @ApiOperation("修改指定管理员信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestAttribute("admin") Admin admin,
                               @RequestParam String cname,
                               @RequestParam String license,
                               @RequestParam String password,
                               @RequestParam String name,
                               @RequestParam String loc,
                               @RequestParam String leader,
                               @RequestParam String tel) {
        AdminParam adminParam = new AdminParam();
        adminParam.setCname(cname);
        adminParam.setLicense(license);
        adminParam.setPassword(password);
        adminParam.setName(name);
        adminParam.setLoc(loc);
        adminParam.setLeader(leader);
        adminParam.setTel(tel);
        int count = adminService.update(admin.getId(), adminParam);
        if (count == 2 ) {
            return CommonResult.success(count);
        }
        System.out.println(count);
        return CommonResult.failed();
    }

    //    已测试
    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestAttribute("admin") Admin admin) {
        int count = adminService.delete(admin.getId());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    //    已测试
    @ApiOperation("管理员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam String cname,
                                 @RequestParam String license,
                                 @RequestParam String password,
                                 @RequestParam String name,
                                 @RequestParam String loc,
                                 @RequestParam String leader,
                                 @RequestParam String tel) {
        AdminParam adminParam = new AdminParam();
        adminParam.setCname(cname);
        adminParam.setLicense(license);
        adminParam.setPassword(password);
        adminParam.setName(name);
        adminParam.setLoc(loc);
        adminParam.setLeader(leader);
        adminParam.setTel(tel);

        int res = adminService.register(adminParam);
        if (res > 0)
            return CommonResult.success(res);
        else
            return CommonResult.failed();

    }

    //    已测试
    @ApiOperation("管理员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam(value = "username") Integer username,
                              @RequestParam(value = "password") String password) {

        System.out.println("login is called with " + username + password);
        if (username == null || password == null)
            return CommonResult.validateFailed("用户名或密码不能为空");
        JSONObject result = adminService.login(username, password);
        if (result == null)
            return CommonResult.validateFailed("用户名或密码错误");
        return CommonResult.success(result);
    }

    //    已测试
    @ApiOperation("管理员登录")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult test() {

        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        return CommonResult.success(result);
    }
}
