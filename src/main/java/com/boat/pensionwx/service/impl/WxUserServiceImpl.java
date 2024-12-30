package com.boat.pensionwx.service.impl;

import cn.hutool.core.util.StrUtil;
import com.boat.pension.mapper.BedMapper;
import com.boat.pension.mapper.SeniorMapper;
import com.boat.pension.mapper.UserseniorMapper;
import com.boat.pension.mapper.WechatuserMapper;
import com.boat.pension.model.*;
import com.boat.pensionwx.service.WorkerService;
import com.boat.pensionwx.dto.SeniorHealthDetailDTO;
import com.boat.pensionwx.dto.WechatUserDetailDTO;
import com.boat.pensionwx.service.WxUserService;
import com.boat.pensionwx.util.WechatUtil;
import com.boat.pensionwx.util.WxJwtTokenUtil;
import com.boat.pensionwx.util.WxRedisUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    WechatUtil wechatUtil;
    @Autowired
    WxJwtTokenUtil jwtTokenUtil;
    @Autowired
    WxRedisUtil redisUtil;

    @Autowired
    WechatuserMapper wechatuserMapper;

    @Autowired
    UserseniorMapper userseniorMapper;

    @Autowired
    BedMapper bedMapper;

    @Autowired
    SeniorMapper seniorMapper;

    @Autowired
    WorkerService workerService;

    @Override
    public JSONObject login(String code) {
        // 1. 解析code获取获取openid、sessionKey等信息
        JSONObject sessionKeyOrOpenId = wechatUtil.getSessionKeyOrOpenId(code);
        String openid = sessionKeyOrOpenId.getString("openid");
        String sessionKey = sessionKeyOrOpenId.getString("session_key");
        if (StrUtil.isEmpty(openid)) {
            return null;
        }
        String token;
        JSONObject result = new JSONObject();
        // 2. 根据openid在redis中查找该用户
        if (redisUtil.hasKey(openid)) {
            //  2.1. 若用户存在，则返回redis中对应的token，并更新token有效期
            token = redisUtil.get(openid).toString();
            System.out.println("User exists. In login api, token from redis=" + token);
            redisUtil.setKeyExpire(openid);
            // 若用户在数据库中存在，则返回用户对象
            Wechatuser user = getUserByOpenid(openid);
            if (user != null) {
                result.put("user", new WechatUserDetailDTO(user));
            }
        } else {
            //  2.2. 若用户不存在，则将claims加密生成jwt-token，并将记录写入redis，然后返回生成的token
            HashMap<String, Object> claims = new HashMap<>();//生成token对应信息
            claims.put("openid", openid);
            claims.put("session_key", sessionKey);
            token = jwtTokenUtil.generateHeader(claims);
            System.out.println("User doesn't exist. In login api, jwt-token generated is " + token);
            redisUtil.set(openid, token);
        }
        //返回token结果

        result.put("token", token);
        return result;
    }

    @Override
    public int register(String code, Wechatuser user) {
        try {
            JSONObject result = wechatUtil.getPhoneNumber(code);
            String phone = JSONObject.fromObject(result.get("phone_info")).get("phoneNumber").toString();
            System.out.println("In register service phone is " + phone);
            user.setTel(phone);
            int workerID = workerService.verifyByPhone(user);
            if (workerID > 0) {
                user.setIsworker(1);
                user.setWorkerid(workerID);
            } else {
                user.setIsworker(0);//老老实实加上
            }
            if (user.getId() != null) {
                wechatuserMapper.updateByPrimaryKey(user);
            } else {
                wechatuserMapper.insert(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Wechatuser getUserByOpenid(String openid) {
        WechatuserExample example = new WechatuserExample();
        example.setDistinct(true);
        example.createCriteria().andOpenidEqualTo(openid);
        List<Wechatuser> users = wechatuserMapper.selectByExample(example);
        return users.size() == 0 ? null : users.get(0);
    }

    @Override
    public Wechatuser getUserById(int id) {
        return wechatuserMapper.selectByPrimaryKey(id);
    }

    @Override
    public JSONObject userDetail(Wechatuser wechatuser) {
        JSONObject result = new JSONObject();
        //获取用户基本信息
        WechatUserDetailDTO base = new WechatUserDetailDTO(wechatuser);
        result.put("user", base);
        //获取老人列表
        result.putAll(seniorList(wechatuser));
        System.out.println("用户详情");
        System.out.println(result);
        return result;
    }

    /***
     * 获取老人id列表
     * @param wechatuser 用户实体
     * @return 老人id列表
     */
    public List<Integer> seniorIDList(Wechatuser wechatuser) {
        //1.获取用户绑定的老人
        List<Integer> seniorIDs = new ArrayList<>();


        //从用户-老人表中获取老人id
        UserseniorExample userseniorExample = new UserseniorExample();
        userseniorExample.createCriteria().andUseridEqualTo(wechatuser.getId());
        List<Usersenior> userseniors = userseniorMapper.selectByExample(userseniorExample);
        if (userseniors.size() > 0) {
            for (Usersenior usersenior : userseniors) {
                seniorIDs.add(usersenior.getSeniorid());
            }
        }
        //如果是护工还需要查房间表
        if (wechatuser.getIsworker() == 1) {
            List<Integer> idInRooms = workerService.selectSeniorsID(wechatuser.getWorkerid());
            if(idInRooms.size()>0){
                seniorIDs.removeAll(idInRooms);//去重
                seniorIDs.addAll(idInRooms);//合并
            }
        }
        return seniorIDs;
    }

    @Override
    public JSONObject seniorList(Wechatuser wechatuser) {
        List<SeniorHealthDetailDTO> seniors = new ArrayList<>();
        List<Integer> seniorIDs = seniorIDList(wechatuser);
        if (seniorIDs.size() > 0) {
            for (Integer i : seniorIDs) {
                Senior senior = seniorMapper.selectByPrimaryKey(i);
                BedExample example = new BedExample();
                example.createCriteria().andSenioridEqualTo(i);
                Bed bed = bedMapper.selectByExample(example).get(0);
                seniors.add(new SeniorHealthDetailDTO(senior, bed));
            }
            Collections.sort(seniors, new Comparator<SeniorHealthDetailDTO>() {
                @Override
                public int compare(SeniorHealthDetailDTO o1, SeniorHealthDetailDTO o2) {
                    return (o1.getId() - o2.getId() > 0) ? 1 : -1;
                }
            });
        } else {
            seniors = null;
        }

        //2.返回结果
        JSONObject result = new JSONObject();
        result.put("seniors", seniors);
        return result;
    }

    @Override
    public int seniorAdd(Wechatuser wechatuser, int deviceID) {
        try {
            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andDeviceidEqualTo(deviceID).andSenioridIsNotNull();
            List<Bed> bedList = bedMapper.selectByExample(bedExample);
            if (bedList.size() == 0) {
                return -1;
            }
            int seniorID = bedList.get(0).getSeniorid();
            //在用户老人列表中查找该用户是否已经绑定过老人
            List<Integer> seniorIDList = seniorIDList(wechatuser);
            if (seniorIDList.contains(seniorID) && seniorIDList.size() > 0) {
                return -2;
            }
            Usersenior usersenior = new Usersenior();
            usersenior.setSeniorid(seniorID);
            usersenior.setUserid(wechatuser.getId());
            usersenior.setBindtime(new Date());
            int result = userseniorMapper.insert(usersenior);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }

    }

    @Override
    public int seniorDelete(Wechatuser wechatuser, int seniorID) {
        try {
            UserseniorExample example = new UserseniorExample();
            example.createCriteria().andUseridEqualTo(wechatuser.getId()).andSenioridEqualTo(seniorID);
            int res = userseniorMapper.deleteByExample(example);
            return res;
        } catch (Exception e) {
            return -1;
        }
    }
}
