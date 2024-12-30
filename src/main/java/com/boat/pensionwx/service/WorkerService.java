package com.boat.pensionwx.service;

import com.boat.pension.model.Wechatuser;

import java.util.List;

public interface WorkerService {
    /**
     * 按护工id获取该护工负责的所有老人
     * @param id 护工id
     * @return 老人id列表
     */
    List<Integer> selectSeniorsID(int id);

    /**
     * 验证该用户是否为护工，若是则返回护工id，否则返回-1
     * @param user 用户实体
     * @return
     */
    int verifyByPhone(Wechatuser user);
}
