package com.boat.pensionwx.service;

import com.boat.pension.model.Wechatuser;
import net.sf.json.JSONObject;

import java.util.List;

public interface WxUserService {
    JSONObject login(String code);

    int register(String code, Wechatuser user);

    Wechatuser getUserByOpenid(String openid);

    Wechatuser getUserById(int id);

    /**
     * 获取用户详细信息，包括老人列表
     * @param wechatuser 用户实体
     * @return 用户信息、老人列表
     */
    JSONObject userDetail(Wechatuser wechatuser);

    /***
     * 获取用户绑定的老人列表
     * @param wechatuser 用户实体
     * @return 老人列表
     */
    JSONObject seniorList(Wechatuser wechatuser);

    /***
     * 获取老人id列表
     * @param wechatuser 用户实体
     * @return 老人id列表
     */
    List<Integer> seniorIDList(Wechatuser wechatuser);

    /**
     *  按设备id添加老人绑定
     * @param wechatuser 用户实体
     * @param deviceID 设备id
     * @return -1表示设备未绑定老人，-2表示老人已绑定，-3表示未知错误
     */
    int seniorAdd(Wechatuser wechatuser,int deviceID);

    /**
     * 解绑老人
     * @param wechatuser 用户实体
     * @param seniorID 老人id
     * @return -1表示位置错误，0表示删除失败
     */
    int seniorDelete(Wechatuser wechatuser,int seniorID);

}
