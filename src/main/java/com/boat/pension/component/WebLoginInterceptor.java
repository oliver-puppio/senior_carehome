package com.boat.pension.component;

import com.boat.pension.api.CommonResult;
import com.boat.pension.api.ResultCode;
import com.boat.pension.mapper.AdminMapper;
import com.boat.pension.model.Admin;
import com.boat.pension.service.AdminService;
import com.boat.pension.service.EntityService;
import com.boat.pension.util.JwtTokenUtil;
import com.boat.pension.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class WebLoginInterceptor implements HandlerInterceptor {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AdminService adminService;

    @Autowired
    EntityService entityService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(true)
            return true;
        //0. 过滤注册和登录页面
        String url=request.getRequestURI();
        System.out.println("In interceptor, url:"+url);
//        if(url.contains("login") || url.contains("register") || url.contains("delete") || url.contains("testlive")  || url.contains("favicon.ico") || url.contains("swagger") || url.contains("query") || url.contains("add") || url.contains("bind") ||url.contains("delete")  ||url.contains("list") ||url.contains("update") ||url.contains("ditch")||url.contains("detail")||url.contains("leave")||url.contains("roomList")||url.contains("assign")||url.contains("log")||url.contains("record")||url.contains("event")||url.contains("live")||url.contains("liststate")){
        if(url.contains("login") || url.contains("register") || url.contains("test")){
            System.out.println("url.contains login or register");
            return true;
        }
        //1.解析token，获取adminid
        String authHeader = request.getHeader("Authorization");
        System.out.println("In itc,token="+authHeader);
        if (null == authHeader || !authHeader.startsWith("Bearer ")){
            //todo:返回token格式不正确提示
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.fromObject(CommonResult.failed(ResultCode.FAILED)).toString());
            System.out.println("Invalid authHeader:"+authHeader);
            return false;
        }
        String authToken = authHeader.substring("Bearer ".length());
        String adminid = jwtTokenUtil.getAdminIDFromToken(authToken);
        System.out.println("adminid:"+authHeader);
        //2.在redis中查询adminid，验证token是否有效
        if(!redisUtil.hasKey(adminid) || !redisUtil.get(adminid).toString().equals(authHeader)){
            //2.1.redis中无结果，说明前端token无效
            //todo:返回登录信息过期提示
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.fromObject(CommonResult.failed(ResultCode.UNAUTHORIZED)).toString());
            System.out.println("Expired token, it ought to be:"+redisUtil.get(adminid).toString());
            return false;
        }
        //2.2.redis中结果与前端token相同，说明token有效，更新token过期时间
        redisUtil.setKeyExpire(authHeader);
        //3.在数据库中查询用户是否存在，若存在则找到用户对象，将其写到request.setAttribute()中，供后续使用；否则只放行注册接口
        Admin admin = adminService.getAdminByAdminId(Integer.parseInt(adminid));
        entityService.setAdmin(admin);
        System.out.println(entityService.getAdmin());
//        if(user==null && !url.contains("register")){
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(JSONObject.fromObject(CommonResult.failed(ResultCode.FORBIDDEN)).toString());
//            return false;
//        }
//        if(user==null){
//            user = new Wechatuser();
//            user.setOpenid(openid);
//            System.out.println("User is null");
//        }
//        System.out.println("user is "+user.toString());
        request.setAttribute("admin",admin);
        return true;

    }
}
