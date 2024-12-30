package com.boat.pension.service;


import com.boat.pension.dto.AdminDetail;
import com.boat.pension.dto.AdminParam;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Wechatuser;
import net.sf.json.JSONObject;

/**
 * 管理员管理Service
 * Created by hxy on 2022/7/1.
 */
public interface AdminService {
    /**
     * 根据管理员id获取管理员
     * @param adminId 管理员id
     * @return 管理员
     */
    Admin getAdminByAdminId(Integer adminId);

    /***
     * 获取管理员详情
     * @param admin
     * @return
     */
    AdminDetail getAdminDetail(Admin admin);

    /**
     * 修改指定管理员信息
     * @param id,admin 管理员id 管理员
     * @return 影响记录数
     */
    int update(Integer id, AdminParam admin);

    /**
     * 删除指定管理员
     * @param id 管理员id
     * @return 影响记录数
     */
    int delete(Integer id);
    /**
     * 管理员登录
     * @param adminid,password 管理员id,管理员密码
     * @return 影响记录数
     */
    JSONObject login(Integer adminid, String password);
    /**
     * 管理员注册
     * @param admin 管理员参数
     * @return 影响记录数
     */
    int register(AdminParam admin);


}
