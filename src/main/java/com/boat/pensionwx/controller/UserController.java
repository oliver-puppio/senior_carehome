package com.boat.pensionwx.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.boat.pension.model.Wechatuser;
import com.boat.pensionwx.result.CommonResult;
import com.boat.pensionwx.result.ResultCode;
import com.boat.pensionwx.service.WxUserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

@Controller
//@Api(tags = "UserController", value = "微信用户管理")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    WxUserService wxUserService;


//    @ApiOperation("微信用户登录")
    @RequestMapping(value = "/wxlogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult wxlogin(@RequestParam(value = "code", required = false) String code) {
        if (code == null)
            return CommonResult.failed();
        JSONObject result = wxUserService.login(code);
        if (result == null)
            return CommonResult.failed();
        return CommonResult.success(result);
    }

//    @ApiOperation("微信用户上传手机号，完成注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam(value = "code") String code, @RequestAttribute(value = "user") Wechatuser user) {
        System.out.println("In register api,user=" + user.toString());
        int res = wxUserService.register(code, user);
        if (res == 0)
            return CommonResult.success("ok");
        else
            return CommonResult.failed();

    }

//    @ApiOperation("获取用户信息")
    @GetMapping("/detail")
    @ResponseBody
    public CommonResult detail(@RequestAttribute(value = "user") Wechatuser user) {
        try {
            JSONObject result = wxUserService.userDetail(user);
            return CommonResult.success(result);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }

//    @ApiOperation("获取用户绑定的老人")
    @GetMapping("/senior/list")
    @ResponseBody
    public CommonResult seniorList(@RequestAttribute(value = "user") Wechatuser user) {
        JSONObject result = wxUserService.seniorList(user);
        return CommonResult.success(result);
    }

//    @ApiOperation("添加老人绑定")
    @PostMapping("/senior/add/{deviceID}")
    @ResponseBody
    public CommonResult seniorAdd(@RequestAttribute(value = "user") Wechatuser user, @PathVariable(value = "deviceID") int deviceID) {
        int res = wxUserService.seniorAdd(user, deviceID);
        if (res == 1) {
            return CommonResult.success(ResultCode.SUCCESS);
        } else {
            String msg;
            switch (res) {
                case -1:
                    msg = "设备未绑定老人";
                    break;
                case -2:
                    msg = "老人已存在";
                    break;
                default:
                    msg = "未知错误";
            }
            return CommonResult.failed(msg);
        }
    }

//    @ApiOperation("删除老人绑定")
    @PostMapping("/senior/delete/{seniorID}")
    @ResponseBody
    public CommonResult seniorDelete(@RequestAttribute(value = "user") Wechatuser user, @PathVariable(value = "seniorID") int seniorID) {
        int res = wxUserService.seniorDelete(user, seniorID);
        if (res == 1) {
            return CommonResult.success(ResultCode.SUCCESS);
        } else {
            return CommonResult.failed(res > -1 ? "未绑定该老人" : "未知错误");
        }
    }

    @Value("${aliyun.endPoint}")
    private String endPoint;
    @Value("${aliyun.accessKeyId}")
    private String akid;
    @Value("${aliyun.accessKeySecret}")
    private String aksecret;

//    @ApiOperation("测试接口")
    @RequestMapping(value = "/wxtest/wxlogin/swagger", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject wxtest() throws FileNotFoundException {
//        OSS ossClient = new OSSClientBuilder().build(endPoint, akid, aksecret);
//        InputStream inputStream=new FileInputStream("D:\\QQ\\personal data\\2318178180\\FileRecv\\Snipaste_2022-07-22_20-00-58.jpg");
//        ossClient.putObject("boat-pension", UUID.randomUUID().toString(),inputStream);
        JSONObject r = new JSONObject();

        r.put("ok", "w");
        return r;
    }
}
