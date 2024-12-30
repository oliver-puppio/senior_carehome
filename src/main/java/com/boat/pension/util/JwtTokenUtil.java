package com.boat.pension.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil {
    protected static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    protected static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    protected String secret;
    @Value("${jwt.expiration}")
    protected Long expiration;
    @Value("${jwt.tokenHead}")
    protected String tokenHead;

    /**
     * 根据负责生成JWT的token
     */
    public String generateHeader(Map<String, Object> claims) {
        return tokenHead+Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    protected Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取openid
     */
    public String getAdminIDFromToken(String token) {
        String adminid;
        try {
            Claims claims = getClaimsFromToken(token);
            System.out.println(claims.toString());
            adminid=claims.get("adminid").toString();
            System.out.println(adminid);
        } catch (Exception e) {
            adminid = null;
        }
        return adminid;
    }

    /**
     * 从token中获取session_key
     */
    public String getSessionKeyFromToken(String token) {
        String session_key;
        try {
            Claims claims = getClaimsFromToken(token);
            session_key=claims.get("openid").toString();
        } catch (Exception e) {
            session_key = null;
        }
        return session_key;
    }


    /**
     * 判断token是否已经失效
     */
    protected boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    protected Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
    public String refreshHeadToken(String oldToken) {
        if(StrUtil.isEmpty(oldToken)){
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if(StrUtil.isEmpty(token)){
            return null;
        }
        //token校验不通过
        Claims claims = getClaimsFromToken(token);
        if(claims==null){
            return null;
        }
        //如果token已经过期，不支持刷新
        if(isTokenExpired(token)){
            return null;
        }
        //如果token在30分钟之内刚刷新过，返回原token
        if(tokenRefreshJustBefore(token,30*60)){
            return token;
        }else{
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateHeader(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     * @param token 原token
     * @param time 指定时间（秒）
     */
    protected boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        return refreshDate.after(created)&&refreshDate.before(DateUtil.offsetSecond(created,time));
    }
}
