package com.boat.pension.service.impl;

import com.boat.pension.dto.AdminDetail;
import com.boat.pension.dto.AdminParam;
import com.boat.pension.mapper.AdminMapper;
import com.boat.pension.mapper.InstitutionMapper;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Institution;
import com.boat.pension.service.AdminService;
import com.boat.pension.util.JwtTokenUtil;
import com.boat.pension.util.RedisUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Primary
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Autowired(required = false)
    private InstitutionMapper carehomeMapper;

    @Autowired(required = false)
    JwtTokenUtil jwtTokenUtil;
    @Autowired(required = false)
    RedisUtil redisUtil;

    @Override
    public Admin getAdminByAdminId(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public AdminDetail getAdminDetail(Admin admin) {
        Institution institution = carehomeMapper.selectByPrimaryKey(admin.getInstid());
        AdminDetail detail = new AdminDetail(admin, institution);
        System.out.println(detail);
        return detail;
    }

    @Override
    public int update(Integer id, AdminParam adminParam) {
        Admin rawAdmin = adminMapper.selectByPrimaryKey(id);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (rawAdmin.getPassword().equals(bCryptPasswordEncoder.encode(adminParam.getPassword()))) {
            //与原加密密码相同的不需要修改
            adminParam.setPassword(null);
        } else {
            //与原加密密码不同的需要加密修改
            if (StrUtil.isEmpty(adminParam.getPassword())) {
                adminParam.setPassword(null);
            } else {
                adminParam.setPassword(bCryptPasswordEncoder.encode(adminParam.getPassword()));
            }
        }
        Institution rawInstitute = carehomeMapper.selectByPrimaryKey(rawAdmin.getInstid());
        BeanUtils.copyProperties(adminParam, rawAdmin);
        System.out.println(rawInstitute);
        BeanUtils.copyProperties(adminParam, rawInstitute);
        System.out.println(rawInstitute);
        System.out.println(rawAdmin);
        rawAdmin.setId(id);
        int count = adminMapper.updateByPrimaryKeySelective(rawAdmin);
        int count2 = carehomeMapper.updateByPrimaryKeySelective(rawInstitute);
        return count+count2;
    }

    @Override
    public int delete(Integer id) {
//        getCacheService().delAdmin(id);
        int count = adminMapper.deleteByPrimaryKey(id);
//        getCacheService().delResourceList(id);
        return count;
    }


    @Override
    public JSONObject login(Integer adminid, String password) {
        // 1. 将adminid转成String
        String sadminid = adminid.toString();
//        String sessionKey = sessionKeyOrAdminId.getString("session_key");

        String token = "";
        //2.1. 判断用户是否存在，密码是否正确
        Admin admin = getAdminByAdminId(adminid);
        if (admin == null) {//用户不存在
            return null;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //  2.2. 若用户存在但是密码不正确
        if (!bCryptPasswordEncoder.matches(password, admin.getPassword())) {
            System.out.println("The password is wrong.");
            return null;
        }
        // 根据adminid在redis中查找该用户
        if (redisUtil.hasKey(sadminid)) {
            System.out.println("用户存在");
            //  2.3. 若用户存在，则返回redis中对应的token，并更新token有效期
            token = redisUtil.get(sadminid).toString();
            System.out.println("Admin exists. In login api, token from redis=" + token);
            redisUtil.setKeyExpire(sadminid);
        } else {
            //  2.4. 若用户不存在，则将claims加密生成jwt-token，并将记录写入redis，然后返回生成的token   说明过期
            HashMap<String, Object> claims = new HashMap<>();//生成token对应信息
            claims.put("adminid", sadminid);
//            claims.put("session_key",sessionKey);
            token = jwtTokenUtil.generateHeader(claims);
            System.out.println("Admin doesn't exist. In login api, jwt-token generated is " + token);
            redisUtil.set(sadminid, token);
        }
        //返回token结果
        JSONObject result = new JSONObject();
        result.put("token", token);
        //todo 如果有报错，则token再拼上“Bearer ”
        return result;
    }

    @Override
    public int register(AdminParam adminParam) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminParam, admin);
        admin.setCname(adminParam.getCname());
        //1. 加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(encode);
        admin.setState(0);
        int res;
        //2. 审核
        if (check(admin.getId()) == 1) {
            admin.setState(1);
            Institution carehome = new Institution();
            BeanUtils.copyProperties(adminParam, carehome);
            carehomeMapper.insert(carehome);
            admin.setInstid(carehome.getId());
            res = adminMapper.insert(admin);
        } else {
            admin.setState(2);
            res = adminMapper.insert(admin);
        }
        if (res > 0) {
            return admin.getId();
        } else {
            return 0;
        }
    }

    //TODO:审核
    public int check(Integer adminid) {
        if (true) {
            return 1;
        } else {
            return 0;
        }
    }
}
