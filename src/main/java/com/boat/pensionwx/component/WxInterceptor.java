package com.boat.pensionwx.component;

import com.boat.pension.mapper.WechatuserMapper;
import com.boat.pension.model.Wechatuser;
import com.boat.pensionwx.result.CommonResult;
import com.boat.pensionwx.result.ResultCode;
import com.boat.pensionwx.service.WxUserService;
import com.boat.pensionwx.util.WxJwtTokenUtil;
import com.boat.pensionwx.util.WxRedisUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class WxInterceptor implements HandlerInterceptor {
    @Autowired
    WxRedisUtil redisUtil;

    @Autowired
    WxJwtTokenUtil jwtTokenUtil;

    @Autowired
    WechatuserMapper wechatuserMapper;

    @Autowired
    WxUserService wxUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //0. 过滤登录页面
        String url=request.getRequestURI();
        System.out.println();
        System.out.println("In interceptor, url:"+url);
        if(url.contains("wxlogin") || url.contains("test")){
            System.out.println("url.contains wxlogin or test");
            return true;
        }
        //1.解析token，获取openid
        String authHeader = request.getHeader("Authorization");
        if (null == authHeader || !authHeader.startsWith("Bearer ")){
            //返回token格式不正确提示
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.fromObject(CommonResult.failed(ResultCode.FAILED)).toString());
            System.out.println("Invalid authHeader:"+authHeader);
            return false;
        }
        String authToken = authHeader.substring("Bearer ".length());
        String openid = jwtTokenUtil.getOpenidFromToken(authToken);
        //2.在redis中查询openid，验证token是否有效
        if(!redisUtil.hasKey(openid) || !redisUtil.get(openid).toString().equals(authHeader)){
            //2.1.redis中无结果，说明前端token无效
            //返回登录信息过期提示
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.fromObject(CommonResult.failed(ResultCode.UNAUTHORIZED)).toString());
            System.out.println("Expired token, it ought to be:"+redisUtil.get(openid).toString());
            return false;
        }
        //2.2.redis中结果与前端token相同，说明token有效，更新token过期时间
        redisUtil.setKeyExpire(authHeader);
        //3.在数据库中查询用户是否存在，若存在则找到用户对象，将其写到request.setAttribute()中，供后续使用；否则只放行注册接口
        Wechatuser user=wxUserService.getUserByOpenid(openid);
        if(user==null && !url.contains("register")){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.fromObject(CommonResult.failed(ResultCode.FORBIDDEN)).toString());
            return false;
        }
        if(user==null){
            user = new Wechatuser();
            user.setOpenid(openid);
            System.out.println("User is null");
        }
        System.out.println("In itc ,user is "+user.toString());
        request.setAttribute("user",user);
        return true;

    }
}
